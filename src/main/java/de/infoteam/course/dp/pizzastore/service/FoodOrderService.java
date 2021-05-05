package de.infoteam.course.dp.pizzastore.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.repository.DishRepository;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Subscriber;

public class FoodOrderService implements Subscriber<DishStateChange> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodOrderService.class);

	private final FoodFactory sicilianPizzaFactory;
	private final FoodFactory gourmetPizzaFactory;
	private final IngredientLogger ingredientLogger;
	private final DishRepository dishRepository;
	private final ExecutorService kitchen;
	private final AtomicLong orderIdSequence = new AtomicLong(0);

	private FoodOrderService(FoodFactory sicilianPizzaFactory, FoodFactory gourmetPizzaFactory,
			IngredientLogger ingredientLogger, DishRepository pizzaRepository, int numberOfChefs) {
		this.sicilianPizzaFactory = sicilianPizzaFactory;
		this.gourmetPizzaFactory = gourmetPizzaFactory;
		this.ingredientLogger = ingredientLogger;
		this.dishRepository = pizzaRepository;
		this.kitchen = Executors.newFixedThreadPool(numberOfChefs);
	}

	public Dish order(MenuItem selectedItem, FoodStyle selectedStyle) {
		Dish dish = chooseFactory(selectedStyle).createDish(selectedItem, orderIdSequence.incrementAndGet());
		// speicher die Pizza im Repository
		this.dishRepository.saveOrUpdate(dish);

		LOGGER.info("Received new order for {} {}", selectedStyle.getName(), dish.name());
		
		FoodPreparationTask task =	new FoodPreparationTask(dish, ingredientLogger);
		task.subscribe(this);
		kitchen.submit(task);

		return dish;
	}

	FoodFactory chooseFactory(FoodStyle selectedStyle) {
		FoodFactory factory = null;
		switch (selectedStyle) {
		case SICILIAN:
			factory = this.sicilianPizzaFactory;
			break;
		case GOURMET:
			factory = this.gourmetPizzaFactory;
			break;
		default:
			throw new IllegalArgumentException("No PizzaFactory for style " + selectedStyle);
		}
		return factory;
	}

	public void shutdown() {
		LOGGER.info("The PizzaKitchen is closing now. Pizza in progress will be finished though...");
		this.kitchen.shutdown();
	}

	@Override
	public void update(DishStateChange context) {
		LOGGER.info("Receiving update {} {}", context.getDishId(), context.getState());
		this.dishRepository.findById(context.getDishId()).ifPresent(pizza -> {
			pizza.updateState(context.getState());
			this.dishRepository.saveOrUpdate(pizza);
		});
	}

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder für {@code PizzaService}
	 */
	public static final class Builder {

		private FoodFactory sicilianFactory;
		private FoodFactory gourmetFactory;
		private IngredientLogger ingredientLogger;
		private DishRepository pizzaRepository;
		private int numberOfChefs = 1;

		private Builder() {
			super();
		}

		public Builder sicilianFactory(FoodFactory sicilianFactory) {
			this.sicilianFactory = sicilianFactory;
			return this;
		}

		public Builder gourmetFactory(FoodFactory gourmetFactory) {
			this.gourmetFactory = gourmetFactory;
			return this;
		}

		public Builder ingredientLogger(IngredientLogger ingredientLogger) {
			this.ingredientLogger = ingredientLogger;
			return this;
		}

		public Builder pizzaRepository(DishRepository pizzaRepository) {
			this.pizzaRepository = pizzaRepository;
			return this;
		}

		public Builder numberOfChefs(int numberOfChefs) {
			if (numberOfChefs < 1 || numberOfChefs > 8) {
				throw new IllegalArgumentException("Number of chefs must be between 1 and 8");
			}
			this.numberOfChefs = numberOfChefs;
			return this;
		}

		public FoodOrderService build() {
			if (this.sicilianFactory == null) {
				throw new IllegalStateException("A Sicilian PizzaFactory is required");
			}
			if (this.gourmetFactory == null) {
				throw new IllegalStateException("A Gourmet PizzaFactory is required");
			}
			if (this.ingredientLogger == null) {
				throw new IllegalStateException("An IngredientLogger is required");
			}
			if (this.pizzaRepository == null) {
				throw new IllegalStateException("A PizzaRepository is required");
			}
			return new FoodOrderService(sicilianFactory, gourmetFactory, ingredientLogger, pizzaRepository, numberOfChefs);
		}
	}
}

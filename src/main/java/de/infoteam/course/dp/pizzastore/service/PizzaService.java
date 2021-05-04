package de.infoteam.course.dp.pizzastore.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import de.infoteam.course.dp.pizzastore.repository.PizzaRepository;

public class PizzaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

	private final PizzaFactory sicilianPizzaFactory;
	private final PizzaFactory gourmetPizzaFactory;
	private final IngredientLogger ingredientLogger;
	private final PizzaRepository pizzaRepository;
	private final ExecutorService pizzaKitchen;
	private final AtomicLong orderIdSequence = new AtomicLong(0);

	private PizzaService(PizzaFactory sicilianPizzaFactory, PizzaFactory gourmetPizzaFactory,
			IngredientLogger ingredientLogger, PizzaRepository pizzaRepository, int numberOfChefs) {
		this.sicilianPizzaFactory = sicilianPizzaFactory;
		this.gourmetPizzaFactory = gourmetPizzaFactory;
		this.ingredientLogger = ingredientLogger;
		this.pizzaRepository = pizzaRepository;
		this.pizzaKitchen = Executors.newFixedThreadPool(numberOfChefs);
	}

	public Pizza order(MenuItem selectedItem, PizzaStyle selectedStyle) {
		Pizza pizza = chooseFactory(selectedStyle).createPizza(selectedItem, orderIdSequence.incrementAndGet());
		// speicher die Pizza im Repository
		this.pizzaRepository.saveOrUpdate(pizza);
		
		LOGGER.info("Received new order for {} {}", selectedStyle.getName(), pizza.name());
		/*
		 * Pizza wird asynchron in der Pizzaküche fertiggestellt. Klinke hier das
		 * Observer-Pattern ein, um die Änderungen im PizzaRepository zu speichern.
		 */
		pizzaKitchen.submit(new PizzaPreparationTask(pizza, ingredientLogger));

		return pizza;
	}

	PizzaFactory chooseFactory(PizzaStyle selectedStyle) {
		PizzaFactory factory = null;
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
		this.pizzaKitchen.shutdown();
	}

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder für {@code PizzaService}
	 */
	public static final class Builder {

		private PizzaFactory sicilianFactory;
		private PizzaFactory gourmetFactory;
		private IngredientLogger ingredientLogger;
		private PizzaRepository pizzaRepository;
		private int numberOfChefs = 1;

		private Builder() {
			super();
		}

		public Builder sicilianFactory(PizzaFactory sicilianFactory) {
			this.sicilianFactory = sicilianFactory;
			return this;
		}

		public Builder gourmetFactory(PizzaFactory gourmetFactory) {
			this.gourmetFactory = gourmetFactory;
			return this;
		}

		public Builder ingredientLogger(IngredientLogger ingredientLogger) {
			this.ingredientLogger = ingredientLogger;
			return this;
		}

		public Builder pizzaRepository(PizzaRepository pizzaRepository) {
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

		public PizzaService build() {
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
			return new PizzaService(sicilianFactory, gourmetFactory, ingredientLogger, pizzaRepository, numberOfChefs);
		}
	}
}

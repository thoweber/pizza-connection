package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import de.infoteam.course.dp.pizzastore.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodOrderService implements Subscriber<DishStateChange> {

  private static final Logger LOGGER = LoggerFactory.getLogger(FoodOrderService.class);

  private final FoodFactory sicilianFoodFactory;
  private final FoodFactory gourmetFoodFactory;
  private final IngredientLogger ingredientLogger;
  private final DishRepository dishRepository;
  private final ExecutorService kitchen;
  private final AtomicLong orderIdSequence = new AtomicLong(0);

  private FoodOrderService(
      FoodFactory sicilianFoodFactory,
      FoodFactory gourmetFoodFactory,
      IngredientLogger ingredientLogger,
      DishRepository dishRepository,
      int numberOfChefs) {
    this.sicilianFoodFactory = sicilianFoodFactory;
    this.gourmetFoodFactory = gourmetFoodFactory;
    this.ingredientLogger = ingredientLogger;
    this.dishRepository = dishRepository;
    this.kitchen = Executors.newFixedThreadPool(numberOfChefs);
  }

  public Dish order(MenuItem selectedItem, FoodStyle selectedStyle) {
    Dish dish = chooseFactory(selectedStyle).createDish(selectedItem, orderIdSequence.incrementAndGet());
    dishRepository.saveOrUpdate(dish);

    LOGGER.info("Received new order for {}", dish.name());

    FoodPreparationTask task =	new FoodPreparationTask(dish, ingredientLogger);
    task.subscribe(this);
    kitchen.submit(task);
    return dish;
  }

  FoodFactory chooseFactory(FoodStyle selectedStyle) {
    return switch (selectedStyle) {
      case SICILIAN -> this.sicilianFoodFactory;
      case GOURMET -> this.gourmetFoodFactory;
    };
  }

  @Override
  public void update(DishStateChange context) {
    LOGGER.info("Receiving update {} {}", context.getDishId(), context.getState());
    this.dishRepository.findById(context.getDishId()).ifPresent(dish -> {
      dish.updateState(context.getState());
      this.dishRepository.saveOrUpdate(dish);
    });
  }

  public void shutdown() {
    LOGGER.info("The kitchen is closing now. Dishes in progress will be finished though...");
    kitchen.shutdown();
    try {
      if (!kitchen.awaitTermination(300, TimeUnit.SECONDS)) {
        kitchen.shutdownNow();
      }
    } catch (InterruptedException ex) {
      kitchen.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Builder für {@code PizzaService} */
  public static final class Builder {

    private FoodFactory sicilianFactory;
    private FoodFactory gourmetFactory;
    private DishRepository dishRepository;
    private IngredientLogger ingredientLogger;
    private int numberOfChefs = 1; // Builder default value

    /*
     * sicherstellen, dass der Builder nur über die statische Methode in {@code
     * PizzaService} erzeugt wird.
     */
    private Builder() {
      super();
    }

    public Builder sicilianFactory(SicilianFoodFactory sicilianFactory) {
      this.sicilianFactory = sicilianFactory;
      return this;
    }

    public Builder gourmetFactory(GourmetFoodFactory gourmetFactory) {
      this.gourmetFactory = gourmetFactory;
      return this;
    }

    public Builder ingredientLogger(IngredientLogger ingredientLogger) {
      this.ingredientLogger = ingredientLogger;
      return this;
    }

    public Builder pizzaRepository(DishRepository dishRepository) {
      this.dishRepository = dishRepository;
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
        throw new IllegalStateException("A Sicilian FoodFactory is required");
      }
      if (this.gourmetFactory == null) {
        throw new IllegalStateException("A Gourmet FoodFactory is required");
      }
      if (this.ingredientLogger == null) {
        throw new IllegalStateException("An IngredientLogger is required");
      }
      if (this.dishRepository == null) {
        throw new IllegalStateException("A DishRepository is required");
      }
      return new FoodOrderService(sicilianFactory, gourmetFactory, ingredientLogger, dishRepository, numberOfChefs);
    }
  }
}

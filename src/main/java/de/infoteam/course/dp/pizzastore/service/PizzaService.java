package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import de.infoteam.course.dp.pizzastore.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

  private final PizzaFactory sicilianPizzaFactory;
  private final PizzaFactory gourmetPizzaFactory;
  private final IngredientLogger ingredientLogger;
  private final PizzaRepository pizzaRepository;
  private final ExecutorService pizzaKitchen;
  private final AtomicLong orderIdSequence = new AtomicLong(0);

  private PizzaService(
      PizzaFactory sicilianPizzaFactory,
      PizzaFactory gourmetPizzaFactory,
      IngredientLogger ingredientLogger,
      PizzaRepository pizzaRepository,
      int numberOfChefs) {
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

    LOGGER.info("Received new order for {}", pizza.name());
    /*
     * Pizza wird asynchron in der Pizzaküche fertiggestellt. Klinke hier einen
     * Observer ein, der die Nachrichten für die REST-Schnittstellen entgegennimmt.
     */
    pizzaKitchen.submit(new PizzaPreparationTask(pizza, ingredientLogger));
    return pizza;
  }

  PizzaFactory chooseFactory(PizzaStyle selectedStyle) {
    return switch (selectedStyle) {
      case SICILIAN -> this.sicilianPizzaFactory;
      case GOURMET -> this.gourmetPizzaFactory;
    };
  }

  public void shutdown() {
    LOGGER.info("The PizzaKitchen is closing now. Pizza in progress will be finished though...");
    this.pizzaKitchen.shutdown();
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Builder für {@code PizzaService} */
  public static final class Builder {

    private PizzaFactory sicilianFactory;
    private PizzaFactory gourmetFactory;
    private PizzaRepository pizzaRepository;
    private IngredientLogger ingredientLogger;
    private int numberOfChefs = 1; // Builder default value

    /*
     * sicherstellen, dass der Builder nur über die statische Methode in {@code
     * PizzaService} erzeugt wird.
     */
    private Builder() {
      super();
    }

    public Builder sicilianFactory(SicilianPizzaFactory sicilianFactory) {
      this.sicilianFactory = sicilianFactory;
      return this;
    }

    public Builder gourmetFactory(GourmetPizzaFactory gourmetFactory) {
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

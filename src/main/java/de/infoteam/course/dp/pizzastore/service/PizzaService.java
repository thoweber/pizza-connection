package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

  private final PizzaFactory sicilianPizzaFactory;
  private final PizzaFactory gourmetPizzaFactory;

	private PizzaService(PizzaFactory sicilianPizzaFactory, PizzaFactory gourmetPizzaFactory) {
		this.sicilianPizzaFactory = sicilianPizzaFactory;
		this.gourmetPizzaFactory = gourmetPizzaFactory;
	}

  public Pizza order(MenuItem selectedItem, PizzaStyle selectedStyle) {
    var pizza = chooseFactory(selectedStyle).createPizza(selectedItem);

    LOGGER.info("Received new order for {}", pizza.name());
    preparePizza(pizza);
    bakePizza(pizza);
    servePizza(pizza);
    return pizza;
  }

  private PizzaFactory chooseFactory(PizzaStyle selectedStyle) {
    return switch (selectedStyle) {
      case SICILIAN -> this.sicilianPizzaFactory;
      case GOURMET -> this.gourmetPizzaFactory;
    };
  }

  void preparePizza(Pizza pizza) {
    pizza.addIngredients();

    // output ingredients to log
    var ingredients =
        pizza.getIngredients().stream().map(Ingredient::name).collect(Collectors.joining(", "));
    LOGGER.info(" > adding ingredients: {}", ingredients);
  }

  void bakePizza(Pizza pizza) {
    // output baking procedure to log
    LOGGER.info(
        " > baking for {} minutes at {}° Celsius",
        pizza.getBakingDuration().toMinutes(),
        pizza.getBakingTemperature());
  }

	void servePizza(Pizza pizza) {
		// output serving to log
    LOGGER.info(" > serving {}...", pizza.name());
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

		public PizzaService build() {
			if (this.sicilianFactory == null) {
				throw new IllegalStateException("A Sicilian PizzaFactory is required");
			}
			if (this.gourmetFactory == null) {
				throw new IllegalStateException("A Gourmet PizzaFactory is required");
			}
			return new PizzaService(sicilianFactory, gourmetFactory);
		}
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

		public PizzaService build() {
			if (this.sicilianFactory == null) {
				throw new IllegalStateException("A Sicilian PizzaFactory is required");
			}
			if (this.gourmetFactory == null) {
				throw new IllegalStateException("A Gourmet PizzaFactory is required");
			}
			return new PizzaService(sicilianFactory, gourmetFactory);
		}
	}

}

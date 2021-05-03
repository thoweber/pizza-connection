package de.infoteam.course.dp.pizzastore.service;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;

public class PizzaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

	private final PizzaFactory sicilianPizzaFactory;
	private final PizzaFactory gourmetPizzaFactory;
	private final IngredientLogger ingredientLogger;
	private final AtomicLong orderIdSequence = new AtomicLong(0);

	private PizzaService(PizzaFactory sicilianPizzaFactory, PizzaFactory gourmetPizzaFactory,
			IngredientLogger ingredientLogger) {
		this.sicilianPizzaFactory = sicilianPizzaFactory;
		this.gourmetPizzaFactory = gourmetPizzaFactory;
		this.ingredientLogger = ingredientLogger;
	}

	public Pizza order(MenuItem selectedItem, PizzaStyle selectedStyle) {
		Pizza pizza = chooseFactory(selectedStyle).createPizza(selectedItem, orderIdSequence.incrementAndGet());
		LOGGER.info("Received new order for {} {}", selectedStyle.getName(), pizza.name());
		/*
		 * Diese Funktion wird ausgelagert
		 */
		preparePizza(pizza);
		logConsumedIngredients(pizza);
		bakePizza(pizza);
		servePizza(pizza);
		return pizza;
	}

	private void logConsumedIngredients(Pizza pizza) {
		pizza.getIngredients().forEach(this.ingredientLogger::logIngredient);
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

	void preparePizza(Pizza pizza) {
		pizza.addIngredients();

		// output ingredients to log
		StringJoiner joiner = new StringJoiner(", ");
		pizza.getIngredients().stream().map(Ingredient::name).forEach(joiner::add);
		LOGGER.info(" > adding ingredients: {}", joiner);
	}

	void bakePizza(Pizza pizza) {
		// output baking procedure to log
		LOGGER.info(" > baking for {} minutes at {}° Celsius", pizza.getBakingDuration().toMinutes(),
				pizza.getBakingTemperature());
	}

	void servePizza(Pizza pizza) {
		// output serving to log
		LOGGER.info(" > serving...");
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
			return new PizzaService(sicilianFactory, gourmetFactory, ingredientLogger);
		}
	}
}

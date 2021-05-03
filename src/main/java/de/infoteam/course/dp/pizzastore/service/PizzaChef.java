package de.infoteam.course.dp.pizzastore.service;

import java.time.Duration;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;

public class PizzaChef implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaChef.class);

	private Pizza pizza;
	private IngredientLogger ingredientLogger;

	private boolean simulateProgress = true;

	public PizzaChef(Pizza pizza, IngredientLogger ingredientLogger) {
		this.pizza = pizza;
		this.ingredientLogger = ingredientLogger;
	}

	PizzaChef(Pizza pizza, IngredientLogger ingredientLogger, boolean simulateProgress) {
		this(pizza, ingredientLogger);
		this.simulateProgress = simulateProgress;
	}

	@Override
	public void run() {
		preparePizza(pizza);
		logConsumedIngredients(pizza);
		bakePizza(pizza);
		servePizza(pizza);
	}

	void preparePizza(Pizza pizza) {
		pizza.addIngredients();

		// output ingredients to log
		StringJoiner joiner = new StringJoiner(", ");
		pizza.getIngredients().stream().map(Ingredient::name).forEach(joiner::add);
		LOGGER.info(" > adding ingredients: {}", joiner);

		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(5));
		}

		/*
		 * Hier Observer benachrichtigen
		 */
	}

	void bakePizza(Pizza pizza) {
		// output baking procedure to log
		LOGGER.info(" > baking for {} minutes at {}° Celsius", pizza.getBakingDuration().toMinutes(),
				pizza.getBakingTemperature());
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(pizza.getBakingDuration().toMinutes() * 3));
		}

		/*
		 * Hier Observer benachrichtigen
		 */
	}

	void servePizza(Pizza pizza) {
		// output serving to log
		LOGGER.info(" > serving...");
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(1));
		}

		/*
		 * Hier Observer benachrichtigen
		 */
	}

	void logConsumedIngredients(Pizza pizza) {
		pizza.getIngredients().forEach(this.ingredientLogger::logIngredient);
	}

	private void sleep(Duration duration) {
		try {
			Thread.sleep(duration.toMillis());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.info("The PizzaChef has been interrupted while doing important work!");
		}
	}

}

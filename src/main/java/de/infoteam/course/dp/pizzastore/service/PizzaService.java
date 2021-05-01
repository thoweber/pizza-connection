package de.infoteam.course.dp.pizzastore.service;

import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;

public class PizzaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

	private final PizzaFactory pizzaFactory;

	public PizzaService(PizzaFactory pizzaFactory) {
		this.pizzaFactory = pizzaFactory;
	}

	public Pizza order(MenuItem selectedItem) {
		Pizza pizza = pizzaFactory.createPizza(selectedItem);
		LOGGER.info("Received new order for {}", pizza.name());
		preparePizza(pizza);
		bakePizza(pizza);
		servePizza(pizza);
		return pizza;
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

}
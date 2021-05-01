package de.infoteam.course.dp.pizzastore.model;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.dishes.DefaultPizza;

public class PizzaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

	public Pizza order(Pizza pizza) {
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

	public List<Pizza> getDishes() {
		return Arrays.asList(new DefaultPizza());
	}

}

package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

	private final PizzaFactory sicilianPizzaFactory;
	private final PizzaFactory gourmetPizzaFactory;

	public PizzaService(PizzaFactory sicilianPizzaFactory, PizzaFactory gourmetPizzaFactory) {
		this.sicilianPizzaFactory = sicilianPizzaFactory;
		this.gourmetPizzaFactory = gourmetPizzaFactory;
	}

	public Pizza order(MenuItem selectedItem, PizzaStyle selectedStyle) {
		Pizza pizza = chooseFactory(selectedStyle).createPizza(selectedItem);;
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

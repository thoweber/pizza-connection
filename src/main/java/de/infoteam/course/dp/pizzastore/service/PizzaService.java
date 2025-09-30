package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import java.util.StringJoiner;
import org.slf4j.Logger;
import java.util.stream.Collectors;
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
		/*
		 * Richtige Factory für bestellte Pizza auswählen und Pizza erzeugen
		 */
		Pizza pizza = null;

		LOGGER.info("Received new order for {}", pizza.name());
		preparePizza(pizza);
		bakePizza(pizza);
		servePizza(pizza);
		return pizza;
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
}

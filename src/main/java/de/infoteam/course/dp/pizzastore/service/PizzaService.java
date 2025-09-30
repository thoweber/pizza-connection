package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.DefaultPizza;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

  public Pizza order() {
    var pizza = new DefaultPizza();
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

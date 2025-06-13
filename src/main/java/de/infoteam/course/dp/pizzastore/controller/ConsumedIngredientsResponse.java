package de.infoteam.course.dp.pizzastore.controller;

import de.infoteam.course.dp.pizzastore.model.ConsumedIngredients;
import java.util.Map;

public record ConsumedIngredientsResponse(
    Map<String, Integer> dough,
    Map<String, Integer> sauce,
    Map<String, Integer> cheese,
    Map<String, Integer> toppings)
    implements ConsumedIngredients {

  public static ConsumedIngredientsResponse of(ConsumedIngredients cio) {
    return new ConsumedIngredientsResponse(
        Map.copyOf(cio.dough()),
        Map.copyOf(cio.sauce()),
        Map.copyOf(cio.cheese()),
        Map.copyOf(cio.toppings()));
  }
}

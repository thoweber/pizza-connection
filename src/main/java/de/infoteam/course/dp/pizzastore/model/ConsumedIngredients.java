package de.infoteam.course.dp.pizzastore.model;

import java.util.Map;

public interface ConsumedIngredients {

    Map<String, Integer> dough();
    Map<String, Integer> sauce();
    Map<String, Integer> cheese();
    Map<String, Integer> toppings();

}

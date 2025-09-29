package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.ConsumedIngredients;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.Cheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.Topping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IngredientLoggerAdapter implements ConsumedIngredients {

    private Map<String, Integer> consumedDough = new HashMap<>();
    private Map<String, Integer> consumedSauce = new HashMap<>();
    private Map<String, Integer> consumedCheese = new HashMap<>();
    private Map<String, Integer> consumedToppings = new HashMap<>();

    public IngredientLoggerAdapter(IngredientLogger ingredientLogger) {
        ingredientLogger.getConsumedIngredients().stream().filter(Dough.class::isInstance).map(Ingredient::name)
                .forEach(name -> this.consumedDough.merge(name, 1, Integer::sum));
        ingredientLogger.getConsumedIngredients().stream().filter(Sauce.class::isInstance).map(Ingredient::name)
                .forEach(name -> this.consumedSauce.merge(name, 1, Integer::sum));
        ingredientLogger.getConsumedIngredients().stream().filter(Cheese.class::isInstance).map(Ingredient::name)
                .forEach(name -> this.consumedCheese.merge(name, 1, Integer::sum));
        ingredientLogger.getConsumedIngredients().stream().filter(Topping.class::isInstance).map(Ingredient::name)
                .forEach(name -> this.consumedToppings.merge(name, 1, Integer::sum));
    }

    @Override
    public Map<String, Integer> dough() {
        return Collections.unmodifiableMap(consumedDough);
    }

    @Override
    public Map<String, Integer> sauce() {
        return Collections.unmodifiableMap(consumedSauce);
    }

    @Override
    public Map<String, Integer> cheese() {
        return Collections.unmodifiableMap(consumedCheese);
    }

    @Override
    public Map<String, Integer> toppings() {
        return Collections.unmodifiableMap(consumedToppings);
    }
}

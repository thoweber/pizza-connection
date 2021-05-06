package de.infoteam.course.dp.pizzastore.service.handler;

import de.infoteam.course.dp.pizzastore.service.DishHandler;
import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Publisher;

public class LogConsumedIngredients extends DishHandler {
    private IngredientLogger ingredientLogger;

    public LogConsumedIngredients(IngredientLogger ingredientLogger)
    {
        this.ingredientLogger = ingredientLogger;
    }

    @Override
    public void doHandle(Dish dish, Publisher<DishStateChange> publisher)
    {
        dish.getIngredients().forEach(this.ingredientLogger::logIngredient);
    }
}

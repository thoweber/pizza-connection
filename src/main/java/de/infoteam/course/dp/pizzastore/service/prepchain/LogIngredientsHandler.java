package de.infoteam.course.dp.pizzastore.service.prepchain;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;

public class LogIngredientsHandler extends AbstractObservableDishHandler {

	private final IngredientLogger ingredientLogger;

	public LogIngredientsHandler(IngredientLogger ingredientLogger, boolean simulateProgress) {
		super(simulateProgress);
		this.ingredientLogger = ingredientLogger;
	}

	@Override
	protected boolean canHandle(Dish dish) {
		// logging geht auch immer
		return true;
	}

	@Override
	protected void doHandle(Dish dish) {
		dish.getIngredients().forEach(this.ingredientLogger::logIngredient);

	}

}

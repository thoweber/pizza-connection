package de.infoteam.course.dp.pizzastore.service.prepchain;

import java.time.Duration;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.State;

public class FoodPreparationHandler extends AbstractObservableDishHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodPreparationHandler.class);

	public FoodPreparationHandler(boolean simulateProgess) {
		super(simulateProgess);
	}

	@Override
	protected boolean canHandle(Dish dish) {
		// vorbereiten geht immer ;-)
		return true;
	}

	@Override
	protected void doHandle(Dish dish) {
		dish.updateState(State.IN_PREPARATION);
		notifySubscribers(dish);

		dish.addIngredients();

		// output ingredients to log
		StringJoiner joiner = new StringJoiner(", ");
		dish.getIngredients().stream().map(Ingredient::name).forEach(joiner::add);
		LOGGER.info(" > adding ingredients: {}", joiner);

		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(5));
		}

		getNext().handle(dish);
	}

}

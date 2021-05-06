package de.infoteam.course.dp.pizzastore.service.handler;

import java.time.Duration;
import java.util.StringJoiner;

import de.infoteam.course.dp.pizzastore.service.DishHandler;
import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Publisher;

public class PrepareDishes extends DishHandler {

    @Override
    public void doHandle(Dish dish, Publisher<DishStateChange> publisher) {
        dish.updateState(State.IN_PREPARATION);
		publisher.notifySubscribers();

		dish.addIngredients();

		// output ingredients to log
		StringJoiner joiner = new StringJoiner(", ");
		dish.getIngredients().stream().map(Ingredient::name).forEach(joiner::add);
		LOGGER.info(" > adding ingredients: {}", joiner);
			
        sleep(Duration.ofSeconds(5));
    }
}

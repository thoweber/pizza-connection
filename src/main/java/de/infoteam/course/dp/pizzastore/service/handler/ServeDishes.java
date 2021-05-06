package de.infoteam.course.dp.pizzastore.service.handler;

import java.time.Duration;

import de.infoteam.course.dp.pizzastore.service.DishHandler;
import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Publisher;

public class ServeDishes extends DishHandler{

    @Override
    public void doHandle(Dish dish, Publisher<DishStateChange> publisher)
    {
        dish.updateState(State.DISH_UP);
		publisher.notifySubscribers();
		// output serving to log
		LOGGER.info(" > serving...");
		// sleep
		sleep(Duration.ofSeconds(1));

		dish.updateState(State.READY);
		publisher.notifySubscribers();

    }
}

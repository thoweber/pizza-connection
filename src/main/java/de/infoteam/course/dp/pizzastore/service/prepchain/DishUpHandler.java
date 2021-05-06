package de.infoteam.course.dp.pizzastore.service.prepchain;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;

public class DishUpHandler extends AbstractObservableDishHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DishUpHandler.class);

	public DishUpHandler(boolean simulateProgess) {
		super(simulateProgess);
	}

	@Override
	protected boolean canHandle(Dish dish) {
		// anrichten geht auch immer
		return true;
	}

	@Override
	protected void doHandle(Dish dish) {
		dish.updateState(State.DISH_UP);
		// output serving to log
		LOGGER.info(" > dishing up...");
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(1));
		}
	}

}

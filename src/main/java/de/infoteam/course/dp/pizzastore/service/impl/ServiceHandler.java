package de.infoteam.course.dp.pizzastore.service.impl;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.service.prepchain.AbstractObservableDishHandler;

public class ServiceHandler extends AbstractObservableDishHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHandler.class);

	public ServiceHandler(boolean simulateProgess) {
		super(simulateProgess);
	}

	@Override
	protected boolean canHandle(Dish dish) {
		// servieren geht auch immer
		return true;
	}

	@Override
	protected void doHandle(Dish dish) {
		dish.updateState(State.DISH_UP);
		notifySubscribers(dish);
		// output serving to log
		LOGGER.info(" > serving...");
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(1));
		}
		dish.updateState(State.READY);
		notifySubscribers(dish);
	}

}

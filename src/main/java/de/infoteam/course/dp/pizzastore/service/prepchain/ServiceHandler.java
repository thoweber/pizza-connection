package de.infoteam.course.dp.pizzastore.service.prepchain;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;

public class ServiceHandler extends AbstractDishHandler {
	
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
		// output serving to log
		LOGGER.info(" > serving...");
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(1));
		}
		dish.updateState(State.READY);
	}

}

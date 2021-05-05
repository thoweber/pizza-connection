package de.infoteam.course.dp.pizzastore.service.prepchain;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;

public class BakingHandler extends AbstractObservableDishHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BakingHandler.class);

	public BakingHandler(boolean simulateProgess) {
		super(simulateProgess);
	}

	@Override
	protected boolean canHandle(Dish dish) {
		return dish instanceof Pizza;
	}

	@Override
	protected void doHandle(Dish dish) {
		Pizza pizza = (Pizza) dish;
		pizza.updateState(State.IN_OVEN);
		notifySubscribers(pizza);
		// output baking procedure to log
		LOGGER.info(" > baking for {} minutes at {}° Celsius", pizza.getBakingDuration().toMinutes(),
				pizza.getBakingTemperature());
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(pizza.getBakingDuration().toMinutes() * 3));
		}
	}

}

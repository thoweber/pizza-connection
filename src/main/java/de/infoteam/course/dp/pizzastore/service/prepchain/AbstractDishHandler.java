package de.infoteam.course.dp.pizzastore.service.prepchain;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.service.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.Handler;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

import de.infoteam.course.dp.pizzastore.service.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDishHandler implements Handler<Dish, AbstractDishHandler, DishStateChange> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDishHandler.class);
	private AbstractDishHandler next = null;
	protected boolean simulateProgress;

	protected AbstractDishHandler(boolean simulateProgess) {
		this.simulateProgress = simulateProgess;
	}

	protected boolean isNotifying() {
		return true;
	}

	@Override
	public final AbstractDishHandler setNext(AbstractDishHandler nextHandler) {
		next = nextHandler;
		return next;
	}

	@Override
	public final void handle(Dish dish, Set<Subscriber<DishStateChange>> subscribers) {
		if (canHandle(dish)) {
			doHandle(dish);
			if (isNotifying()) {
			    notifySubscribers(dish, subscribers);
			}
		}
		Optional.ofNullable(next).ifPresent(n -> n.handle(dish, subscribers));
	}

	protected abstract boolean canHandle(Dish dish);

	protected abstract void doHandle(Dish dish);

	public final void notifySubscribers(Dish dish, Set<Subscriber<DishStateChange>> subscribers) {
		var nextState = DishStateChange.of(dish);
		subscribers.forEach(s -> s.update(nextState));
	}

	protected void sleep(Duration duration) {
		try {
			Thread.sleep(duration.toMillis());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.info("The {} has been interrupted while doing important work!", getClass().getSimpleName());
		}
	}

}

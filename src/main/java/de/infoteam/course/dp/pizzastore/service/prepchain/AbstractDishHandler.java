package de.infoteam.course.dp.pizzastore.service.prepchain;

import java.time.Duration;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.service.Handler;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Subscriber;

public abstract class AbstractDishHandler
		implements Handler<Dish, AbstractDishHandler, DishStateChange> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDishHandler.class);
	private AbstractDishHandler next;
	protected boolean simulateProgress;

	protected AbstractDishHandler(boolean simulateProgess) {
		this.simulateProgress = simulateProgess;
	}

	@Override
	public final void setNext(AbstractDishHandler nextHandler) {
		this.next = nextHandler;
	}

	protected final Handler<Dish, AbstractDishHandler, DishStateChange> getNext() {
		return this.next;
	}

	@Override
	public final void handle(Dish dish, Set<Subscriber<DishStateChange>> subscribers) {
		if (canHandle(dish)) {
			doHandle(dish);
			notifySubscribers(dish, subscribers);
		}
		this.next.handle(dish, subscribers);
	}

	protected abstract boolean canHandle(Dish dish);

	protected abstract void doHandle(Dish dish);


	public final void notifySubscribers(Dish dish, Set<Subscriber<DishStateChange>> subscribers) {
		final DishStateChange nextState = DishStateChange.of(dish);
		subscribers.stream().forEach(s -> s.update(nextState));
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

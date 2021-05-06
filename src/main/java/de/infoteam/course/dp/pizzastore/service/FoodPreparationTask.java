package de.infoteam.course.dp.pizzastore.service;

import java.time.Duration;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Publisher;
import de.infoteam.course.dp.pizzastore.service.model.Subscriber;

import de.infoteam.course.dp.pizzastore.service.DishHandler;
import de.infoteam.course.dp.pizzastore.service.handler.BakeDishes;
import de.infoteam.course.dp.pizzastore.service.handler.LogConsumedIngredients;
import de.infoteam.course.dp.pizzastore.service.handler.PrepareDishes;
import de.infoteam.course.dp.pizzastore.service.handler.ServeDishes;
public class FoodPreparationTask implements Runnable, Publisher<DishStateChange> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodPreparationTask.class);

	private Dish dish;
	private IngredientLogger ingredientLogger;
	private DishHandler dishHandler;

	private boolean simulateProgress = true;

	private Set<Subscriber<DishStateChange>> subscribers = new CopyOnWriteArraySet<>();

	public FoodPreparationTask(Dish dish, IngredientLogger ingredientLogger) {
		this.dish = dish;
		this.ingredientLogger = ingredientLogger;
		handlerChain();
	}

	FoodPreparationTask(Pizza pizza, IngredientLogger ingredientLogger, boolean simulateProgress) {
		this(pizza, ingredientLogger);
		this.simulateProgress = simulateProgress;
	}


	@Override
	public void run() {
		makeDish();
	}

	private void handlerChain()
	{
		this.dishHandler = new PrepareDishes().setNext(new LogConsumedIngredients(ingredientLogger).setNext(new BakeDishes().setNext(new ServeDishes())));
	}

	private void makeDish()
	{
		this.dishHandler.handle(this.dish, this);
	}

	@Override
	public void subscribe(Subscriber<DishStateChange> subscriber) {
		this.subscribers.add(subscriber);
	}

	@Override
	public void unsubscribe(Subscriber<DishStateChange> subscriber) {
		this.subscribers.remove(subscriber);
	}

	@Override
	public void notifySubscribers() {
		final DishStateChange next = DishStateChange.of(this.dish);
		this.subscribers.stream().forEach(s -> s.update(next));
		// clear all subscriptions when task is done
		if (next.getState() == State.READY) {
			this.subscribers.clear();
		}
	}

}

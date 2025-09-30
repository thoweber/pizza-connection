package de.infoteam.course.dp.pizzastore.service;

import java.time.Duration;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArraySet;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.service.prepchain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;

public class FoodPreparationTask implements Runnable, Publisher<DishStateChange> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodPreparationTask.class);

	private final Dish dish;
	private final IngredientLogger ingredientLogger;

	private boolean simulateProgress = true;

	private Set<Subscriber<DishStateChange>> subscribers = new CopyOnWriteArraySet<>();

	public FoodPreparationTask(Dish dish, IngredientLogger ingredientLogger) {
		this.dish = dish;
		this.ingredientLogger = ingredientLogger;
	}

	FoodPreparationTask(Dish dish, IngredientLogger ingredientLogger, boolean simulateProgress) {
		this(dish, ingredientLogger);
		this.simulateProgress = simulateProgress;
	}

	@Override
	public void run() {
		var chain = new FoodPreparationHandler(simulateProgress);
		chain.setNext(new LogIngredientsHandler(ingredientLogger, simulateProgress))
				.setNext(new BakingHandler(simulateProgress))
				.setNext(new DishUpHandler(simulateProgress))
				.setNext(new ServiceHandler(simulateProgress));
		chain.handle(dish, subscribers);
	}

	@Override
	public void subscribe(Subscriber<DishStateChange> subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void unsubscribe(Subscriber<DishStateChange> subscriber) {
		subscribers.remove(subscriber);
	}

}

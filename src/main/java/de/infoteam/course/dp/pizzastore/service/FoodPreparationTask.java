package de.infoteam.course.dp.pizzastore.service;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Publisher;
import de.infoteam.course.dp.pizzastore.service.model.Subscriber;
import de.infoteam.course.dp.pizzastore.service.prepchain.AbstractDishHandler;
import de.infoteam.course.dp.pizzastore.service.prepchain.BakingHandler;
import de.infoteam.course.dp.pizzastore.service.prepchain.DishUpHandler;
import de.infoteam.course.dp.pizzastore.service.prepchain.FoodPreparationHandler;
import de.infoteam.course.dp.pizzastore.service.prepchain.LogIngredientsHandler;
import de.infoteam.course.dp.pizzastore.service.prepchain.ServiceHandler;

public class FoodPreparationTask implements Runnable, Publisher<DishStateChange> {

	private Dish dish;
	private IngredientLogger ingredientLogger;

	private boolean simulateProgress = true;

	private Set<Subscriber<DishStateChange>> subscribers = new CopyOnWriteArraySet<>();

	public FoodPreparationTask(Dish dish, IngredientLogger ingredientLogger) {
		this.dish = dish;
		this.ingredientLogger = ingredientLogger;
	}

	FoodPreparationTask(Pizza pizza, IngredientLogger ingredientLogger, boolean simulateProgress) {
		this(pizza, ingredientLogger);
		this.simulateProgress = simulateProgress;
	}

	@Override
	public void run() {
		/* prepare handlers */
		AbstractDishHandler prepHandler = new FoodPreparationHandler(simulateProgress);
		AbstractDishHandler logHandler = new LogIngredientsHandler(this.ingredientLogger, simulateProgress);
		AbstractDishHandler bakingHandler = new BakingHandler(simulateProgress);
		AbstractDishHandler dishUpHandler = new DishUpHandler(simulateProgress);
		AbstractDishHandler serviceHandler = new ServiceHandler(simulateProgress);

		/* set-up chain */
		prepHandler.setNext(logHandler);
		logHandler.setNext(bakingHandler);
		bakingHandler.setNext(dishUpHandler);
		dishUpHandler.setNext(serviceHandler);
		prepHandler.handle(dish, subscribers);
	}

	@Override
	public void subscribe(Subscriber<DishStateChange> subscriber) {
		this.subscribers.add(subscriber);
	}

	@Override
	public void unsubscribe(Subscriber<DishStateChange> subscriber) {
		this.subscribers.remove(subscriber);
	}

}

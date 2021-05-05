package de.infoteam.course.dp.pizzastore.service;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Publisher;
import de.infoteam.course.dp.pizzastore.service.model.Subscriber;
import de.infoteam.course.dp.pizzastore.service.prepchain.AbstractObservableDishHandler;
import de.infoteam.course.dp.pizzastore.service.prepchain.BakingHandler;
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
		/*
		 * Dieser Code ist zu abhängig vom Essensangebot. Wir werden das Chain of
		 * Responsibility Pattern implementieren.
		 */
		AbstractObservableDishHandler prepHandler = new FoodPreparationHandler(simulateProgress);
		AbstractObservableDishHandler logHandler = new LogIngredientsHandler(this.ingredientLogger, simulateProgress);
		AbstractObservableDishHandler bakingHandler = new BakingHandler(simulateProgress);
		AbstractObservableDishHandler serviceHandler = new ServiceHandler(simulateProgress);

		/* set-up chain */
		prepHandler.setNext(logHandler);
		logHandler.setNext(bakingHandler);
		bakingHandler.setNext(serviceHandler);
		prepHandler.handle(dish);
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

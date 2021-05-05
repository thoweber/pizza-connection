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

public class FoodPreparationTask implements Runnable, Publisher<DishStateChange> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodPreparationTask.class);

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
		preparePizza(dish);
		logConsumedIngredients(dish);
		if (dish instanceof Pizza) {
			bakePizza((Pizza) dish);
		}
		servePizza(dish);
	}

	void preparePizza(Dish dish) {
		dish.updateState(State.IN_PREPARATION);
		notifySubscribers();

		dish.addIngredients();

		// output ingredients to log
		StringJoiner joiner = new StringJoiner(", ");
		dish.getIngredients().stream().map(Ingredient::name).forEach(joiner::add);
		LOGGER.info(" > adding ingredients: {}", joiner);

		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(5));
		}
	}

	void bakePizza(Pizza pizza) {
		pizza.updateState(State.IN_OVEN);
		notifySubscribers();
		// output baking procedure to log
		LOGGER.info(" > baking for {} minutes at {}° Celsius", pizza.getBakingDuration().toMinutes(),
				pizza.getBakingTemperature());
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(pizza.getBakingDuration().toMinutes() * 3));
		}
	}

	void servePizza(Dish pizza) {
		pizza.updateState(State.DISH_UP);
		notifySubscribers();
		// output serving to log
		LOGGER.info(" > serving...");
		// sleep
		if (simulateProgress) {
			sleep(Duration.ofSeconds(1));
		}
		pizza.updateState(State.READY);
		notifySubscribers();
	}

	void logConsumedIngredients(Dish pizza) {
		pizza.getIngredients().forEach(this.ingredientLogger::logIngredient);
	}

	private void sleep(Duration duration) {
		try {
			Thread.sleep(duration.toMillis());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.info("The PizzaChef has been interrupted while doing important work!");
		}
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

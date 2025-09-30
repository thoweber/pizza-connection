package de.infoteam.course.dp.pizzastore.service;

import static de.infoteam.course.dp.pizzastore.model.State.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.model.dishes.TomatoSalad;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;

import java.util.ArrayList;
import java.util.List;

import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.ArtichokeTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.OliveTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.TomatoTopping;
import org.junit.jupiter.api.Test;

class FoodPreparationTaskTest {

	private static final class SubscriberTestDouble implements Subscriber<DishStateChange> {

		private final List<State> stateList = new ArrayList<>();

		@Override
		public void update(DishStateChange context) {
			stateList.add(context.getState());
		}

		public List<State> getStateList() {
			return stateList;
		}
	}

	private void assertIngredients(List<Class<? extends Ingredient>> expectedIngredients, IngredientLogger ingredientLogger) {
		var consumedIngredients = ingredientLogger.getConsumedIngredients();
		assertEquals(expectedIngredients.size(), consumedIngredients.size(), "Number of Ingredients do not match");
		for (var i = 0; i < expectedIngredients.size(); i++) {
			assertEquals(expectedIngredients.get(i), consumedIngredients.get(i).getClass(), "Ingredient #" + i + " does not match");
		}
	}

	@Test
	void test_run_changes_states_for_pizza_in_order() {
		// given
		var ingredientLogger = new IngredientLogger();
		var foodPreparationTask = new FoodPreparationTask(new VeggiePizza(1, new ThinCrustyDough(), new PlainTomatoSauce()), ingredientLogger, false);
		var subscriber = new SubscriberTestDouble();
		foodPreparationTask.subscribe(subscriber);
		// when
		foodPreparationTask.run();
		// then
		var states = subscriber.getStateList();
		assertEquals(IN_PREPARATION, states.get(0));
		assertEquals(IN_OVEN, states.get(1));
		assertEquals(DISH_UP, states.get(2));
		assertEquals(READY, states.get(3));

		var expectedIngredients = List.of(ThinCrustyDough.class, PlainTomatoSauce.class, MozzarellaCheese.class,
				TomatoTopping.class, ArtichokeTopping.class, OliveTopping.class);
		assertIngredients(expectedIngredients, ingredientLogger);
	}

	@Test
	void test_run_changes_states_for_salad_in_order() {
		// given
		var ingredientLogger = new IngredientLogger();
    var foodPreparationTask =
        new FoodPreparationTask(
            new TomatoSalad(2, new ArtichokeTopping(), new MozzarellaCheese()),
            ingredientLogger,
            false);
		var subscriber = new SubscriberTestDouble();
		foodPreparationTask.subscribe(subscriber);
		// when
		foodPreparationTask.run();
		// then
		var states = subscriber.getStateList();
		assertEquals(IN_PREPARATION, states.get(0));
		assertEquals(DISH_UP, states.get(1));
		assertEquals(READY, states.get(2));

		var expectedIngredients = List.of(TomatoTopping.class, TomatoTopping.class, TomatoTopping.class,
				ArtichokeTopping.class, MozzarellaCheese.class);
		assertIngredients(expectedIngredients, ingredientLogger);
	}

}

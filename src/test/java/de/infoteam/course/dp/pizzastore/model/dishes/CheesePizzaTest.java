package de.infoteam.course.dp.pizzastore.model.dishes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;

class CheesePizzaTest {

	@Test
	void test_CheesePizza_has_the_correct_ingredients() {
		// given
		CheesePizza pizza = new CheesePizza(new ThinCrustyDough(), new PlainTomatoSauce());
		// when
		pizza.addIngredients();
		// then
		assertNotNull(pizza.getIngredients());
		List<Ingredient> ingredients = pizza.getIngredients();
		assertEquals(3, ingredients.size());
		assertEquals(ThinCrustyDough.class, ingredients.get(0).getClass());
		assertEquals(PlainTomatoSauce.class, ingredients.get(1).getClass());
		assertEquals(MozzarellaCheese.class, ingredients.get(2).getClass());
	}

	@Test
	void test_CheesePizza_has_the_correct_baking_duration() {
		// given
		CheesePizza pizza = new CheesePizza(new ThinCrustyDough(), new PlainTomatoSauce());
		// when
		Duration bakingDuration = pizza.getBakingDuration();
		// then
		assertEquals(6, bakingDuration.toMinutes());
	}

	@Test
	void test_CheesePizza_has_the_correct_baking_temperature() {
		// given
		CheesePizza pizza = new CheesePizza(new ThinCrustyDough(), new PlainTomatoSauce());
		// when
		int temperature = pizza.getBakingTemperature();
		// then
		assertEquals(337, temperature);
	}

}

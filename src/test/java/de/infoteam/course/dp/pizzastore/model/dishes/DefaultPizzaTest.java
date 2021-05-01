package de.infoteam.course.dp.pizzastore.model.dishes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

class DefaultPizzaTest {

	@Test
	void test_DefaultPizza_has_the_correct_ingredients() {
		// given
		DefaultPizza pizza = new DefaultPizza();
		// when
		pizza.addIngredients();
		// then
		assertNotNull(pizza.getIngredients());
		List<Ingredient> ingredients = pizza.getIngredients();
		assertEquals(3, ingredients.size());
		assertEquals("thin crusty dough", ingredients.get(0).name());
		assertEquals("plain tomato sauce", ingredients.get(1).name());
		assertEquals("mozarella", ingredients.get(2).name());
	}

	@Test
	void test_DefaultPizza_has_the_correct_baking_duration() {
		// given
		DefaultPizza pizza = new DefaultPizza();
		// when
		Duration bakingDuration = pizza.getBakingDuration();
		// then
		assertEquals(6, bakingDuration.toMinutes());
	}
	
	@Test
	void test_DefaultPizza_has_the_correct_baking_temperature() {
		// given
		DefaultPizza pizza = new DefaultPizza();
		// when
		int temperature = pizza.getBakingTemperature();
		// then
		assertEquals(337, temperature);
	}

}

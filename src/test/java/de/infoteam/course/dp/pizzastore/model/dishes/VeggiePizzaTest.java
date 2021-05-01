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
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.ArtichokeTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.OliveTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.TomatoTopping;

class VeggiePizzaTest {

	@Test
	void test_VeggiePizza_has_the_correct_ingredients() {
		// given
		VeggiePizza pizza = new VeggiePizza();
		// when
		pizza.addIngredients();
		// then
		assertNotNull(pizza.getIngredients());
		List<Ingredient> ingredients = pizza.getIngredients();
		assertEquals(6, ingredients.size());
		assertEquals(ThinCrustyDough.class, ingredients.get(0).getClass());
		assertEquals(PlainTomatoSauce.class, ingredients.get(1).getClass());
		assertEquals(MozzarellaCheese.class, ingredients.get(2).getClass());
		assertEquals(TomatoTopping.class, ingredients.get(3).getClass());
		assertEquals(ArtichokeTopping.class, ingredients.get(4).getClass());
		assertEquals(OliveTopping.class, ingredients.get(5).getClass());
	}

	@Test
	void test_VeggiePizza_has_the_correct_baking_duration() {
		// given
		VeggiePizza pizza = new VeggiePizza();
		// when
		Duration bakingDuration = pizza.getBakingDuration();
		// then
		assertEquals(8, bakingDuration.toMinutes());
	}
	
	@Test
	void test_VeggiePizza_has_the_correct_baking_temperature() {
		// given
		VeggiePizza pizza = new VeggiePizza();
		// when
		int temperature = pizza.getBakingTemperature();
		// then
		assertEquals(300, temperature);
	}

}

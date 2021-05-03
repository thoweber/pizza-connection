package de.infoteam.course.dp.pizzastore.model.dishes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.PepperoniTopping;

class PepperoniPizzaTest {

	@Test
	void test_PepperoniPizza_has_the_correct_ingredients() {
		// given
		PepperoniPizza pizza = new PepperoniPizza(2, new ThinCrustyDough(), new PlainTomatoSauce());
		// when
		pizza.addIngredients();
		// then
		assertNotNull(pizza.getIngredients());
		List<Ingredient> ingredients = pizza.getIngredients();
		assertEquals(4, ingredients.size());
		assertEquals(ThinCrustyDough.class, ingredients.get(0).getClass());
		assertEquals(PlainTomatoSauce.class, ingredients.get(1).getClass());
		assertEquals(MontereyJackCheese.class, ingredients.get(2).getClass());
		assertEquals(PepperoniTopping.class, ingredients.get(3).getClass());
	}

	@Test
	void test_PepperoniPizza_has_the_correct_baking_duration() {
		// given
		PepperoniPizza pizza = new PepperoniPizza(2, new ThinCrustyDough(), new PlainTomatoSauce());
		// when
		Duration bakingDuration = pizza.getBakingDuration();
		// then
		assertEquals(7, bakingDuration.toMinutes());
	}
	
	@Test
	void test_PepperoniPizza_has_the_correct_baking_temperature() {
		// given
		PepperoniPizza pizza = new PepperoniPizza(2, new ThinCrustyDough(), new PlainTomatoSauce());
		// when
		int temperature = pizza.getBakingTemperature();
		// then
		assertEquals(315, temperature);
	}

}

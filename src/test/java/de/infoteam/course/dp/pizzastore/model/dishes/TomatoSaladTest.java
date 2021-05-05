package de.infoteam.course.dp.pizzastore.model.dishes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.OliveTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.PepperoniTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.TomatoTopping;

class TomatoSaladTest {

	@Test
	void test_TomatoSalad_has_the_correct_ingredients() {
		// given
		TomatoSalad salad = new TomatoSalad(2, new PepperoniTopping(), new OliveTopping());
		// when
		salad.addIngredients();
		// then
		assertNotNull(salad.getIngredients());
		List<Ingredient> ingredients = salad.getIngredients();
		assertEquals(5, ingredients.size());
		assertEquals(TomatoTopping.class, ingredients.get(0).getClass());
		assertEquals(TomatoTopping.class, ingredients.get(1).getClass());
		assertEquals(TomatoTopping.class, ingredients.get(2).getClass());
		assertEquals(PepperoniTopping.class, ingredients.get(3).getClass());
		assertEquals(OliveTopping.class, ingredients.get(4).getClass());
	}

}

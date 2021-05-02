package de.infoteam.course.dp.pizzastore.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.TomatoTopping;

class IngredientLoggerTest {

	@Test
	void test_logged_ingredients_are_recorded_correctly() {
		// given
		IngredientLogger logger = new IngredientLogger();
		List<Ingredient> ingredients = Arrays.asList(new MozzarellaCheese(), new TomatoTopping(),
				new ThinCrustyDough());
		// when
		ingredients.forEach(logger::logIngredient);
		// then
		List<Ingredient> loggedIngredients = logger.getConsumedIngredients();
		assertEquals(ingredients.size(), loggedIngredients.size());
		for (int i = 0; i < ingredients.size(); i++) {
			assertEquals(ingredients.get(i), loggedIngredients.get(i));
		}
	}

	@Test
	void test_printShoppingList() throws IOException {
		// given
		IngredientLogger logger = new IngredientLogger();
		List<Ingredient> ingredients = Arrays.asList(new MozzarellaCheese(), new TomatoTopping(),
				new ThinCrustyDough());
		ingredients.forEach(logger::logIngredient);
		ByteArrayOutputStream expectedList = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(expectedList);
		ingredients.stream().map(Ingredient::name).forEach(ps::println);
		expectedList.close();
		ByteArrayOutputStream producedList = new ByteArrayOutputStream();
		// when
		logger.printShoppingList(producedList);
		producedList.close();
		// then
		byte[] expected = expectedList.toByteArray();
		byte[] actual = producedList.toByteArray();
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);	
		}
		
		
	}
}

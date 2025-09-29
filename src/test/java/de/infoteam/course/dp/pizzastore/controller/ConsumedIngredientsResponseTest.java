package de.infoteam.course.dp.pizzastore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;

import de.infoteam.course.dp.pizzastore.service.IngredientLoggerAdapter;
import org.junit.jupiter.api.Test;

class ConsumedIngredientsResponseTest {

	@Test
	void test_of_returns_a_valid_representation() {
		// given
		IngredientLogger logger = new IngredientLogger();
		logger.logIngredient(new ThinCrustyDough());
		logger.logIngredient(new ThinCrustyDough());
		logger.logIngredient(new HandTossedDough());
		logger.logIngredient(new PlainTomatoSauce());
		logger.logIngredient(new MontereyJackCheese());
		logger.logIngredient(new MontereyJackCheese());
		// when
		ConsumedIngredientsResponse response = ConsumedIngredientsResponse.of(new IngredientLoggerAdapter(logger));
		// then
		assertTrue(response.toppings().isEmpty());
		assertEquals(2, response.dough().size());
		assertEquals(2, response.dough().get(new ThinCrustyDough().name()));
		assertEquals(1, response.dough().get(new HandTossedDough().name()));
		assertEquals(1, response.sauce().size());
		assertEquals(1, response.sauce().get(new PlainTomatoSauce().name()));
		assertEquals(1, response.cheese().size());
		assertEquals(2, response.cheese().get(new MontereyJackCheese().name()));
	}

}

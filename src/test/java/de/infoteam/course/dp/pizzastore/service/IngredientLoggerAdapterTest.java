package de.infoteam.course.dp.pizzastore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import org.junit.jupiter.api.Test;

class IngredientLoggerAdapterTest {

	@Test
	void test_aggregation_of_ingredients() {
		// given
		IngredientLogger logger = new IngredientLogger();
		logger.logIngredient(new ThinCrustyDough());
		logger.logIngredient(new ThinCrustyDough());
		logger.logIngredient(new HandTossedDough());
		logger.logIngredient(new PlainTomatoSauce());
		logger.logIngredient(new MontereyJackCheese());
		logger.logIngredient(new MontereyJackCheese());
		// when
		IngredientLoggerAdapter adapter = new IngredientLoggerAdapter(logger);
		// then
		assertTrue(adapter.toppings().isEmpty());
		assertEquals(2, adapter.dough().size());
		assertEquals(2, adapter.dough().get(new ThinCrustyDough().name()));
		assertEquals(1, adapter.dough().get(new HandTossedDough().name()));
		assertEquals(1, adapter.sauce().size());
		assertEquals(1, adapter.sauce().get(new PlainTomatoSauce().name()));
		assertEquals(1, adapter.cheese().size());
		assertEquals(2, adapter.cheese().get(new MontereyJackCheese().name()));
	}

}

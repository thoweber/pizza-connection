package de.infoteam.course.dp.pizzastore.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;

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
		assertTrue(adapter.getConsumedTopping().isEmpty());
		assertEquals(2, adapter.getConsumedDough().size());
		assertEquals(2, adapter.getConsumedDough().get(new ThinCrustyDough().name()));
		assertEquals(1, adapter.getConsumedDough().get(new HandTossedDough().name()));
		assertEquals(1, adapter.getConsumedSauce().size());
		assertEquals(1, adapter.getConsumedSauce().get(new PlainTomatoSauce().name()));
		assertEquals(1, adapter.getConsumedCheese().size());
		assertEquals(2, adapter.getConsumedCheese().get(new MontereyJackCheese().name()));
	}

}

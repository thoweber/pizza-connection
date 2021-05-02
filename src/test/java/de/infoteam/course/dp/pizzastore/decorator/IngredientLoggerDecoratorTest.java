package de.infoteam.course.dp.pizzastore.decorator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;

class IngredientLoggerDecoratorTest {

	@Test
	void test_aggregation_of_ingredients() {
		// given
		IngredientLoggerDecorator decorator = new IngredientLoggerDecorator(new IngredientLogger());
		decorator.logIngredient(new ThinCrustyDough());
		decorator.logIngredient(new ThinCrustyDough());
		decorator.logIngredient(new HandTossedDough());
		decorator.logIngredient(new PlainTomatoSauce());
		decorator.logIngredient(new MontereyJackCheese());
		decorator.logIngredient(new MontereyJackCheese());
		// when
		decorator.printShoppingList(new ByteArrayOutputStream());
		// then
		assertTrue(decorator.getConsumedTopping().isEmpty());
		assertEquals(2, decorator.getConsumedDough().size());
		assertEquals(2, decorator.getConsumedDough().get(new ThinCrustyDough().name()));
		assertEquals(1, decorator.getConsumedDough().get(new HandTossedDough().name()));
		assertEquals(1, decorator.getConsumedSauce().size());
		assertEquals(1, decorator.getConsumedSauce().get(new PlainTomatoSauce().name()));
		assertEquals(1, decorator.getConsumedCheese().size());
		assertEquals(2, decorator.getConsumedCheese().get(new MontereyJackCheese().name()));
	}

}

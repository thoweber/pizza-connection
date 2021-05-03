package de.infoteam.course.dp.pizzastore.controller.dto;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.adapter.IngredientLoggerAdapter;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;

class ConsumedIngredientsResponseTest {

	@Test
	void test_getters_and_setters() {
		// given
		Map<String, Integer> dough = Arrays.stream(new Object[][] { { "dough1", 1 }, { "dough2", 2 }, })
				.collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
		Map<String, Integer> cheese = Arrays.stream(new Object[][] { { "cheese1", 3 }, { "cheese2", 4 }, })
				.collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
		Map<String, Integer> sauce = Arrays.stream(new Object[][] { { "sauce1", 1 }, { "sauce2", 2 }, })
				.collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
		Map<String, Integer> toppings = Arrays.stream(new Object[][] { { "topping1", 3 }, { "topping2", 4 }, })
				.collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
		ConsumedIngredientsResponse response = new ConsumedIngredientsResponse();
		// when
		response.setConsumedDough(dough);
		response.setConsumedSauce(sauce);
		response.setConsumedCheese(cheese);
		response.setConsumedTopping(toppings);
		// then
		assertEquals(dough, response.getConsumedDough());
		assertEquals(cheese, response.getConsumedCheese());
		assertEquals(sauce, response.getConsumedSauce());
		assertEquals(toppings, response.getConsumedTopping());
	}

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
		assertTrue(response.getConsumedTopping().isEmpty());
		assertEquals(2, response.getConsumedDough().size());
		assertEquals(2, response.getConsumedDough().get(new ThinCrustyDough().name()));
		assertEquals(1, response.getConsumedDough().get(new HandTossedDough().name()));
		assertEquals(1, response.getConsumedSauce().size());
		assertEquals(1, response.getConsumedSauce().get(new PlainTomatoSauce().name()));
		assertEquals(1, response.getConsumedCheese().size());
		assertEquals(2, response.getConsumedCheese().get(new MontereyJackCheese().name()));
	}

}

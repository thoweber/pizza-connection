package de.infoteam.course.dp.pizzastore.controller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.State;

class FoodResponseTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		FoodResponse response = new FoodResponse();
		// when
		response.setId(42).setName("test").setState(State.DISH_UP);
		// then
		assertEquals(42, response.getId());
		assertEquals("test", response.getName());
		assertEquals(State.DISH_UP, response.getState());
	}

}

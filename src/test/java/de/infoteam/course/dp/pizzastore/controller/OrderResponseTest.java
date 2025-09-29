package de.infoteam.course.dp.pizzastore.controller;

import static org.junit.jupiter.api.Assertions.*;

import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.State;
import org.junit.jupiter.api.Test;

class OrderResponseTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		OrderResponse response = new OrderResponse();
		// when
		response.setId(42).setName("test").setPizzaStyle(FoodStyle.GOURMET).setState(State.DISH_UP);
		// then
		assertEquals(42, response.getId());
		assertEquals("test", response.getName());
		assertEquals(FoodStyle.GOURMET, response.getPizzaStyle());
		assertEquals("gourmet test", response.getFullName());
		assertEquals(State.DISH_UP, response.getState());
	}

}

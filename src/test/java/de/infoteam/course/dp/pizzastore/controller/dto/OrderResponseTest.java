package de.infoteam.course.dp.pizzastore.controller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.FoodStyle;

class OrderResponseTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		OrderResponse response = new OrderResponse();
		// when
		response.setId(42).setName("test").setPizzaStyle(FoodStyle.GOURMET);
		// then
		assertEquals(42, response.getId());
		assertEquals("test", response.getName());
		assertEquals(FoodStyle.GOURMET, response.getPizzaStyle());
		assertEquals("gourmet test", response.getFullName());
	}

}

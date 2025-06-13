package de.infoteam.course.dp.pizzastore.controller;

import static org.junit.jupiter.api.Assertions.*;

import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import org.junit.jupiter.api.Test;

class PizzaOrderResponseTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		PizzaOrderResponse response = new PizzaOrderResponse();
		// when
		response.setId(42).setName("test").setPizzaStyle(PizzaStyle.GOURMET);
		// then
		assertEquals(42, response.getId());
		assertEquals("test", response.getName());
		assertEquals(PizzaStyle.GOURMET, response.getPizzaStyle());
		assertEquals("gourmet test", response.getFullName());
	}

}

package de.infoteam.course.dp.pizzastore.controller.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;

class PizzaOrderRequestTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		PizzaOrderRequest request = new PizzaOrderRequest();
		// when
		request.setMenuItem(MenuItem.VEGGIE_PIZZA).setPizzaStyle(PizzaStyle.GOURMET);
		// then
		assertEquals(MenuItem.VEGGIE_PIZZA, request.getMenuItem());
		assertEquals(PizzaStyle.GOURMET, request.getPizzaStyle());
	}

}

package de.infoteam.course.dp.pizzastore.controller;

import static org.junit.jupiter.api.Assertions.*;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import org.junit.jupiter.api.Test;

class OrderRequestTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		OrderRequest request = new OrderRequest();
		// when
		request.setMenuItem(MenuItem.VEGGIE_PIZZA).setPizzaStyle(FoodStyle.GOURMET);
		// then
		assertEquals(MenuItem.VEGGIE_PIZZA, request.getMenuItem());
		assertEquals(FoodStyle.GOURMET, request.getPizzaStyle());
	}

}

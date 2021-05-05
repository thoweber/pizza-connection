package de.infoteam.course.dp.pizzastore.controller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.MenuItem;

class OrderRequestTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		OrderRequest request = new OrderRequest();
		// when
		request.setMenuItem(MenuItem.VEGGIE_PIZZA).setFoodStyle(FoodStyle.GOURMET);
		// then
		assertEquals(MenuItem.VEGGIE_PIZZA, request.getMenuItem());
		assertEquals(FoodStyle.GOURMET, request.getFoodStyle());
	}

}

package de.infoteam.course.dp.pizzastore.controller.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import de.infoteam.course.dp.pizzastore.model.State;

class PizzaResponseTest {

	@Test
	void test_values_are_stored_as_expected() {
		// given
		PizzaResponse response = new PizzaResponse();
		// when
		response.setId(42).setName("test").setState(State.DISH_UP);
		// then
		assertEquals(42, response.getId());
		assertEquals("test", response.getName());
		assertEquals(State.DISH_UP, response.getState());
	}

}

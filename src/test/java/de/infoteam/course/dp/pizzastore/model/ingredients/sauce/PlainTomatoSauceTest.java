package de.infoteam.course.dp.pizzastore.model.ingredients.sauce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlainTomatoSauceTest {

	@Test
	void test_name_returns_expected_value() {
		// given
		PlainTomatoSauce sauce = new PlainTomatoSauce();
		// when/then
		assertEquals("plain tomato sauce", sauce.name());
	}

}

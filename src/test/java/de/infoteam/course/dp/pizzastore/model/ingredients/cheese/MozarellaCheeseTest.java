package de.infoteam.course.dp.pizzastore.model.ingredients.cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MozarellaCheeseTest {

	@Test
	void test_name_returns_expected_value() {
		// given
		MozarellaCheese cheese = new MozarellaCheese();
		// when/then
		assertEquals("mozarella", cheese.name());
	}

}

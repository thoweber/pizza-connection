package de.infoteam.course.dp.pizzastore.model.ingredients.cheese;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MozzarellaCheeseTest {

	@Test
	void test_name_returns_expected_value() {
		// given
		MozzarellaCheese cheese = new MozzarellaCheese();
		// when/then
		assertEquals("mozzarella", cheese.name());
	}

}

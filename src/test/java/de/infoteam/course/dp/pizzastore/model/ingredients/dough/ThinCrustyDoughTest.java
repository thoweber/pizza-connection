package de.infoteam.course.dp.pizzastore.model.ingredients.dough;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ThinCrustyDoughTest {

	@Test
	void test_name_returns_expected_value() {
		// given
		ThinCrustyDough dough = new ThinCrustyDough();
		// when/then
		assertEquals("thin crusty dough", dough.name());
	}

}

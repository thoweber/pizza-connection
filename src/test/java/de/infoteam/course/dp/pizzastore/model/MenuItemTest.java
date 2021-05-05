package de.infoteam.course.dp.pizzastore.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuItemTest {

	@Test
	void test_MenuItem_has_exactly_three_values() {
		assertEquals(4, MenuItem.values().length);
	}

	static Stream<Arguments> menuItemValues() {
		return Stream.of(Arguments.of(MenuItem.CHEESE_PIZZA, "cheese pizza"),
				Arguments.of(MenuItem.PEPPERONI_PIZZA, "pepperoni pizza"),
				Arguments.of(MenuItem.VEGGIE_PIZZA, "veggie pizza"),
				Arguments.of(MenuItem.TOMATO_SALAD, "tomato salad"));
	}

	@ParameterizedTest
	@MethodSource("menuItemValues")
	void test_MenuItems_have_the_expected_names(MenuItem item, String expectedName) {
		assertEquals(expectedName, item.getName());
	}

}

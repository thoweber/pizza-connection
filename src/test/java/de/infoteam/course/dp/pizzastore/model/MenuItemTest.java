package de.infoteam.course.dp.pizzastore.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

class MenuItemTest {

	@Test
	void test_MenuItem_has_exactly_one_value() {
		assertEquals(1, MenuItem.values().length);
	}

	static Stream<Arguments> menuItemValues() {
		return Stream.of(Arguments.of(MenuItem.DEFAULT_PIZZA, "default pizza"));
	}

	@ParameterizedTest
	@MethodSource("menuItemValues")
	void test_MenuItems_have_the_expected_names(MenuItem item, String expectedName) {
		assertEquals(expectedName, item.getName());
	}

}

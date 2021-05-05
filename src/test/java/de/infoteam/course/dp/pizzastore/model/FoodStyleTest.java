package de.infoteam.course.dp.pizzastore.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FoodStyleTest {

	@Test
	void test_PizzaStyle_has_exactly_two_values() {
		assertEquals(2, FoodStyle.values().length);
	}

	static Stream<Arguments> pizzaStyleValues() {
		return Stream.of(Arguments.of(FoodStyle.SICILIAN, "sicilian"),
				Arguments.of(FoodStyle.GOURMET, "gourmet"));
	}

	@ParameterizedTest
	@MethodSource("pizzaStyleValues")
	void test_PizzaStyle_have_the_expected_names(FoodStyle item, String expectedName) {
		assertEquals(expectedName, item.getName());
	}

}

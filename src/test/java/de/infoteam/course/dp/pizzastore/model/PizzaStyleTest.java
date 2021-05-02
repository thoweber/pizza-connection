package de.infoteam.course.dp.pizzastore.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

class PizzaStyleTest {

	@Test
	void test_PizzaStyle_has_exactly_two_values() {
		assertEquals(2, PizzaStyle.values().length);
	}

	static Stream<Arguments> pizzaStyleValues() {
		return Stream.of(Arguments.of(PizzaStyle.SICILIAN, "sicilian"),
				Arguments.of(PizzaStyle.GOURMET, "gourmet"));
	}

	@ParameterizedTest
	@MethodSource("pizzaStyleValues")
	void test_PizzaStyle_have_the_expected_names(PizzaStyle item, String expectedName) {
		assertEquals(expectedName, item.getName());
	}

}

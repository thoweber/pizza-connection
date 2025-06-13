package de.infoteam.course.dp.pizzastore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.infoteam.course.dp.pizzastore.model.MenuItem;

class PizzaStoreAppTest {

	static Stream<Arguments> menuItemForChoiceValueSource() {
		return Stream
				.concat(Stream.of(MenuItem.values()).map(mi -> Arguments.of(Integer.toString(mi.ordinal() + 1), mi)),
						Stream.of(Arguments.of("bogus", null), Arguments.of("q", null), Arguments.of(null, null),
								Arguments.of("0", null),
								Arguments.of(Integer.toString(MenuItem.values().length + 1), null)));
	}

	@ParameterizedTest
	@MethodSource("menuItemForChoiceValueSource")
	void test_menuItemForChoice(String choice, MenuItem expected) {
		assertEquals(expected, PizzaStoreApp.menuItemForChoice(choice).orElse(null));
	}

}

package de.infoteam.course.dp.pizzastore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PepperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.service.impl.GourmetPizzaFactory;
import de.infoteam.course.dp.pizzastore.service.impl.SicilianPizzaFactory;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

	@Mock
	Pizza pizza;

	IngredientLogger ingredientLogger = new IngredientLogger();

	PizzaService pizzaService = PizzaService.builder().gourmetFactory(new GourmetPizzaFactory())
			.sicilianFactory(new SicilianPizzaFactory()).ingredientLogger(ingredientLogger).build();

	static Stream<Arguments> menuItemPizzaClassSource() {
		return Stream.of(Arguments.of(MenuItem.CHEESE_PIZZA, CheesePizza.class),
				Arguments.of(MenuItem.PEPPERONI_PIZZA, PepperoniPizza.class),
				Arguments.of(MenuItem.VEGGIE_PIZZA, VeggiePizza.class));
	}

	@ParameterizedTest
	@MethodSource("menuItemPizzaClassSource")
	void test_order_returns_the_right_kind_of_pizza_SICILIAN_style(MenuItem menuItem, Class<Pizza> expectedPizzaKind) {
		// when
		Pizza pizza = pizzaService.order(menuItem, PizzaStyle.SICILIAN);
		// then
		assertEquals(expectedPizzaKind, pizza.getClass());
	}

	@ParameterizedTest
	@MethodSource("menuItemPizzaClassSource")
	void test_order_returns_the_right_kind_of_pizza_GOURMET_style(MenuItem menuItem, Class<Pizza> expectedPizzaKind) {
		// when
		Pizza pizza = pizzaService.order(menuItem, PizzaStyle.GOURMET);
		// then
		assertEquals(expectedPizzaKind, pizza.getClass());
	}

	@Test
	void test_chooseFactory_returns_the_SicilianPizzaFactory_for_style_SICILIAN() {
		// given
		PizzaStyle style = PizzaStyle.SICILIAN;
		// when
		PizzaFactory factory = pizzaService.chooseFactory(style);
		// then
		assertEquals(SicilianPizzaFactory.class, factory.getClass());
	}

	@Test
	void test_chooseFactory_returns_the_SicilianPizzaFactory_for_style_GOURMET() {
		// given
		PizzaStyle style = PizzaStyle.GOURMET;
		// when
		PizzaFactory factory = pizzaService.chooseFactory(style);
		// then
		assertEquals(GourmetPizzaFactory.class, factory.getClass());
	}

	@Test
	void test_builder_without_sicilian_factory_throw_IllegalStateException() {
		PizzaService.Builder builder = PizzaService.builder().gourmetFactory(new GourmetPizzaFactory())
				.ingredientLogger(new IngredientLogger());
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

	@Test
	void test_builder_without_gourmet_factory_throw_IllegalStateException() {
		PizzaService.Builder builder = PizzaService.builder().sicilianFactory(new SicilianPizzaFactory())
				.ingredientLogger(new IngredientLogger());
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

	@Test
	void test_builder_without_IngredientLogger_throw_IllegalStateException() {
		PizzaService.Builder builder = PizzaService.builder().sicilianFactory(new SicilianPizzaFactory())
				.gourmetFactory(new GourmetPizzaFactory());
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

}

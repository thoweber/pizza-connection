package de.infoteam.course.dp.pizzastore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PepperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.service.impl.GourmetPizzaFactory;
import de.infoteam.course.dp.pizzastore.service.impl.SicilianPizzaFactory;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

	@Mock
	Pizza pizza;

	@Spy
	PizzaService pizzaService = new PizzaService(new SicilianPizzaFactory(), new GourmetPizzaFactory());

	@Test
	void test_order_calls_preparePizza_bakePizza_servePizza_in_order() {
		// when
		pizzaService.order(MenuItem.CHEESE_PIZZA, PizzaStyle.SICILIAN);
		// then
		InOrder inOrder = inOrder(pizzaService);
		then(pizzaService).should(inOrder).preparePizza(any());
		then(pizzaService).should(inOrder).bakePizza(any());
		then(pizzaService).should(inOrder).servePizza(any());
	}

	@Test
	void test_preparePizza_calls_addIngredients() {
		// given
		given(pizza.getIngredients()).willReturn(Arrays.asList(new ThinCrustyDough()));
		// when
		pizzaService.preparePizza(pizza);
		// then
		then(pizza).should().addIngredients();
	}

	@Test
	void test_bakePizza_accesses_baking_information_from_pizza() {
		// given
		given(pizza.getBakingDuration()).willReturn(Duration.ofMinutes(10));
		given(pizza.getBakingTemperature()).willReturn(250);
		// when
		pizzaService.bakePizza(pizza);
		// then
		then(pizza).should().getBakingDuration();
		then(pizza).should().getBakingTemperature();
	}

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
}

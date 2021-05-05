package de.infoteam.course.dp.pizzastore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PepperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.repository.DishRepository;
import de.infoteam.course.dp.pizzastore.service.impl.GourmetFoodFactory;
import de.infoteam.course.dp.pizzastore.service.impl.SicilianFoodFactory;

class FoodOrderServiceTest {

	IngredientLogger ingredientLogger = new IngredientLogger();

	FoodOrderService foodOrderService = FoodOrderService.builder().gourmetFactory(new GourmetFoodFactory())
			.sicilianFactory(new SicilianFoodFactory()).ingredientLogger(ingredientLogger)
			.pizzaRepository(new DishRepository()).build();

	static Stream<Arguments> menuItemPizzaClassSource() {
		return Stream.of(Arguments.of(MenuItem.CHEESE_PIZZA, CheesePizza.class),
				Arguments.of(MenuItem.PEPPERONI_PIZZA, PepperoniPizza.class),
				Arguments.of(MenuItem.VEGGIE_PIZZA, VeggiePizza.class));
	}

	@ParameterizedTest
	@MethodSource("menuItemPizzaClassSource")
	void test_order_returns_the_right_kind_of_pizza_SICILIAN_style(MenuItem menuItem, Class<Pizza> expectedPizzaKind) {
		// when
		Dish dish = foodOrderService.order(menuItem, FoodStyle.SICILIAN);
		// then
		assertEquals(expectedPizzaKind, dish.getClass());
	}

	@ParameterizedTest
	@MethodSource("menuItemPizzaClassSource")
	void test_order_returns_the_right_kind_of_pizza_GOURMET_style(MenuItem menuItem, Class<Pizza> expectedPizzaKind) {
		// when
		Dish dish = foodOrderService.order(menuItem, FoodStyle.GOURMET);
		// then
		assertEquals(expectedPizzaKind, dish.getClass());
	}

	@Test
	void test_chooseFactory_returns_the_SicilianPizzaFactory_for_style_SICILIAN() {
		// given
		FoodStyle style = FoodStyle.SICILIAN;
		// when
		FoodFactory factory = foodOrderService.chooseFactory(style);
		// then
		assertEquals(SicilianFoodFactory.class, factory.getClass());
	}

	@Test
	void test_chooseFactory_returns_the_SicilianPizzaFactory_for_style_GOURMET() {
		// given
		FoodStyle style = FoodStyle.GOURMET;
		// when
		FoodFactory factory = foodOrderService.chooseFactory(style);
		// then
		assertEquals(GourmetFoodFactory.class, factory.getClass());
	}

	@Test
	void test_builder_without_sicilian_factory_throw_IllegalStateException() {
		FoodOrderService.Builder builder = FoodOrderService.builder().gourmetFactory(new GourmetFoodFactory())
				.ingredientLogger(new IngredientLogger()).pizzaRepository(new DishRepository());
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

	@Test
	void test_builder_without_gourmet_factory_throw_IllegalStateException() {
		FoodOrderService.Builder builder = FoodOrderService.builder().sicilianFactory(new SicilianFoodFactory())
				.ingredientLogger(new IngredientLogger()).pizzaRepository(new DishRepository());
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

	@Test
	void test_builder_without_IngredientLogger_throw_IllegalStateException() {
		FoodOrderService.Builder builder = FoodOrderService.builder().sicilianFactory(new SicilianFoodFactory())
				.gourmetFactory(new GourmetFoodFactory()).pizzaRepository(new DishRepository());
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

	@Test
	void test_builder_without_PizzaRepository_throw_IllegalStateException() {
		FoodOrderService.Builder builder = FoodOrderService.builder().sicilianFactory(new SicilianFoodFactory())
				.gourmetFactory(new GourmetFoodFactory()).ingredientLogger(new IngredientLogger());
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

	@Test
	void test_builder_does_not_accept_numOfChefs_smaller_than_1() {
		FoodOrderService.Builder builder = FoodOrderService.builder();
		assertThrows(IllegalArgumentException.class, () -> builder.numberOfChefs(0));
	}

	@Test
	void test_builder_does_not_accept_numOfChefs_larger_than_8() {
		FoodOrderService.Builder builder = FoodOrderService.builder();
		assertThrows(IllegalArgumentException.class, () -> builder.numberOfChefs(9));
	}

	@Test
	void test_builder_with_acceptable_values_builds() {
		FoodOrderService.Builder builder = FoodOrderService.builder().gourmetFactory(new GourmetFoodFactory())
				.sicilianFactory(new SicilianFoodFactory()).ingredientLogger(new IngredientLogger())
				.pizzaRepository(new DishRepository()).numberOfChefs(6);
		assertNotNull(builder.build());
	}

}

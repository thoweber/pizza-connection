package de.infoteam.course.dp.pizzastore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PeperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.repository.DishRepository;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FoodOrderServiceTest {

  @Mock IngredientLogger ingredientLogger;

  @Spy
  SicilianFoodFactory sicilianFoodFactory = new SicilianFoodFactory();
  @Spy
  GourmetFoodFactory gourmetFoodFactory = new GourmetFoodFactory();

  FoodOrderService foodOrderService;

  @BeforeEach
  void setup() {
    foodOrderService =
        spy(
            FoodOrderService.builder()
                .gourmetFactory(gourmetFoodFactory)
                .sicilianFactory(sicilianFoodFactory)
                .ingredientLogger(ingredientLogger)
                .pizzaRepository(new DishRepository())
                .build());
  }

  static Stream<Arguments> menuItemPizzaClassSource() {
    return Stream.of(
        Arguments.of(MenuItem.CHEESE_PIZZA, CheesePizza.class),
        Arguments.of(MenuItem.PEPERONI_PIZZA, PeperoniPizza.class),
        Arguments.of(MenuItem.VEGGIE_PIZZA, VeggiePizza.class));
  }

  @ParameterizedTest
  @MethodSource("menuItemPizzaClassSource")
  void test_order_returns_the_right_kind_of_pizza_SICILIAN_style(
      MenuItem menuItem, Class<Pizza> expectedPizzaKind) {
    // when
    Dish dish = foodOrderService.order(menuItem, FoodStyle.SICILIAN);
    // then
    assertEquals(expectedPizzaKind, dish.getClass());
  }

  @ParameterizedTest
  @MethodSource("menuItemPizzaClassSource")
  void test_order_returns_the_right_kind_of_pizza_GOURMET_style(
      MenuItem menuItem, Class<Pizza> expectedPizzaKind) {
    // when
    Dish dish = foodOrderService.order(menuItem, FoodStyle.GOURMET);
    // then
    assertEquals(expectedPizzaKind, dish.getClass());
  }

	@Test
	void test_chooseFactory_uses_the_SicilianPizzaFactory_for_style_SICILIAN() {
		// given
		FoodStyle style = FoodStyle.SICILIAN;
		MenuItem menuItem = MenuItem.VEGGIE_PIZZA;
		// when
		foodOrderService.order(menuItem, style);
		// then
		verify(sicilianFoodFactory, times(1)).createDish(eq(menuItem), anyLong());
		verify(gourmetFoodFactory, never()).createDish(any(), anyLong());
	}

	@Test
	void test_chooseFactory_uses_the_GourmetPizzaFactory_for_style_GOURMET() {
		// given
		FoodStyle style = FoodStyle.GOURMET;
		MenuItem menuItem = MenuItem.PEPERONI_PIZZA;
		// when
		foodOrderService.order(menuItem, style);
		// then
		verify(gourmetFoodFactory, times(1)).createDish(eq(menuItem), anyLong());
		verify(sicilianFoodFactory, never()).createDish(any(), anyLong());
	}

  @Test
  void test_builder_without_sicilian_factory_throw_IllegalStateException() {
    FoodOrderService.Builder builder =
        FoodOrderService.builder()
            .gourmetFactory(new GourmetFoodFactory())
            .ingredientLogger(new IngredientLogger());
    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  void test_builder_without_gourmet_factory_throw_IllegalStateException() {
    FoodOrderService.Builder builder =
        FoodOrderService.builder()
            .sicilianFactory(new SicilianFoodFactory())
            .ingredientLogger(new IngredientLogger());
    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  void test_builder_without_IngredientLogger_throw_IllegalStateException() {
    FoodOrderService.Builder builder =
        FoodOrderService.builder()
            .sicilianFactory(new SicilianFoodFactory())
            .gourmetFactory(new GourmetFoodFactory());
    assertThrows(IllegalStateException.class, builder::build);
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

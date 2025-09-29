package de.infoteam.course.dp.pizzastore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PeperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.repository.PizzaRepository;
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
class PizzaServiceTest {

  @Mock IngredientLogger ingredientLogger;

  @Spy SicilianPizzaFactory sicilianPizzaFactory = new SicilianPizzaFactory();
  @Spy GourmetPizzaFactory gourmetPizzaFactory = new GourmetPizzaFactory();

  PizzaService pizzaService;

  @BeforeEach
  void setup() {
    pizzaService =
        spy(
            PizzaService.builder()
                .gourmetFactory(gourmetPizzaFactory)
                .sicilianFactory(sicilianPizzaFactory)
                .ingredientLogger(ingredientLogger)
                .pizzaRepository(new PizzaRepository())
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
    var pizza = pizzaService.order(menuItem, PizzaStyle.SICILIAN);
    // then
    assertEquals(expectedPizzaKind, pizza.getClass());
  }

  @ParameterizedTest
  @MethodSource("menuItemPizzaClassSource")
  void test_order_returns_the_right_kind_of_pizza_GOURMET_style(
    MenuItem menuItem, Class<Pizza> expectedPizzaKind) {
    // when
    var pizza = pizzaService.order(menuItem, PizzaStyle.GOURMET);
    // then
    assertEquals(expectedPizzaKind, pizza.getClass());
  }

  @Test
  void test_chooseFactory_uses_the_SicilianPizzaFactory_for_style_SICILIAN() {
    // given
    var style = PizzaStyle.SICILIAN;
    var menuItem = MenuItem.VEGGIE_PIZZA;
    // when
    pizzaService.order(menuItem, style);
    // then
    verify(sicilianPizzaFactory, times(1)).createPizza(eq(menuItem), anyLong());
    verify(gourmetPizzaFactory, never()).createPizza(any(), anyLong());
  }

  @Test
  void test_chooseFactory_uses_the_GourmetPizzaFactory_for_style_GOURMET() {
    // given
    var style = PizzaStyle.GOURMET;
    var menuItem = MenuItem.PEPERONI_PIZZA;
    // when
    pizzaService.order(menuItem, style);
    // then
    verify(gourmetPizzaFactory, times(1)).createPizza(eq(menuItem), anyLong());
    verify(sicilianPizzaFactory, never()).createPizza(any(), anyLong());
  }

  @Test
  void test_builder_without_sicilian_factory_throw_IllegalStateException() {
    PizzaService.Builder builder =
        PizzaService.builder()
            .gourmetFactory(new GourmetPizzaFactory())
            .ingredientLogger(new IngredientLogger());
    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  void test_builder_without_gourmet_factory_throw_IllegalStateException() {
    PizzaService.Builder builder =
        PizzaService.builder()
            .sicilianFactory(new SicilianPizzaFactory())
            .ingredientLogger(new IngredientLogger());
    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  void test_builder_without_IngredientLogger_throw_IllegalStateException() {
    PizzaService.Builder builder =
        PizzaService.builder()
            .sicilianFactory(new SicilianPizzaFactory())
            .gourmetFactory(new GourmetPizzaFactory());
    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  void test_builder_without_PizzaRepository_throw_IllegalStateException() {
    PizzaService.Builder builder = PizzaService.builder().sicilianFactory(new SicilianPizzaFactory())
            .gourmetFactory(new GourmetPizzaFactory()).ingredientLogger(new IngredientLogger());
    assertThrows(IllegalStateException.class, () -> builder.build());
  }

  @Test
  void test_builder_does_not_accept_numOfChefs_smaller_than_1() {
    PizzaService.Builder builder = PizzaService.builder();
    assertThrows(IllegalArgumentException.class, () -> builder.numberOfChefs(0));
  }

  @Test
  void test_builder_does_not_accept_numOfChefs_larger_than_8() {
    PizzaService.Builder builder = PizzaService.builder();
    assertThrows(IllegalArgumentException.class, () -> builder.numberOfChefs(9));
  }

  @Test
  void test_builder_with_acceptable_values_builds() {
    PizzaService.Builder builder = PizzaService.builder().gourmetFactory(new GourmetPizzaFactory())
            .sicilianFactory(new SicilianPizzaFactory()).ingredientLogger(new IngredientLogger())
            .pizzaRepository(new PizzaRepository()).numberOfChefs(6);
    assertNotNull(builder.build());
  }
}

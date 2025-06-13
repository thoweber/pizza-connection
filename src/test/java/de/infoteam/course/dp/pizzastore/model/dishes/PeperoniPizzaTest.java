package de.infoteam.course.dp.pizzastore.model.dishes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.PeperoniTopping;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;

class PeperoniPizzaTest {

  @Test
  void test_PeperoniPizza_has_the_correct_ingredients() {
    // given
    PeperoniPizza pizza = new PeperoniPizza();
    // when
    pizza.addIngredients();
    // then
    assertNotNull(pizza.getIngredients());
    List<Ingredient> ingredients = pizza.getIngredients();
    assertEquals(4, ingredients.size());
    assertEquals(ThinCrustyDough.class, ingredients.get(0).getClass());
    assertEquals(PlainTomatoSauce.class, ingredients.get(1).getClass());
    assertEquals(MontereyJackCheese.class, ingredients.get(2).getClass());
    assertEquals(PeperoniTopping.class, ingredients.get(3).getClass());
  }

  @Test
  void test_PeperoniPizza_has_the_correct_baking_duration() {
    // given
    PeperoniPizza pizza = new PeperoniPizza();
    // when
    Duration bakingDuration = pizza.getBakingDuration();
    // then
    assertEquals(7, bakingDuration.toMinutes());
  }

  @Test
  void test_PeperoniPizza_has_the_correct_baking_temperature() {
    // given
    PeperoniPizza pizza = new PeperoniPizza();
    // when
    int temperature = pizza.getBakingTemperature();
    // then
    assertEquals(315, temperature);
  }
}

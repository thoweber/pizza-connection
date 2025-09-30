package de.infoteam.course.dp.pizzastore.model.dishes;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheesePizza implements Pizza {

  private static final String NAME = "cheese pizza (formerly known as default)";

  private final List<Ingredient> ingredients = new ArrayList<>();

  @Override
  public void addIngredients() {
    ingredients.add(new ThinCrustyDough());
    ingredients.add(new PlainTomatoSauce());
    ingredients.add(new MozzarellaCheese());
  }

  @Override
  public List<Ingredient> getIngredients() {
    return Collections.unmodifiableList(ingredients);
  }

  @Override
  public Duration getBakingDuration() {
    return Duration.ofMinutes(6);
  }

  @Override
  public int getBakingTemperature() {
    return 337;
  }

  @Override
  public String name() {
    return CheesePizza.NAME;
  }
}

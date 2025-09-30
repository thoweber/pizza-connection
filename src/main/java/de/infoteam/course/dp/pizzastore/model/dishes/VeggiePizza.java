package de.infoteam.course.dp.pizzastore.model.dishes;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.ArtichokeTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.OliveTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.TomatoTopping;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VeggiePizza implements Pizza {

  private static final String NAME = "veggie pizza";

  private final List<Ingredient> ingredients = new ArrayList<>();

  @Override
  public void addIngredients() {
    ingredients.add(new ThinCrustyDough());
    ingredients.add(new PlainTomatoSauce());
    ingredients.add(new MozzarellaCheese());
    ingredients.add(new TomatoTopping());
    ingredients.add(new ArtichokeTopping());
    ingredients.add(new OliveTopping());
  }

  @Override
  public List<Ingredient> getIngredients() {
    return Collections.unmodifiableList(this.ingredients);
  }

  @Override
  public Duration getBakingDuration() {
    return Duration.ofMinutes(8);
  }

  @Override
  public int getBakingTemperature() {
    return 300;
  }

  @Override
  public String name() {
    return VeggiePizza.NAME;
  }
}

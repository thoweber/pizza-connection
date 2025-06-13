package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;


public class PeperoniTopping implements Topping {

  private static final String NAME = "hot peperoni";

  @Override
  public String name() {
    return PeperoniTopping.NAME;
  }
}

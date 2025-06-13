package de.infoteam.course.dp.pizzastore.model;

public enum MenuItem {
  DEFAULT_PIZZA("default pizza");

  private final String name;

  MenuItem(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

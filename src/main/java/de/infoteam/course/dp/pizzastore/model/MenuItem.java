package de.infoteam.course.dp.pizzastore.model;

public enum MenuItem {

	CHEESE_PIZZA("cheese pizza"), PEPPERONI_PIZZA("pepperoni pizza"), VEGGIE_PIZZA("veggie pizza");

	private final String name;

	private MenuItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

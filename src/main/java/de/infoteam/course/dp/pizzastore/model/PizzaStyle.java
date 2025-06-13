package de.infoteam.course.dp.pizzastore.model;

public enum PizzaStyle {

	SICILIAN("sicilian"), GOURMET("gourmet");
	
	private final String name;
	
	PizzaStyle(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}

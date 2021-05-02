package de.infoteam.course.dp.pizzastore.model;

public enum PizzaStyle {

	SICILIAN("sicilian"), GOURMET("gourmet");
	
	private String name;
	
	private PizzaStyle(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}

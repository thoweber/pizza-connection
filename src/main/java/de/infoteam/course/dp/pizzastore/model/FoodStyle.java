package de.infoteam.course.dp.pizzastore.model;

public enum FoodStyle {

	SICILIAN("sicilian"), GOURMET("gourmet");
	
	private String name;
	
	private FoodStyle(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}

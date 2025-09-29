package de.infoteam.course.dp.pizzastore.controller;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;

public class OrderRequest {

	private MenuItem menuItem;
	private FoodStyle foodStyle;

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public OrderRequest setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
		return this;
	}

	public FoodStyle getPizzaStyle() {
		return foodStyle;
	}

	public OrderRequest setPizzaStyle(FoodStyle foodStyle) {
		this.foodStyle = foodStyle;
		return this;
	}

}

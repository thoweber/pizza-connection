package de.infoteam.course.dp.pizzastore.controller.dto;

import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.MenuItem;

public class OrderRequest {

	private MenuItem menuItem;
	private FoodStyle pizzaStyle;

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public OrderRequest setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
		return this;
	}

	public FoodStyle getFoodStyle() {
		return pizzaStyle;
	}

	public OrderRequest setFoodStyle(FoodStyle pizzaStyle) {
		this.pizzaStyle = pizzaStyle;
		return this;
	}

}

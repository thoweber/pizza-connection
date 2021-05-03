package de.infoteam.course.dp.pizzastore.controller.dto;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;

public class PizzaOrderRequest {

	private MenuItem menuItem;
	private PizzaStyle pizzaStyle;

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public PizzaOrderRequest setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
		return this;
	}

	public PizzaStyle getPizzaStyle() {
		return pizzaStyle;
	}

	public PizzaOrderRequest setPizzaStyle(PizzaStyle pizzaStyle) {
		this.pizzaStyle = pizzaStyle;
		return this;
	}

}

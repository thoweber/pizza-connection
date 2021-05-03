package de.infoteam.course.dp.pizzastore.controller.dto;

import de.infoteam.course.dp.pizzastore.model.PizzaStyle;

public class PizzaOrderResponse {

	private long id;
	private String name;
	private PizzaStyle pizzaStyle;

	public long getId() {
		return id;
	}

	public PizzaOrderResponse setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PizzaOrderResponse setName(String name) {
		this.name = name;
		return this;
	}

	public PizzaStyle getPizzaStyle() {
		return pizzaStyle;
	}

	public PizzaOrderResponse setPizzaStyle(PizzaStyle pizzaStyle) {
		this.pizzaStyle = pizzaStyle;
		return this;
	}

	public String getFullName() {
		return pizzaStyle.getName() + " " + name;
	}

}

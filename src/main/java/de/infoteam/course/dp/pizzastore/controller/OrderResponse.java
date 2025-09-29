package de.infoteam.course.dp.pizzastore.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.State;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrderResponse {

	private long id;
	private String name;
	private FoodStyle foodStyle;
	private State state;

	public long getId() {
		return id;
	}

	public OrderResponse setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public OrderResponse setName(String name) {
		this.name = name;
		return this;
	}

	public FoodStyle getPizzaStyle() {
		return foodStyle;
	}

	public OrderResponse setPizzaStyle(FoodStyle foodStyle) {
		this.foodStyle = foodStyle;
		return this;
	}

	public String getFullName() {
		return foodStyle.getName() + " " + name;
	}

	public State getState() {
		return state;
	}

	public OrderResponse setState(State state) {
		this.state=state;
		return this;
	}
}

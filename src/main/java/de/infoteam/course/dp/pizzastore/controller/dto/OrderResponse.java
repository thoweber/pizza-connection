package de.infoteam.course.dp.pizzastore.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.infoteam.course.dp.pizzastore.model.FoodStyle;
import de.infoteam.course.dp.pizzastore.model.State;

@JsonInclude(value = Include.NON_NULL)
public class OrderResponse {

	private long id;
	private String name;
	private FoodStyle pizzaStyle;
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
		return pizzaStyle;
	}

	public OrderResponse setPizzaStyle(FoodStyle pizzaStyle) {
		this.pizzaStyle = pizzaStyle;
		return this;
	}

	public String getFullName() {
		return pizzaStyle.getName() + " " + name;
	}
	
	public State getState() {
		return state;
	}

	public OrderResponse setState(State state) {
		this.state=state;
		return this;
	}

}

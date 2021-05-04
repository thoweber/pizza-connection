package de.infoteam.course.dp.pizzastore.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.infoteam.course.dp.pizzastore.model.State;

@JsonInclude(value = Include.NON_NULL)
public class PizzaResponse {

	private long id;
	private String name;
	private State state;

	public long getId() {
		return id;
	}

	public PizzaResponse setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PizzaResponse setName(String name) {
		this.name = name;
		return this;
	}

	public State getState() {
		return state;
	}

	public PizzaResponse setState(State state) {
		this.state=state;
		return this;
	}

}

package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;

public final class PizzaStateChange {

	private final long pizzaId;
	private final State state;

	private PizzaStateChange(long pizzaId, State state) {
		this.pizzaId = pizzaId;
		this.state = state;
	}
	
	public static PizzaStateChange of(Pizza pizza) {
		return new PizzaStateChange(pizza.getId(), pizza.getState());
	}
	
	public long getPizzaId() {
		return pizzaId;
	}
	
	public State getState() {
		return state;
	}

}

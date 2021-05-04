package de.infoteam.course.dp.pizzastore.service.model;

import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;

public final class PizzaStateChange {

	private long pizzaId;
	private State state;

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

package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;

public final class DishStateChange {

	private final long dishId;
	private final State state;

	private DishStateChange(long dishId, State state) {
		this.dishId = dishId;
		this.state = state;
	}
	
	public static DishStateChange of(Dish dish) {
		return new DishStateChange(dish.getId(), dish.getState());
	}
	
	public long getDishId() {
		return dishId;
	}
	
	public State getState() {
		return state;
	}

}

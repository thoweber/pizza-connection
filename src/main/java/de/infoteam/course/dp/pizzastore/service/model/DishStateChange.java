package de.infoteam.course.dp.pizzastore.service.model;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;

public final class DishStateChange {

	private long dishId;
	private State state;

	private DishStateChange(long id, State state) {
		this.dishId = id;
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

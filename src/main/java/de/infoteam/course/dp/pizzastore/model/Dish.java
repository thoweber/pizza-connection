package de.infoteam.course.dp.pizzastore.model;

import java.util.List;

public interface Dish {

	/**
	 * Returns the id of the {@code Pizza}.
	 * 
	 * @return the id of the {@code Pizza}
	 */
	long getId();

	/**
	 * Returns the state of the {@code Pizza}.
	 * 
	 * @return the state of the {@code Pizza}
	 */
	State getState();

	/**
	 * Updates the state to the given value.
	 * 
	 * @param state the new state
	 */
	void updateState(State state);

	/**
	 * Returns the name of the {@code Pizza}.
	 * 
	 * @return the name of the {@code Pizza}
	 */
	String name();

	/**
	 * Adds the required ingredients to the {@code Pizza}.
	 */
	void addIngredients();

	/**
	 * Returns a list of all ingredients of the {@code Pizza}.
	 * 
	 * @return a list of all ingredients of the {@code Pizza}
	 */
	List<Ingredient> getIngredients();
	
}

package de.infoteam.course.dp.pizzastore.model;

import java.time.Duration;
import java.util.List;

/**
 * Defines the interface for a {@code Pizza}.
 * 
 * @author Thomas Weber
 */
public interface Pizza {
	
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

	/**
	 * Returns the duration, the {@code Pizza} has to be baked for.
	 * 
	 * @return the baking duration
	 */
	Duration getBakingDuration();

	/**
	 * Returns the temperature in degrees Celsius required when baking the
	 * {@code Pizza}.
	 * 
	 * @return the temperature in degrees Celsius
	 */
	int getBakingTemperature();

}

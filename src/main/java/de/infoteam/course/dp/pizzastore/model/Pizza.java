package de.infoteam.course.dp.pizzastore.model;

import java.time.Duration;

/**
 * Defines the interface for a {@code Pizza}.
 * 
 * @author Thomas Weber
 */
public interface Pizza extends Dish {

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

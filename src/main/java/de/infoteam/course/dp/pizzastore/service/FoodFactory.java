package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.MenuItem;

/**
 * A factory to create a {@code Dish} from a {@code MenuItem}.
 * 
 * @author Thomas Weber
 */
public interface FoodFactory {

	/**
	 * Creates the {@code Dish} for the given {@code MenuItem}.
	 * 
	 * @param selectedItem the selected {@code MenuItem}
	 * @param orderId      the id of the dish to be created
	 * @return the {@code Dish}
	 */
	Dish createDish(MenuItem selectedItem, long orderId);

}

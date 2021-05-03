package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;

/**
 * A factory to create {@code Pizza} for {@code MenuItem}s.
 * 
 * @author Thomas Weber
 */
public interface PizzaFactory {

	/**
	 * Creates the {@code Pizza} for the given {@code MenuItem}.
	 * 
	 * @param selectedItem the selected {@code MenuItem}
	 * @param orderId      the id of the pizza to be created
	 * @return the {@code Pizza}
	 */
	Pizza createPizza(MenuItem selectedItem, long orderId);

}

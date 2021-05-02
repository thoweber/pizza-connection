package de.infoteam.course.dp.pizzastore.service.impl;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PepperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.service.PizzaFactory;

public class ConcretePizzaFactory implements PizzaFactory {

	@Override
	public Pizza createPizza(MenuItem selectedItem) {
		Pizza pizza = null;
		switch (selectedItem) {
		case CHEESE_PIZZA:
			pizza = new CheesePizza();
			break;
		case PEPPERONI_PIZZA:
			pizza = new PepperoniPizza();
			break;
		case VEGGIE_PIZZA:
			pizza = new VeggiePizza();
			break;
		default:
			throw new IllegalStateException("No pizza defined for " + selectedItem);
		}
		return pizza;
	}

}

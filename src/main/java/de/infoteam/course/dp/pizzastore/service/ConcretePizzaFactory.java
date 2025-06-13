package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PeperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;

public class ConcretePizzaFactory implements PizzaFactory {

	@Override
	public Pizza createPizza(MenuItem selectedItem) {
		return switch (selectedItem) {
			case CHEESE_PIZZA -> new CheesePizza();
			case PEPERONI_PIZZA -> new PeperoniPizza();
			case VEGGIE_PIZZA -> new VeggiePizza();
		};
	}

}

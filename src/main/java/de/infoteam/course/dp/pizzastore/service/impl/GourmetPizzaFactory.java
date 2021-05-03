package de.infoteam.course.dp.pizzastore.service.impl;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PepperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PremiumTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.service.PizzaFactory;

public class GourmetPizzaFactory implements PizzaFactory {

	@Override
	public Pizza createPizza(MenuItem selectedItem, long id) {
		Pizza pizza = null;
		switch (selectedItem) {
		case CHEESE_PIZZA:
			pizza = new CheesePizza(id, prepareDough(), prepareSauce());
			break;
		case PEPPERONI_PIZZA:
			pizza = new PepperoniPizza(id, prepareDough(), prepareSauce());
			break;
		case VEGGIE_PIZZA:
			pizza = new VeggiePizza(id, prepareDough(), prepareSauce());
			break;
		default:
			throw new IllegalStateException("No pizza defined for " + selectedItem);
		}
		return pizza;
	}
	
	private Dough prepareDough() {
		return new HandTossedDough();
	}

	private Sauce prepareSauce() {
		return new PremiumTomatoSauce();
	}

}

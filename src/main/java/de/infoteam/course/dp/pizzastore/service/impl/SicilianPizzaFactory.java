package de.infoteam.course.dp.pizzastore.service.impl;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PepperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.service.PizzaFactory;

public class SicilianPizzaFactory implements PizzaFactory {

	@Override
	public Pizza createPizza(MenuItem selectedItem) {
		Pizza pizza = null;
		switch (selectedItem) {
		case CHEESE_PIZZA:
			pizza = new CheesePizza(prepareDough(), prepareSauce());
			break;
		case PEPPERONI_PIZZA:
			pizza = new PepperoniPizza(prepareDough(), prepareSauce());
			break;
		case VEGGIE_PIZZA:
			pizza = new VeggiePizza(prepareDough(), prepareSauce());
			break;
		default:
			throw new IllegalStateException("No pizza defined for " + selectedItem);
		}
		return pizza;
	}
	
	private Dough prepareDough() {
		return new ThinCrustyDough();
	}

	private Sauce prepareSauce() {
		return new PlainTomatoSauce();
	}
}

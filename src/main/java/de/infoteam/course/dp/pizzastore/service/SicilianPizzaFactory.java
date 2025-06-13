package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PeperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;

public class SicilianPizzaFactory implements PizzaFactory {

	@Override
	public Pizza createPizza(MenuItem selectedItem) {
		return switch (selectedItem) {
			case CHEESE_PIZZA -> new CheesePizza(prepareDough(), prepareSauce());
			case PEPERONI_PIZZA -> new PeperoniPizza(prepareDough(), prepareSauce());
			case VEGGIE_PIZZA -> new VeggiePizza(prepareDough(), prepareSauce());
		};
	}

	private Dough prepareDough() {
		return new ThinCrustyDough();
	}

	private Sauce prepareSauce() {
		return new PlainTomatoSauce();
	}
}

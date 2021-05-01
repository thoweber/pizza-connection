package de.infoteam.course.dp.pizzastore.model;

import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;

public abstract class AbstractPizza implements Pizza {

	private final Dough dough;
	private final Sauce sauce;

	protected AbstractPizza(Dough dough, Sauce sauce) {
		this.dough = dough;
		this.sauce = sauce;
	}
	
	public Dough getDough() {
		return dough;
	}
	
	public Sauce getSauce() {
		return sauce;
	}
	
}

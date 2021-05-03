package de.infoteam.course.dp.pizzastore.model;

import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;

public abstract class AbstractPizza implements Pizza {

	private final long id;
	private Dough dough;
	private Sauce sauce;

	protected AbstractPizza(long id, Dough dough, Sauce sauce) {
		this.id = id;
		this.dough = dough;
		this.sauce = sauce;
	}
	
	public long getId() {
		return this.id;
	}

	protected Dough getDough() {
		return dough;
	}

	protected Sauce getSauce() {
		return sauce;
	}

}

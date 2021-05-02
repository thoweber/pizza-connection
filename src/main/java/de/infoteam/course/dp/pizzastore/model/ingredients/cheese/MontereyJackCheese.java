package de.infoteam.course.dp.pizzastore.model.ingredients.cheese;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class MontereyJackCheese implements Ingredient {

	private static final String NAME = "Monterey Jack";

	@Override
	public String name() {
		return MontereyJackCheese.NAME;
	}

}

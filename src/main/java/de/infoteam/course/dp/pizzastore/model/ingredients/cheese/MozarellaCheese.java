package de.infoteam.course.dp.pizzastore.model.ingredients.cheese;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class MozarellaCheese implements Ingredient {

	private static final String NAME = "mozarella";

	@Override
	public String name() {
		return MozarellaCheese.NAME;
	}

}

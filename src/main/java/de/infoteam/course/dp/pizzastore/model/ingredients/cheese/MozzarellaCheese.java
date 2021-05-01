package de.infoteam.course.dp.pizzastore.model.ingredients.cheese;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class MozzarellaCheese implements Ingredient {

	private static final String NAME = "mozzarella";

	@Override
	public String name() {
		return MozzarellaCheese.NAME;
	}

}

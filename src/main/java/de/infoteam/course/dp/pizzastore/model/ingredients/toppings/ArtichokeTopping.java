package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class ArtichokeTopping implements Ingredient {

	private static final String NAME = "artichoke hearts";

	@Override
	public String name() {
		return ArtichokeTopping.NAME;
	}

}

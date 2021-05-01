package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class OliveTopping implements Ingredient {

	private static final String NAME = "black Kalamata olives";

	@Override
	public String name() {
		return OliveTopping.NAME;
	}

}

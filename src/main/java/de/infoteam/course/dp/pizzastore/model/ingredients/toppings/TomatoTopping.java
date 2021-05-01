package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class TomatoTopping implements Ingredient {

	private static final String NAME = "freshly sliced tomato";

	@Override
	public String name() {
		return TomatoTopping.NAME;
	}

}

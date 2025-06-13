package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class PeperoniTopping implements Ingredient {

	private static final String NAME = "hot peperoni";

	@Override
	public String name() {
		return PeperoniTopping.NAME;
	}

}

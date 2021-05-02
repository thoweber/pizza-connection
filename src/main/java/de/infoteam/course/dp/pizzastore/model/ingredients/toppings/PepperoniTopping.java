package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class PepperoniTopping implements Ingredient {

	private static final String NAME = "hot pepperoni";

	@Override
	public String name() {
		return PepperoniTopping.NAME;
	}

}

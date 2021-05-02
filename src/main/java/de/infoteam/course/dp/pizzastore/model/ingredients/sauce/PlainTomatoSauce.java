package de.infoteam.course.dp.pizzastore.model.ingredients.sauce;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class PlainTomatoSauce implements Ingredient {

	private static final String NAME = "plain tomato sauce";
	
	@Override
	public String name() {
		return PlainTomatoSauce.NAME;
	}

}

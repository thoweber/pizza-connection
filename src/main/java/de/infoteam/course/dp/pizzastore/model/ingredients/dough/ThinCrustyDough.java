package de.infoteam.course.dp.pizzastore.model.ingredients.dough;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class ThinCrustyDough implements Ingredient {

	private static final String NAME = "thin crusty dough";
	
	@Override
	public String name() {
		return ThinCrustyDough.NAME;
	}

}

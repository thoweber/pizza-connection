package de.infoteam.course.dp.pizzastore.model.ingredients.dough;

public class HandTossedDough implements Dough {

private static final String NAME = "hand tossed dough";
	
	@Override
	public String name() {
		return HandTossedDough.NAME;
	}
	
}

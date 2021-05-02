package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

public class ArtichokeTopping implements Topping {

	private static final String NAME = "artichoke hearts";

	@Override
	public String name() {
		return ArtichokeTopping.NAME;
	}

}

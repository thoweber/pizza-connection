package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

public class OliveTopping implements Topping {

	private static final String NAME = "black Kalamata olives";

	@Override
	public String name() {
		return OliveTopping.NAME;
	}

}

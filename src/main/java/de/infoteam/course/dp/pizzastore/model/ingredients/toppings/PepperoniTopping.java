package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

public class PepperoniTopping implements Topping {

	private static final String NAME = "hot pepperoni";

	@Override
	public String name() {
		return PepperoniTopping.NAME;
	}

}

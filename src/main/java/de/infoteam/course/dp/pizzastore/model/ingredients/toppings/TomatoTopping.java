package de.infoteam.course.dp.pizzastore.model.ingredients.toppings;

public class TomatoTopping implements Topping {

	private static final String NAME = "freshly sliced tomato";

	@Override
	public String name() {
		return TomatoTopping.NAME;
	}

}

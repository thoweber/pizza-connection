package de.infoteam.course.dp.pizzastore.model.ingredients.sauce;

public class PlainTomatoSauce implements Sauce {

	private static final String NAME = "plain tomato sauce";
	
	@Override
	public String name() {
		return PlainTomatoSauce.NAME;
	}

}

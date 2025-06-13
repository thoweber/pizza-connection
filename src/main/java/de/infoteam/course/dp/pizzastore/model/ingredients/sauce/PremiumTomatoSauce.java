package de.infoteam.course.dp.pizzastore.model.ingredients.sauce;

public class PremiumTomatoSauce implements Sauce {

	private static final String NAME = "premium tomato sauce";
	
	@Override
	public String name() {
		return PremiumTomatoSauce.NAME;
	}

}

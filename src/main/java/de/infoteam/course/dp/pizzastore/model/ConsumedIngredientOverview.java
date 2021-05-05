package de.infoteam.course.dp.pizzastore.model;

import java.util.Map;

public interface ConsumedIngredientOverview {
	
	Map<String, Integer> getConsumedCheese();

	Map<String, Integer> getConsumedSauce();

	Map<String, Integer> getConsumedDough();

	Map<String, Integer> getConsumedTopping();
	
}

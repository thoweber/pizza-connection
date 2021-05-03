package de.infoteam.course.dp.pizzastore.controller.dto;

import java.util.HashMap;
import java.util.Map;

import de.infoteam.course.dp.pizzastore.model.ConsumedIngredientOverview;

public class ConsumedIngredientsResponse implements ConsumedIngredientOverview {

	private Map<String, Integer> consumedDough = new HashMap<>();
	private Map<String, Integer> consumedSauce = new HashMap<>();
	private Map<String, Integer> consumedCheese = new HashMap<>();
	private Map<String, Integer> consumedTopping = new HashMap<>();

	@Override
	public Map<String, Integer> getConsumedDough() {
		return consumedDough;
	}

	public void setConsumedDough(Map<String, Integer> consumedDough) {
		this.consumedDough = consumedDough;
	}

	@Override
	public Map<String, Integer> getConsumedSauce() {
		return consumedSauce;
	}

	public void setConsumedSauce(Map<String, Integer> consumedSauce) {
		this.consumedSauce = consumedSauce;
	}

	@Override
	public Map<String, Integer> getConsumedCheese() {
		return consumedCheese;
	}

	public void setConsumedCheese(Map<String, Integer> consumedCheese) {
		this.consumedCheese = consumedCheese;
	}

	@Override
	public Map<String, Integer> getConsumedTopping() {
		return consumedTopping;
	}

	public void setConsumedTopping(Map<String, Integer> consumedTopping) {
		this.consumedTopping = consumedTopping;
	}
	
	public static ConsumedIngredientsResponse of(ConsumedIngredientOverview cio) {
		ConsumedIngredientsResponse response = new ConsumedIngredientsResponse();
		response.setConsumedCheese(cio.getConsumedCheese());
		response.setConsumedDough(cio.getConsumedDough());
		response.setConsumedSauce(cio.getConsumedSauce());
		response.setConsumedTopping(cio.getConsumedTopping());
		return response;
	}

}

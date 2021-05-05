package de.infoteam.course.dp.pizzastore.controller;

import java.util.List;

import de.infoteam.course.dp.pizzastore.controller.dto.ConsumedIngredientsResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.FoodResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.OrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.OrderResponse;

public interface FoodController {

	/**
	 * Orders a pizza.
	 * 
	 * @param orderRequest the request describing the desired pizza
	 * @return a pizza order response
	 */
	OrderResponse order(OrderRequest orderRequest);

	/**
	 * Returns all consumed ingredients.
	 * 
	 * @return all consumed ingredients
	 */
	ConsumedIngredientsResponse consumedIngredients();

	/**
	 * Returns all pizzas which are not yet ready.
	 * 
	 * @return all pizzas which are not yet ready.
	 */
	List<FoodResponse> queue();

	/**
	 * Returns all pizzas which are ready to be picked up.
	 * 
	 * @return all pizzas which are ready to be picked up
	 */
	List<FoodResponse> pickUp();

}

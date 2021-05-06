package de.infoteam.course.dp.pizzastore.controller;

import java.util.List;

import de.infoteam.course.dp.pizzastore.controller.dto.ConsumedIngredientsResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaResponse;

public interface PizzaController {

	/**
	 * Orders a pizza.
	 * 
	 * @param orderRequest the request describing the desired pizza
	 * @return a pizza order response
	 */
	PizzaOrderResponse order(PizzaOrderRequest orderRequest);

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
	public List<PizzaResponse> queue();

	/**
	 * Returns all pizzas which are ready to be picked up.
	 * 
	 * @return all pizzas which are ready to be picked up
	 */
	public List<PizzaResponse> pickUp();

}

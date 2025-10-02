package de.infoteam.course.dp.pizzastore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PizzaController {

  @PostMapping("/close-kitchen")
  void closeKitchen();

  @PostMapping("/order")
  PizzaOrderResponse order(@RequestBody PizzaOrderRequest orderRequest);

  @GetMapping("/consumed-ingredients")
  ConsumedIngredientsResponse consumedIngredients();

	/*
	 * Die Methoden queue() und pickUp() sind für den Remote-Proxy irrelevant,
	 * da sie nicht aus dem Code heraus aufgerufen werden.
	 */
}

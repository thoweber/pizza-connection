package de.infoteam.course.dp.pizzastore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FoodController {

  @PostMapping("/close-kitchen")
  void closeKitchen();

  @PostMapping("/order")
  OrderResponse order(@RequestBody OrderRequest orderRequest);

  @GetMapping("/consumed-ingredients")
  ConsumedIngredientsResponse consumedIngredients();

  @GetMapping("/queue")
  List<FoodResponse> queue();

	/*
	 * Die Methoden queue() und pickUp() sind für den Remote-Proxy irrelevant,
	 * da sie nicht aus dem Code heraus aufgerufen werden.
	 */
}

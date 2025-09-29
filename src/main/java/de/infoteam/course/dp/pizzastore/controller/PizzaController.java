package de.infoteam.course.dp.pizzastore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PizzaController {

  @PostMapping("/close-kitchen")
  void closeKitchen();

  @PostMapping("/order")
  PizzaOrderResponse order(@RequestBody PizzaOrderRequest orderRequest);

  @GetMapping("/consumed-ingredients")
  ConsumedIngredientsResponse consumedIngredients();

  @GetMapping("/queue")
  List<PizzaResponse> queue();

  @GetMapping("/pick-up")
  List<PizzaResponse> pickUp();
}

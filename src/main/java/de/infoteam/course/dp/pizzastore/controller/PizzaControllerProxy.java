package de.infoteam.course.dp.pizzastore.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PizzaControllerProxy implements PizzaController {

	private RestTemplate restTemplate;
	private final String serverAddress = "http://localhost:8080";
	private String closeKitchenRoute = "/close-kitchen";
	private final String orderPizzaRoute = "/order";
	private final String consumedIngredientsRoute = "/consumed-ingredients";
	private final String queueRoute = "/queue";

	public PizzaControllerProxy() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public void closeKitchen() {
		restTemplate.postForLocation(serverAddress + closeKitchenRoute, null);
	}

	@Override
	public PizzaOrderResponse order(PizzaOrderRequest orderRequest) {
		return restTemplate.postForEntity(serverAddress + orderPizzaRoute, orderRequest, PizzaOrderResponse.class)
				.getBody();
	}

	@Override
	public ConsumedIngredientsResponse consumedIngredients() {
		return restTemplate.getForEntity(serverAddress + consumedIngredientsRoute, ConsumedIngredientsResponse.class)
				.getBody();
	}

	@Override
	public List<PizzaResponse> queue() {
		return Arrays.asList(restTemplate.getForEntity(serverAddress + queueRoute, PizzaResponse[].class).getBody());
	}

	@Override
	public List<PizzaResponse> pickUp() {
		return Arrays.asList(restTemplate.getForEntity(serverAddress + queueRoute, PizzaResponse[].class).getBody());
	}

}

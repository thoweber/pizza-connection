package de.infoteam.course.dp.pizzastore.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import de.infoteam.course.dp.pizzastore.controller.dto.ConsumedIngredientsResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaResponse;

public class PizzaControllerProxy implements PizzaController {

	private RestTemplate restTemplate;
	private final String serverAddress = "http://localhost:8080";
	private final String orderPizzaRoute = "/order";
	private final String consumedIngredientsRoute = "/consumed-ingredients";
	private final String queueRoute = "/queue";

	public PizzaControllerProxy() {
		this.restTemplate = new RestTemplate();
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

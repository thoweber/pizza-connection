package de.infoteam.course.dp.pizzastore.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import de.infoteam.course.dp.pizzastore.controller.dto.ConsumedIngredientsResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.FoodResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.OrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.OrderResponse;

public class FoodControllerProxy implements FoodController {

	private RestTemplate restTemplate;
	private final String serverAddress = "http://localhost:8080";
	private final String orderRoute = "/order";
	private final String consumedIngredientsRoute = "/consumed-ingredients";
	private final String queueRoute = "/queue";

	public FoodControllerProxy() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public OrderResponse order(OrderRequest orderRequest) {
		return restTemplate.postForEntity(serverAddress + orderRoute, orderRequest, OrderResponse.class)
				.getBody();
	}

	@Override
	public ConsumedIngredientsResponse consumedIngredients() {
		return restTemplate.getForEntity(serverAddress + consumedIngredientsRoute, ConsumedIngredientsResponse.class)
				.getBody();
	}

	@Override
	public List<FoodResponse> queue() {
		return Arrays.asList(restTemplate.getForEntity(serverAddress + queueRoute, FoodResponse[].class).getBody());
	}

	@Override
	public List<FoodResponse> pickUp() {
		return Arrays.asList(restTemplate.getForEntity(serverAddress + queueRoute, FoodResponse[].class).getBody());
	}

}

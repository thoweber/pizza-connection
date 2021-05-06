package de.infoteam.course.dp.pizzastore.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.infoteam.course.dp.pizzastore.adapter.IngredientLoggerAdapter;
import de.infoteam.course.dp.pizzastore.controller.dto.ConsumedIngredientsResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.FoodResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.OrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.OrderResponse;
import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.repository.DishRepository;
import de.infoteam.course.dp.pizzastore.service.FoodOrderService;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;
import de.infoteam.course.dp.pizzastore.service.impl.GourmetFoodFactory;
import de.infoteam.course.dp.pizzastore.service.impl.SicilianFoodFactory;

@RestController
public class FoodControllerImpl implements FoodController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodControllerImpl.class);

	private FoodOrderService foodOrderService;
	private IngredientLogger ingredientLogger;
	private DishRepository dishRepository;

	public FoodControllerImpl() {
		this.ingredientLogger = new IngredientLogger();
		this.dishRepository = new DishRepository();
		this.foodOrderService = FoodOrderService.builder().gourmetFactory(new GourmetFoodFactory())
				.sicilianFactory(new SicilianFoodFactory()).ingredientLogger(ingredientLogger)
				.pizzaRepository(dishRepository).numberOfChefs(2).build();
	}

	/*
	 * Da wir so wenig Spring wie möglich benutzen, muss hier unser ExecutorService
	 * "pizzaKitchen" im PizzaService beendet werden.
	 */
	@PreDestroy
	void shutdownPizzaService() {
		this.foodOrderService.shutdown();
	}

	@PostMapping("/order")
	public OrderResponse order(@RequestBody OrderRequest orderRequest) {
		LOGGER.info("Received OrderRequest");

		Dish dish = foodOrderService.order(orderRequest.getMenuItem(), orderRequest.getFoodStyle());

		return new OrderResponse().setId(dish.getId()).setName(dish.name())
				.setPizzaStyle(orderRequest.getFoodStyle());
	}

	@GetMapping("/consumed-ingredients")
	public ConsumedIngredientsResponse consumedIngredients() {
		return ConsumedIngredientsResponse.of(new IngredientLoggerAdapter(ingredientLogger));
	}

	@GetMapping("/queue")
	public List<FoodResponse> queue() {
		return this.dishRepository.findAll().stream().filter(p -> p.getState() != State.READY)
				.map(this::toPizzaResponse).sorted(this::comparePizzaResponses).collect(Collectors.toList());
	}

	@GetMapping("/pick-up")
	public List<FoodResponse> pickUp() {
		return this.dishRepository.findAllByState(State.READY).stream().map(this::toPizzaResponse)
				.sorted(this::comparePizzaResponses).collect(Collectors.toList());
	}


	private FoodResponse toPizzaResponse(Dish dish) {
		return new FoodResponse().setId(dish.getId()).setName(dish.name()).setState(dish.getState());
	}
	
	private int comparePizzaResponses(FoodResponse o1, FoodResponse o2) {
		return Long.valueOf(o1.getId()).compareTo(o2.getId());
	}
}

package de.infoteam.course.dp.pizzastore.controller;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.repository.DishRepository;
import de.infoteam.course.dp.pizzastore.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FoodControllerImpl implements FoodController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodControllerImpl.class);

	private final FoodOrderService foodOrderService;
	private final IngredientLogger ingredientLogger;
	private final DishRepository dishRepository; // wird für die neuen REST-Endpunkte benötigt

	public FoodControllerImpl(DishRepository dishRepository) {
		this.ingredientLogger = new IngredientLogger();
		this.dishRepository = dishRepository;
		this.foodOrderService = FoodOrderService.builder().gourmetFactory(new GourmetFoodFactory())
				.sicilianFactory(new SicilianFoodFactory()).ingredientLogger(ingredientLogger)
				.pizzaRepository(dishRepository).numberOfChefs(2)
				.build();
	}

	@PostMapping("/close-kitchen")
	public void closeKitchen() {
		this.foodOrderService.shutdown();
	}

	@PostMapping("/order")
	@Override
	public OrderResponse order(@RequestBody OrderRequest orderRequest) {
		LOGGER.info("Received PizzaOrderRequest");
		
		Dish pizza = foodOrderService.order(orderRequest.getMenuItem(), orderRequest.getPizzaStyle());
		
		return new OrderResponse().setId(pizza.getId()).setName(pizza.name())
				.setPizzaStyle(orderRequest.getPizzaStyle());
	}

	@GetMapping("/consumed-ingredients")
	@Override
	public ConsumedIngredientsResponse consumedIngredients() {
	    return ConsumedIngredientsResponse.of(new IngredientLoggerAdapter(ingredientLogger));
	}

	@GetMapping("/queue")
	@Override
	public List<FoodResponse> queue() {
		return this.dishRepository.findAll().stream().filter(p -> p.getState() != State.READY).map(this::toFoodResponse)
				.collect(Collectors.toList());
	}

	@GetMapping("/pick-up")
	public List<FoodResponse> pickUp() {
		return this.dishRepository.findAllByState(State.READY).stream().map(this::toFoodResponse).collect(Collectors.toList());
	}

	private FoodResponse toFoodResponse(Dish dish) {
		return new FoodResponse().setId(dish.getId()).setName(dish.name()).setState(dish.getState());
	}
}

package de.infoteam.course.dp.pizzastore.controller;

import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.repository.PizzaRepository;
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
public class PizzaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	private final PizzaService pizzaService;
	private final IngredientLogger ingredientLogger;
	private final PizzaRepository pizzaRepository; // wird für die neuen REST-Endpunkte benötigt

	public PizzaController(PizzaRepository pizzaRepository) {
		this.ingredientLogger = new IngredientLogger();
		this.pizzaRepository = pizzaRepository;
		this.pizzaService = PizzaService.builder().gourmetFactory(new GourmetPizzaFactory())
				.sicilianFactory(new SicilianPizzaFactory()).ingredientLogger(ingredientLogger)
				.pizzaRepository(pizzaRepository).numberOfChefs(2)
				.build();
	}

	public void closeKitchen() {
		this.pizzaService.shutdown();
	}

	@PostMapping("/order")
	public PizzaOrderResponse order(@RequestBody PizzaOrderRequest orderRequest) {
		LOGGER.info("Received PizzaOrderRequest");
		
		Pizza pizza = pizzaService.order(orderRequest.getMenuItem(), orderRequest.getPizzaStyle());
		
		return new PizzaOrderResponse().setId(pizza.getId()).setName(pizza.name())
				.setPizzaStyle(orderRequest.getPizzaStyle());
	}

	@GetMapping("/consumed-ingredients")
	public ConsumedIngredientsResponse consumedIngredients() {
	    return ConsumedIngredientsResponse.of(new IngredientLoggerAdapter(ingredientLogger));
	}

	@GetMapping("/queue")
	public List<PizzaResponse> queue() {
		return this.pizzaRepository.findAll().stream().filter(p -> p.getState() != State.READY).map(this::toPizzaResponse)
				.collect(Collectors.toList());
	}

	@GetMapping("/pick-up")
	public List<PizzaResponse> pickUp() {
		return this.pizzaRepository.findAllByState(State.READY).stream().map(this::toPizzaResponse).collect(Collectors.toList());
	}

	private PizzaResponse toPizzaResponse(Pizza pizza) {
		return new PizzaResponse().setId(pizza.getId()).setName(pizza.name()).setState(pizza.getState());
	}
}

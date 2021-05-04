package de.infoteam.course.dp.pizzastore.controller;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.infoteam.course.dp.pizzastore.adapter.IngredientLoggerAdapter;
import de.infoteam.course.dp.pizzastore.controller.dto.ConsumedIngredientsResponse;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderResponse;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.repository.PizzaRepository;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;
import de.infoteam.course.dp.pizzastore.service.PizzaService;
import de.infoteam.course.dp.pizzastore.service.impl.GourmetPizzaFactory;
import de.infoteam.course.dp.pizzastore.service.impl.SicilianPizzaFactory;

@RestController
public class PizzaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	private PizzaService pizzaService;
	private IngredientLogger ingredientLogger;
	private PizzaRepository pizzaRepository;

	public PizzaController() {
		this.ingredientLogger = new IngredientLogger();
		this.pizzaRepository = new PizzaRepository();
		this.pizzaService = PizzaService.builder().gourmetFactory(new GourmetPizzaFactory())
				.sicilianFactory(new SicilianPizzaFactory()).ingredientLogger(ingredientLogger)
				.pizzaRepository(pizzaRepository).numberOfChefs(2)
				.build();
	}

	/*
	 * Da wir so wenig Spring wie möglich benutzen, muss hier unser ExecutorService
	 * "pizzaKitchen" im PizzaService beendet werden.
	 */
	@PreDestroy
	void shutdownPizzaService() {
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

	/*
	 * Schaffe unter "/queue" eine Schnittstelle für alle in Bearbeitung
	 * befindlichen Pizzen.
	 */

	/*
	 * Schaffe unter "/pick-up" eine Schnittstelle für alle fertigen Bestellungen.
	 */
}

package de.infoteam.course.dp.pizzastore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderResponse;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;
import de.infoteam.course.dp.pizzastore.service.PizzaService;
import de.infoteam.course.dp.pizzastore.service.impl.GourmetPizzaFactory;
import de.infoteam.course.dp.pizzastore.service.impl.SicilianPizzaFactory;

@RestController
public class PizzaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	private PizzaService pizzaService;
	private IngredientLogger ingredientLogger;

	public PizzaController() {
		this.ingredientLogger = new IngredientLogger();
		this.pizzaService = PizzaService.builder().gourmetFactory(new GourmetPizzaFactory())
				.sicilianFactory(new SicilianPizzaFactory()).ingredientLogger(ingredientLogger).build();
	}

	@PostMapping("/order")
	public PizzaOrderResponse order(@RequestBody PizzaOrderRequest orderRequest) {
		LOGGER.info("Received PizzaOrderRequest");
		
		Pizza pizza = pizzaService.order(orderRequest.getMenuItem(), orderRequest.getPizzaStyle());
		
		return new PizzaOrderResponse().setId(pizza.getId()).setName(pizza.name())
				.setPizzaStyle(orderRequest.getPizzaStyle());
	}

	@GetMapping("/consumed-ingredients")
	public Object consumedIngredients() {
		/*
		 * REST-Schnittstelle zum Abrufen der verbrauchten Zutaten erstellen
		 */
		return null;
	}
}

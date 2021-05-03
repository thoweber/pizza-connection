package de.infoteam.course.dp.pizzastore;

import static de.infoteam.course.dp.pizzastore.Console.println;
import static de.infoteam.course.dp.pizzastore.Console.prompt;
import static de.infoteam.course.dp.pizzastore.Console.showBanner;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderRequest;
import de.infoteam.course.dp.pizzastore.controller.dto.PizzaOrderResponse;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;

/**
 * the PizzaStore order console.
 * 
 * @author Thomas Weber
 */
@Component
public final class PizzaStoreApp implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaStoreApp.class);
	private final AtomicBoolean running = new AtomicBoolean(true);

	// Spring Application Context --> wird zum Stoppen der Anwendung benötigt
	private ApplicationContext appContext;

	/* REST Konstanten */
	private RestTemplate restTemplate;
	private final String serverAddress = "http://localhost:8080";
	private final String orderPizzaRoute = "/order";

	public PizzaStoreApp(ApplicationContext appContext) {
		this.appContext = appContext;
		this.restTemplate = new RestTemplate();
	}

	@Override
	public void run(String... args) {

		while (running.get()) {
			showBanner();
			askForOrder().ifPresent(selectedItem -> chooseStyle(selectedItem).ifPresent(pizzaStyle -> {
				PizzaOrderResponse response = orderPizza(selectedItem, pizzaStyle);
				println("Received order #" + response.getId() + " " + response.getFullName());
				prompt("Press enter...");
			}));
		}

		println("Store is closed.");

		println("===================================");
		println("Consumed Ingredients:");

		/*
		 * Hier müssen wir noch die verbrauchten Zutaten vom Server holen
		 */

		// Stoppt die Spring Anwendung
		SpringApplication.exit(appContext, () -> 0);
	}

	private Optional<MenuItem> askForOrder() {
		println();
		println("Our menu for today:");
		Stream.of(MenuItem.values()).forEach(menu -> println((menu.ordinal() + 1) + "\t" + menu.getName()));
		println("q\tto quit the app");
		println();
		String choice = prompt("Your choice: ");
		return choiceToEnumValue(choice, MenuItem.values(), "q");
	}

	private Optional<PizzaStyle> chooseStyle(MenuItem selectedItem) {
		println("Choose your style for " + selectedItem.getName() + ":");
		Stream.of(PizzaStyle.values()).forEach(style -> println((style.ordinal() + 1) + "\t" + style.getName()));
		println();
		String choice = prompt("Your style: ");
		return choiceToEnumValue(choice, PizzaStyle.values(), null);
	}

	<T extends Enum<T>> Optional<T> choiceToEnumValue(String choice, T[] enumValues, String quit) {
		if (quit != null && quit.equals(choice)) {
			running.set(false);
			return Optional.empty();
		}
		try {
			int index = Integer.parseInt(choice);
			if (index > 0 && index <= enumValues.length) {
				T menuItem = enumValues[index - 1];
				return Optional.of(menuItem);
			}
		} catch (NumberFormatException e) {
			LOGGER.debug("Cannot parse number {}:", e.getMessage());
		}
		println("Sorry, I do not undestand " + choice);
		return Optional.empty();
	}

	/*
	 * REST-bezogene Methoden
	 */
	private PizzaOrderResponse orderPizza(MenuItem selectedItem, PizzaStyle pizzaStyle) {
		PizzaOrderRequest request = new PizzaOrderRequest().setMenuItem(selectedItem).setPizzaStyle(pizzaStyle);
		return restTemplate.postForEntity(serverAddress + orderPizzaRoute, request, PizzaOrderResponse.class).getBody();
	}
}

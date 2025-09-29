package de.infoteam.course.dp.pizzastore;

import static de.infoteam.course.dp.pizzastore.Console.println;
import static de.infoteam.course.dp.pizzastore.Console.prompt;
import static de.infoteam.course.dp.pizzastore.Console.showBanner;

import de.infoteam.course.dp.pizzastore.controller.*;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.FoodStyle;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * the PizzaStore order console.
 *
 * @author Thomas Weber
 */
@Component
public final class PizzaStoreApp implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaStoreApp.class);
	private final AtomicBoolean running = new AtomicBoolean(true);
	private final FoodControllerProxy pizzaControllerProxy;

	// Spring Application Context --> wird zum Stoppen der Anwendung benötigt
	private ApplicationContext appContext;

	public PizzaStoreApp(ApplicationContext appContext, FoodControllerProxy pizzaControllerProxy) {
		this.appContext = appContext;
		this.pizzaControllerProxy = pizzaControllerProxy;
	}

	@Override
	public void run(String... args) {

		while (running.get()) {
			showBanner();
			askForOrder().ifPresent(selectedItem -> chooseStyle(selectedItem).ifPresent(pizzaStyle -> {
				OrderResponse response = pizzaControllerProxy.order(new OrderRequest()
						.setMenuItem(selectedItem).setPizzaStyle(pizzaStyle));
				println("Received order #" + response.getId() + " " + response.getFullName());
				println(pizzaControllerProxy.queue().size() + " pizzas are currently in queue");
				println();
				prompt("Press enter...");
			}));
		}

		println("The kitchen is now closing... Pending orders will be finished...");
		this.pizzaControllerProxy.closeKitchen();

		println("Store is closed.");

		println("===================================");
		println("Consumed Ingredients:");
		printShoppingList(pizzaControllerProxy.consumedIngredients());

		// Stoppt die Spring Anwendung
		SpringApplication.exit(appContext, () -> 0);
	}

	private void printShoppingList(ConsumedIngredientsResponse cir) {
		if (cir == null) {
			println("Error: no consumed ingredients retrieved");
			return;
		}
		println("Dough:");
		outputAggregation(cir.dough());
		println("Sauce:");
		outputAggregation(cir.sauce());
		println("Cheese:");
		outputAggregation(cir.cheese());
		println("Toppings:");
		outputAggregation(cir.toppings());
	}

	private void outputAggregation(Map<String, Integer> consumed) {
		consumed.forEach((key, value) -> println("\t" + value + "x\t" + key));
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

	private Optional<FoodStyle> chooseStyle(MenuItem selectedItem) {
		println("Choose your style for " + selectedItem.getName() + ":");
		Stream.of(FoodStyle.values()).forEach(style -> println((style.ordinal() + 1) + "\t" + style.getName()));
		println();
		String choice = prompt("Your style: ");
		return choiceToEnumValue(choice, FoodStyle.values(), null);
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

}

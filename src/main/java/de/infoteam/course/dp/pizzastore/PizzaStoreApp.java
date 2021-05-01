package de.infoteam.course.dp.pizzastore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.PizzaService;

public class PizzaStoreApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaStoreApp.class);
	private static final AtomicBoolean RUNNING = new AtomicBoolean(true);

	private static PizzaService pizzaService;

	public static void main(String[] args) {
		pizzaService = new PizzaService();
		
		while (RUNNING.get()) {
			showBanner();
			askForOrder().ifPresent(pizzaService::order);
		}
		
		System.out.println("Store is closed.");
	}

	private static Optional<Pizza> askForOrder() {
		List<Pizza> dishes = pizzaService.getDishes();

		printMenu(dishes);
		String choice = readConsoleInput();

		if ("q".equals(choice)) {
			RUNNING.set(false);
			return Optional.empty();
		}
		try {
			int index = Integer.parseInt(choice);
			if (index > 0 && index <= dishes.size()) {
				Pizza dish = dishes.get(index - 1);
				System.out.println("Will order " + dish.name() + " for you...");
				return Optional.of(dish);
			} else {
				System.out.println("Sorry, no dish for " + choice);
			}
		} catch (NumberFormatException e) {
			System.out.println("Sorry, no dish for " + choice);
		}
		return Optional.empty();
	}

	private static void printMenu(List<Pizza> dishes) {
		System.out.println();
		System.out.println("Our menu for today:");
		for (int i = 0; i < dishes.size(); i++) {
			System.out.println((i + 1) + "\t" + dishes.get(i).name());
		}
		System.out.println("q\tto quit the app");
		System.out.println();
	}

	private static String readConsoleInput() {
		System.out.print("Your choice: ");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine().trim();
		} catch (IOException e) {
			LOGGER.error("Error reading user input", e);
			System.exit(1);
			return "";
		} finally {
			System.out.println();
		}
	}

	private static void showBanner() {
		try (Stream<String> lines = Files.lines(
				Paths.get(PizzaStoreApp.class.getClassLoader().getResource("banner.txt").toURI()),
				StandardCharsets.UTF_8)) {
			lines.forEach(System.out::println);
		} catch (Exception e) {
			LOGGER.error("Cannot show store banner", e);
		}
	}

}

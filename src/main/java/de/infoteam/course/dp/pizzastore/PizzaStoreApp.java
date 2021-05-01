package de.infoteam.course.dp.pizzastore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.service.PizzaService;
import de.infoteam.course.dp.pizzastore.service.impl.ConcretePizzaFactory;

/**
 * the PizzaStore application.
 * 
 * @author Thomas Weber
 */
public final class PizzaStoreApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaStoreApp.class);
	private static final AtomicBoolean RUNNING = new AtomicBoolean(true);
	private static final PrintStream OUTPUT = System.out;

	private static PizzaService pizzaService;

	public static void main(String[] args) {
		pizzaService = new PizzaService(new ConcretePizzaFactory());

		while (RUNNING.get()) {
			showBanner();
			printMenu();
			askForOrder().ifPresent(pizzaService::order);
		}

		println("Store is closed.");
	}

	private static Optional<MenuItem> askForOrder() {
		String choice = readConsoleInput();
		return menuItemForChoice(choice);
	}

	static Optional<MenuItem> menuItemForChoice(String choice) {
		if ("q".equals(choice)) {
			RUNNING.set(false);
			return Optional.empty();
		}
		try {
			int index = Integer.parseInt(choice);
			if (index > 0 && index <= MenuItem.values().length) {
				MenuItem menuItem = MenuItem.values()[index - 1];
				println("Will order " + menuItem.getName() + " for you...");
				return Optional.of(menuItem);
			}
		} catch (NumberFormatException e) {
			LOGGER.debug("Cannot parse number {}:", e.getMessage());
		}
		println("Sorry, no menu item for " + choice);
		return Optional.empty();
	}

	private static void printMenu() {
		println();
		println("Our menu for today:");
		Stream.of(MenuItem.values()).forEach(menu -> println((menu.ordinal() + 1) + "\t" + menu.getName()));
		println("q\tto quit the app");
		println();
	}

	private static String readConsoleInput() {
		print("Your choice: ");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine().trim();
		} catch (IOException e) {
			LOGGER.error("Error reading user input", e);
			System.exit(1);
			return "";
		} finally {
			println();
		}
	}

	private static void showBanner() {
		try (Stream<String> lines = Files.lines(
				Paths.get(PizzaStoreApp.class.getClassLoader().getResource("banner.txt").toURI()),
				StandardCharsets.UTF_8)) {
			lines.forEach(PizzaStoreApp::println);
		} catch (Exception e) {
			LOGGER.error("Cannot show store banner", e);
		}
	}

	private static void print(String output) {
		OUTPUT.print(output);
	}

	private static void println() {
		println("");
	}

	private static void println(String output) {
		OUTPUT.println(output);
	}
}

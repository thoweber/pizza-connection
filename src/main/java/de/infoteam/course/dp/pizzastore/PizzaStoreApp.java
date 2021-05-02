package de.infoteam.course.dp.pizzastore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.PizzaStyle;
import de.infoteam.course.dp.pizzastore.service.PizzaService;
import de.infoteam.course.dp.pizzastore.service.impl.GourmetPizzaFactory;
import de.infoteam.course.dp.pizzastore.service.impl.SicilianPizzaFactory;

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
		pizzaService = PizzaService.builder().gourmetFactory(new GourmetPizzaFactory())
				.sicilianFactory(new SicilianPizzaFactory()).build();

		while (RUNNING.get()) {
			showBanner();
			askForOrder().ifPresent(selectedItem -> chooseStyle(selectedItem)
					.ifPresent(pizzaStyle -> pizzaService.order(selectedItem, pizzaStyle)));
		}

		println("Store is closed.");
	}

	private static Optional<MenuItem> askForOrder() {
		println();
		println("Our menu for today:");
		Stream.of(MenuItem.values()).forEach(menu -> println((menu.ordinal() + 1) + "\t" + menu.getName()));
		println("q\tto quit the app");
		println();
		String choice = readConsoleInput();
		return choiceToEnumValue(choice, MenuItem.values(), "q");
	}

	private static Optional<PizzaStyle> chooseStyle(MenuItem selectedItem) {
		println("Choose your style for " + selectedItem.getName() + ":");
		Stream.of(PizzaStyle.values()).forEach(style -> println((style.ordinal() + 1) + "\t" + style.getName()));
		println();
		String choice = readConsoleInput();
		return choiceToEnumValue(choice, PizzaStyle.values(), null);
	}

	static <T extends Enum<T>> Optional<T> choiceToEnumValue(String choice, T[] enumValues, String quit) {
		if (quit != null && quit.equals(choice)) {
			RUNNING.set(false);
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

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				PizzaStoreApp.class.getClassLoader().getResourceAsStream("banner.txt"), StandardCharsets.UTF_8))) {
			String line = reader.readLine();
			while (line != null) {
				println(line);
				line = reader.readLine();
			}
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

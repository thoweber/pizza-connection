package de.infoteam.course.dp.pizzastore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Console {

	private static final Logger LOGGER = LoggerFactory.getLogger(Console.class);
	private static final PrintStream OUT = System.out;

	public static void clear() {
		try {
			String operatingSystem = System.getProperty("os.name");

			if (operatingSystem.contains("Windows")) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();

				startProcess.waitFor();
			}
		} catch (Exception e) {
			LOGGER.error("Error clearing console", e);
		}
	}

	public static void print(String output) {
		OUT.print(output);
	}

	public static void println() {
		println("");
	}

	public static void println(String output) {
		OUT.println(output);
	}

	public static PrintStream printStream() {
		return OUT;
	}

	public static String prompt(String prompt) {
		print(prompt);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine().trim();
		} catch (IOException e) {
			LOGGER.error("Error reading user input", e);
			return "";
		} finally {
			println();
		}
	}

	public static void showBanner() {
		clear();
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

}

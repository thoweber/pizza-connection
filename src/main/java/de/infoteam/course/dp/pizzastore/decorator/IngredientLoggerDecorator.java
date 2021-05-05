package de.infoteam.course.dp.pizzastore.decorator;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.Cheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.Topping;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;
import de.infoteam.course.dp.pizzastore.service.ShoppingListLogger;

public class IngredientLoggerDecorator implements ShoppingListLogger<Ingredient> {

	private final IngredientLogger logger;
	private Map<String, Integer> consumedDough = new HashMap<>();
	private Map<String, Integer> consumedSauce = new HashMap<>();
	private Map<String, Integer> consumedCheese = new HashMap<>();
	private Map<String, Integer> consumedTopping = new HashMap<>();

	// visible for testing
	Map<String, Integer> getConsumedCheese() {
		return Collections.unmodifiableMap(consumedCheese);
	}

	// visible for testing
	Map<String, Integer> getConsumedSauce() {
		return Collections.unmodifiableMap(consumedSauce);
	}

	// visible for testing
	Map<String, Integer> getConsumedDough() {
		return Collections.unmodifiableMap(consumedDough);
	}

	// visible for testing
	Map<String, Integer> getConsumedTopping() {
		return Collections.unmodifiableMap(consumedTopping);
	}

	public IngredientLoggerDecorator(IngredientLogger logger) {
		this.logger = logger;
	}

	public void logIngredient(Ingredient ingredient) {
		logger.logIngredient(ingredient);
	}

	@Override
	public List<Ingredient> getConsumedIngredients() {
		return Collections.unmodifiableList(this.logger.getConsumedIngredients());
	}

	public void printShoppingList(OutputStream outputStream) {
		aggregateIngredientsInMaps();
		PrintStream ps = new PrintStream(outputStream);

		ps.println("Dough:");
		outputAggregation(this.consumedDough, ps);
		ps.println("Sauce:");
		outputAggregation(this.consumedSauce, ps);
		ps.println("Cheese:");
		outputAggregation(this.consumedCheese, ps);
		ps.println("Toppings:");
		outputAggregation(this.consumedTopping, ps);
	}

	private void outputAggregation(Map<String, Integer> consumed, PrintStream ps) {
		consumed.entrySet().stream().forEach(entry -> ps.println("\t" + entry.getValue() + "x\t" + entry.getKey()));
	}

	private void aggregateIngredientsInMaps() {
		this.consumedDough.clear();
		logger.getConsumedIngredients().stream().filter(Dough.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedDough.merge(name, 1, (prev, current) -> prev + current));
		this.consumedSauce.clear();
		logger.getConsumedIngredients().stream().filter(Sauce.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedSauce.merge(name, 1, (prev, current) -> prev + current));
		this.consumedCheese.clear();
		logger.getConsumedIngredients().stream().filter(Cheese.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedCheese.merge(name, 1, (prev, current) -> prev + current));
		this.consumedTopping.clear();
		logger.getConsumedIngredients().stream().filter(Topping.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedTopping.merge(name, 1, (prev, current) -> prev + current));
	}

}

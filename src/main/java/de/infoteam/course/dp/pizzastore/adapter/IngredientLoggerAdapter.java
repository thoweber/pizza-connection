package de.infoteam.course.dp.pizzastore.adapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.infoteam.course.dp.pizzastore.model.ConsumedIngredientOverview;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.Cheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.Topping;
import de.infoteam.course.dp.pizzastore.service.IngredientLogger;

public class IngredientLoggerAdapter implements ConsumedIngredientOverview {

	private Map<String, Integer> consumedDough = new HashMap<>();
	private Map<String, Integer> consumedSauce = new HashMap<>();
	private Map<String, Integer> consumedCheese = new HashMap<>();
	private Map<String, Integer> consumedTopping = new HashMap<>();

	public IngredientLoggerAdapter(IngredientLogger logger) {
		logger.getConsumedIngredients().stream().filter(Dough.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedDough.merge(name, 1, (prev, current) -> prev + current));
		logger.getConsumedIngredients().stream().filter(Sauce.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedSauce.merge(name, 1, (prev, current) -> prev + current));
		logger.getConsumedIngredients().stream().filter(Cheese.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedCheese.merge(name, 1, (prev, current) -> prev + current));
		logger.getConsumedIngredients().stream().filter(Topping.class::isInstance).map(Ingredient::name)
				.forEach(name -> this.consumedTopping.merge(name, 1, (prev, current) -> prev + current));
	}

	public Map<String, Integer> getConsumedCheese() {
		return Collections.unmodifiableMap(consumedCheese);
	}

	public Map<String, Integer> getConsumedSauce() {
		return Collections.unmodifiableMap(consumedSauce);
	}

	public Map<String, Integer> getConsumedDough() {
		return Collections.unmodifiableMap(consumedDough);
	}

	public Map<String, Integer> getConsumedTopping() {
		return Collections.unmodifiableMap(consumedTopping);
	}

//	public void printShoppingList(OutputStream outputStream) {
//		aggregateIngredientsInMaps();
//		PrintStream ps = new PrintStream(outputStream);
//
//		ps.println("Dough:");
//		outputAggregation(this.consumedDough, ps);
//		ps.println("Sauce:");
//		outputAggregation(this.consumedSauce, ps);
//		ps.println("Cheese:");
//		outputAggregation(this.consumedCheese, ps);
//		ps.println("Toppings:");
//		outputAggregation(this.consumedTopping, ps);
//	}
//
//	private void outputAggregation(Map<String, Integer> consumed, PrintStream ps) {
//		consumed.entrySet().stream().forEach(entry -> ps.println("\t" + entry.getValue() + "x\t" + entry.getKey()));
//	}
//
//	private void aggregateIngredientsInMaps() {
//		logger.getConsumedIngredients().stream().filter(Dough.class::isInstance).map(Ingredient::name)
//				.forEach(name -> this.consumedDough.merge(name, 1, (prev, current) -> prev + current));
//		logger.getConsumedIngredients().stream().filter(Sauce.class::isInstance).map(Ingredient::name)
//				.forEach(name -> this.consumedSauce.merge(name, 1, (prev, current) -> prev + current));
//		logger.getConsumedIngredients().stream().filter(Cheese.class::isInstance).map(Ingredient::name)
//				.forEach(name -> this.consumedCheese.merge(name, 1, (prev, current) -> prev + current));
//		logger.getConsumedIngredients().stream().filter(Topping.class::isInstance).map(Ingredient::name)
//				.forEach(name -> this.consumedTopping.merge(name, 1, (prev, current) -> prev + current));
//	}

}

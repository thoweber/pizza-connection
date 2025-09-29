package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class IngredientLogger implements ShoppingListLogger<Ingredient> {

  private final List<Ingredient> consumedIngredients = new CopyOnWriteArrayList<>();

	public void logIngredient(Ingredient ingredient) {
		this.consumedIngredients.add(ingredient);
	}
	
	public List<Ingredient> getConsumedIngredients() {
		return Collections.unmodifiableList(consumedIngredients);
	}
	
	public void printShoppingList(OutputStream outputStream) {
		PrintStream ps = new PrintStream(outputStream);
		consumedIngredients.stream().map(Ingredient::name).forEach(ps::println);
	}
}

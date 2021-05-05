package de.infoteam.course.dp.pizzastore.service;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.infoteam.course.dp.pizzastore.model.Ingredient;

public class IngredientLogger implements ShoppingListLogger<Ingredient> {

	private final List<Ingredient> consumedIngredients = new ArrayList<>();
	
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

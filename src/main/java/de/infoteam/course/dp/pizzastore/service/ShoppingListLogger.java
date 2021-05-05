package de.infoteam.course.dp.pizzastore.service;

import java.io.OutputStream;
import java.util.List;

public interface ShoppingListLogger<T> {

	void logIngredient(T ingredient);
	
	List<T> getConsumedIngredients();
	
	void printShoppingList(OutputStream outputStream);
	
}

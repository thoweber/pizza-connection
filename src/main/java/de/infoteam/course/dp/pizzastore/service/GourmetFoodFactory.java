package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PeperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.TomatoSalad;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PremiumTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.ArtichokeTopping;

public class GourmetFoodFactory implements FoodFactory {

	@Override
	public Dish createDish(MenuItem selectedItem, long id) {
		return switch (selectedItem) {
			case CHEESE_PIZZA -> new CheesePizza(id, prepareDough(), prepareSauce());
			case PEPERONI_PIZZA -> new PeperoniPizza(id, prepareDough(), prepareSauce());
			case VEGGIE_PIZZA -> new VeggiePizza(id, prepareDough(), prepareSauce());
			case TOMATO_SALAD -> new TomatoSalad(id, prepareExtraIngredients());
		};
	}

	private Dough prepareDough() {
		return new HandTossedDough();
	}

	private Sauce prepareSauce() {
		return new PremiumTomatoSauce();
	}

	private Ingredient[] prepareExtraIngredients() {
		return new Ingredient[] { new ArtichokeTopping(), new MozzarellaCheese() };
	}
}

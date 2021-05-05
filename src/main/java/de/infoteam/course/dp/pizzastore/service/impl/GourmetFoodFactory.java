package de.infoteam.course.dp.pizzastore.service.impl;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.MenuItem;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PepperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.TomatoSalad;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.HandTossedDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PremiumTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.ArtichokeTopping;
import de.infoteam.course.dp.pizzastore.service.FoodFactory;

public class GourmetFoodFactory implements FoodFactory {

	@Override
	public Dish createDish(MenuItem selectedItem, long id) {
		Dish dish = null;
		switch (selectedItem) {
		case CHEESE_PIZZA:
			dish = new CheesePizza(id, prepareDough(), prepareSauce());
			break;
		case PEPPERONI_PIZZA:
			dish = new PepperoniPizza(id, prepareDough(), prepareSauce());
			break;
		case VEGGIE_PIZZA:
			dish = new VeggiePizza(id, prepareDough(), prepareSauce());
			break;
		case TOMATO_SALAD:
			dish = new TomatoSalad(id, prepareExtraIngredients());
			break;
		default:
			throw new IllegalStateException("No dish defined for " + selectedItem);
		}
		return dish;
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

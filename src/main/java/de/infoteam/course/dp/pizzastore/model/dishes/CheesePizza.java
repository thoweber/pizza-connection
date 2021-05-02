package de.infoteam.course.dp.pizzastore.model.dishes;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.infoteam.course.dp.pizzastore.model.AbstractPizza;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MozzarellaCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;

public class CheesePizza extends AbstractPizza {

	private static final String NAME = "cheese pizza (formerly known as default)";

	private final List<Ingredient> ingredients = new ArrayList<>();

	public CheesePizza(Dough dough, Sauce sauce) {
		super(dough, sauce);
	}
	
	@Override
	public void addIngredients() {
		this.ingredients.add(getDough());
		this.ingredients.add(getSauce());
		this.ingredients.add(new MozzarellaCheese());
	}

	@Override
	public List<Ingredient> getIngredients() {
		return Collections.unmodifiableList(this.ingredients);
	}


	@Override
	public Duration getBakingDuration() {
		return Duration.ofMinutes(6);
	}

	@Override
	public int getBakingTemperature() {
		return 337;
	}

	@Override
	public String name() {
		return CheesePizza.NAME;
	}
	
}

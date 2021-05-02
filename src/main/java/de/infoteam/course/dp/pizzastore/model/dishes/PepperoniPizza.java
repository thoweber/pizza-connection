package de.infoteam.course.dp.pizzastore.model.dishes;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.PepperoniTopping;

public class PepperoniPizza  implements Pizza {
	
	private static final String NAME = "pepperoni pizza";

	private final List<Ingredient> ingredients = new ArrayList<>();

	@Override
	public void addIngredients() {
		this.ingredients.add(new ThinCrustyDough());
		this.ingredients.add(new PlainTomatoSauce());
		this.ingredients.add(new MontereyJackCheese());
		this.ingredients.add(new PepperoniTopping());
	}

	@Override
	public List<Ingredient> getIngredients() {
		return Collections.unmodifiableList(this.ingredients);
	}


	@Override
	public Duration getBakingDuration() {
		return Duration.ofMinutes(7);
	}

	@Override
	public int getBakingTemperature() {
		return 315;
	}

	@Override
	public String name() {
		return PepperoniPizza.NAME;
	}
	
}

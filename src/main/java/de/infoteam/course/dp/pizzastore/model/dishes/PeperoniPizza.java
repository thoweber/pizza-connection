package de.infoteam.course.dp.pizzastore.model.dishes;

import de.infoteam.course.dp.pizzastore.model.AbstractPizza;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.cheese.MontereyJackCheese;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.PeperoniTopping;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeperoniPizza extends AbstractPizza {
	
	private static final String NAME = "peperoni pizza";

	private final List<Ingredient> ingredients = new ArrayList<>();

	public PeperoniPizza(Dough dough, Sauce sauce) {
		super(dough, sauce);
	}

	@Override
	public void addIngredients() {
		this.ingredients.add(getDough());
		this.ingredients.add(getSauce());
		this.ingredients.add(new MontereyJackCheese());
		this.ingredients.add(new PeperoniTopping());
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
		return PeperoniPizza.NAME;
	}
	
}

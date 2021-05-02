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
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.ArtichokeTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.OliveTopping;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.TomatoTopping;

public class VeggiePizza extends AbstractPizza {

	private static final String NAME = "veggie pizza";

	private final List<Ingredient> ingredients = new ArrayList<>();

	public VeggiePizza(Dough dough, Sauce sauce) {
		super(dough, sauce);
	}
	
	@Override
	public void addIngredients() {
		this.ingredients.add(getDough());
		this.ingredients.add(getSauce());
		this.ingredients.add(new MozzarellaCheese());
		this.ingredients.add(new TomatoTopping());
		this.ingredients.add(new ArtichokeTopping());
		this.ingredients.add(new OliveTopping());
	}

	@Override
	public List<Ingredient> getIngredients() {
		return Collections.unmodifiableList(this.ingredients);
	}

	@Override
	public Duration getBakingDuration() {
		return Duration.ofMinutes(8);
	}

	@Override
	public int getBakingTemperature() {
		return 300;
	}

	@Override
	public String name() {
		return VeggiePizza.NAME;
	}

}

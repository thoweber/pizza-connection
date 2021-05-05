package de.infoteam.course.dp.pizzastore.model.dishes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.infoteam.course.dp.pizzastore.model.AbstractDish;
import de.infoteam.course.dp.pizzastore.model.Ingredient;
import de.infoteam.course.dp.pizzastore.model.Salad;
import de.infoteam.course.dp.pizzastore.model.ingredients.toppings.TomatoTopping;

public class TomatoSalad extends AbstractDish implements Salad {

	private static final String NAME = "mediterranean tomato salad";
	
	private final List<Ingredient> ingredients = new ArrayList<>();
	private final List<Ingredient> extraIngredients;
	
	
	public TomatoSalad(long id, Ingredient... extraIngredients) {
		super(id);
		this.extraIngredients = Arrays.asList(extraIngredients);
	}

	@Override
	public String name() {
		return NAME;
	}

	@Override
	public void addIngredients() {
		this.ingredients.add(new TomatoTopping());
		this.ingredients.add(new TomatoTopping());
		this.ingredients.add(new TomatoTopping());
		this.ingredients.addAll(this.extraIngredients);
	}

	@Override
	public List<Ingredient> getIngredients() {
		return Collections.unmodifiableList(this.ingredients);
	}

}

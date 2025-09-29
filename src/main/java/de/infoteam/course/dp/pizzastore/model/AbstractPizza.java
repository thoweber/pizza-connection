package de.infoteam.course.dp.pizzastore.model;

import de.infoteam.course.dp.pizzastore.model.ingredients.dough.Dough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.Sauce;

public abstract class AbstractPizza extends AbstractDish implements Pizza {

	private Dough dough;
	private Sauce sauce;

	protected AbstractPizza(long id, Dough dough, Sauce sauce) {
		super(id);
		this.dough = dough;
		this.sauce = sauce;
	}

	public State getState() {
		return this.state;
	}

	public void updateState(State newState) {
		if (newState == null || newState.ordinal() < state.ordinal()) {
			throw new IllegalArgumentException("State " + state + " cannot be updated to " + newState);
		}
		this.state = newState;
	}

	protected Dough getDough() {
		return dough;
	}

	protected Sauce getSauce() {
		return sauce;
	}

}

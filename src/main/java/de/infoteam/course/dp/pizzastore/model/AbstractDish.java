package de.infoteam.course.dp.pizzastore.model;

public class AbstractDish {

	protected final long id;
	private State state = State.QUEUED;

	public AbstractDish(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return this.id;
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

}
package de.infoteam.course.dp.pizzastore.service.model;

public interface Subscriber<T> {

	void update(T context);
	
}

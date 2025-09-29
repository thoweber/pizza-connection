package de.infoteam.course.dp.pizzastore.service;

public interface Subscriber<T> {

	void update(T context);
	
}

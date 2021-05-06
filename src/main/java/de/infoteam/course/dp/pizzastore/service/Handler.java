package de.infoteam.course.dp.pizzastore.service;

import java.util.Set;

import de.infoteam.course.dp.pizzastore.service.model.Subscriber;

public interface Handler<T, H, E> {

	void setNext(H nextHandler);
	
	void handle(T request, Set<Subscriber<E>> subscribers);
}

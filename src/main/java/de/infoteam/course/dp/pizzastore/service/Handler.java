package de.infoteam.course.dp.pizzastore.service;

import java.util.Set;

import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Subscriber;

public interface Handler<T, H> {

	void setNext(H nextHandler);
	
	void handle(T request, Set<Subscriber<DishStateChange>> subscribers);
}

package de.infoteam.course.dp.pizzastore.service;

public interface Handler<T, H> {

	void setNext(H nextHandler);
	
	void handle(T request);
}

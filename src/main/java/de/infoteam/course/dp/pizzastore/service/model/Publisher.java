package de.infoteam.course.dp.pizzastore.service.model;

public interface Publisher<T> {

	void subscribe(Subscriber<T> subscriber);
	
	void unsubscribe(Subscriber<T> subscriber);
	
	void notifySubscribers();
	
}

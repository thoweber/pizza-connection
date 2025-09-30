package de.infoteam.course.dp.pizzastore.service;

import de.infoteam.course.dp.pizzastore.service.prepchain.AbstractDishHandler;

import java.util.Set;

public interface Handler<T, H, E> {

    Handler<?, H, ?> setNext(H nextHandler);

    void handle(T request, Set<Subscriber<E>> subscribers);
}

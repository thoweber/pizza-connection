package de.infoteam.course.dp.pizzastore.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.infoteam.course.dp.pizzastore.service.model.Publisher;
import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;

public abstract class DishHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DishHandler.class);
    
    protected DishHandler nextHandler;

    public void handle(Dish dish, Publisher<DishStateChange> publisher)
    {
        doHandle(dish, publisher);

        if(nextHandler != null)
        {
            nextHandler.handle(dish, publisher);
        }
    }

    protected abstract void doHandle(Dish dish, Publisher<DishStateChange> publisher);

    public DishHandler setNext(DishHandler nextDishHandler){
        this.nextHandler = nextDishHandler;
        return this;
    }

    protected void sleep(Duration duration) {
		try {
			Thread.sleep(duration.toMillis());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.info("The PizzaChef has been interrupted while doing important work!");
		}
	}
}

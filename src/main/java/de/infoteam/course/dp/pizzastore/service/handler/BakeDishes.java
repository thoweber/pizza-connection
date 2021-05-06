package de.infoteam.course.dp.pizzastore.service.handler;

import java.time.Duration;

import de.infoteam.course.dp.pizzastore.service.DishHandler;
import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.service.model.DishStateChange;
import de.infoteam.course.dp.pizzastore.service.model.Publisher;

public class BakeDishes extends DishHandler{
    @Override
    public void doHandle(Dish dish, Publisher<DishStateChange> publisher)
    {
        if (dish instanceof Pizza) {
            Pizza pizza = (Pizza) dish;
            pizza.updateState(State.IN_OVEN);
            publisher.notifySubscribers();
            // output baking procedure to log
            LOGGER.info(" > baking for {} minutes at {}° Celsius", pizza.getBakingDuration().toMinutes(),
                pizza.getBakingTemperature());
            // sleep
            sleep(Duration.ofSeconds(pizza.getBakingDuration().toMinutes() * 3));
        }
    }
 
}

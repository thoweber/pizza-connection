package de.infoteam.course.dp.pizzastore.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;

@ExtendWith(MockitoExtension.class)
class PizzaPreparationTaskTest {
	
	@Spy
	Pizza pizza = new VeggiePizza(1, new ThinCrustyDough(), new PlainTomatoSauce());
	
	IngredientLogger ingredientLogger = new IngredientLogger();
	
	@Spy
	PizzaPreparationTask pizzaChef = new PizzaPreparationTask(pizza, ingredientLogger, false);

	@Test
	void test_run_calls_preparePizza_logIngredients_bakePizza_servePizza_in_order() {
		// when
		pizzaChef.run();
		// then
		InOrder inOrder = inOrder(pizzaChef);
		then(pizzaChef).should(inOrder).preparePizza(any());
		then(pizzaChef).should(inOrder).logConsumedIngredients(any());
		then(pizzaChef).should(inOrder).bakePizza(any());
		then(pizzaChef).should(inOrder).servePizza(any());
	}

	@Test
	void test_preparePizza_calls_addIngredients() {
		// given
		given(pizza.getIngredients()).willReturn(Arrays.asList(new ThinCrustyDough()));
		// when
		pizzaChef.preparePizza(pizza);
		// then
		then(pizza).should().addIngredients();
	}

	@Test
	void test_bakePizza_accesses_baking_information_from_pizza() {
		// given
		given(pizza.getBakingDuration()).willReturn(Duration.ofMinutes(10));
		given(pizza.getBakingTemperature()).willReturn(250);
		// when
		pizzaChef.bakePizza(pizza);
		// then
		then(pizza).should().getBakingDuration();
		then(pizza).should().getBakingTemperature();
	}
	
}

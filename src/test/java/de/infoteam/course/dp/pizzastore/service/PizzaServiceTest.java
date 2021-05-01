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
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {
	
	@Mock
	Pizza pizza;
	
	@Spy
	PizzaService pizzaService = new PizzaService();

	@Test
	void test_order_calls_preparePizza_bakePizza_servePizza_in_order() {
		// when
		pizzaService.order();
		// then
		InOrder inOrder = inOrder(pizzaService);
		then(pizzaService).should(inOrder).preparePizza(any());
		then(pizzaService).should(inOrder).bakePizza(any());
		then(pizzaService).should(inOrder).servePizza(any());
	}
	
	@Test
	void test_preparePizza_calls_addIngredients() {
		// given
		given(pizza.getIngredients()).willReturn(Arrays.asList(new ThinCrustyDough()));
		// when
		pizzaService.preparePizza(pizza);
		// then
		then(pizza).should().addIngredients();
	}
	
	@Test
	void test_bakePizza_accesses_baking_information_from_pizza() {
		// given
		given(pizza.getBakingDuration()).willReturn(Duration.ofMinutes(10));
		given(pizza.getBakingTemperature()).willReturn(250);
		// when
		pizzaService.bakePizza(pizza);
		// then
		then(pizza).should().getBakingDuration();
		then(pizza).should().getBakingTemperature();
	}

}

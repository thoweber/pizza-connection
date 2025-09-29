package de.infoteam.course.dp.pizzastore.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;
import de.infoteam.course.dp.pizzastore.model.dishes.CheesePizza;
import de.infoteam.course.dp.pizzastore.model.dishes.PeperoniPizza;
import de.infoteam.course.dp.pizzastore.model.dishes.VeggiePizza;
import de.infoteam.course.dp.pizzastore.model.ingredients.dough.ThinCrustyDough;
import de.infoteam.course.dp.pizzastore.model.ingredients.sauce.PlainTomatoSauce;
import java.util.Collection;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DishRepositoryTest {

	DishRepository repository;

	@BeforeEach
	void initRepository() {
		this.repository = new DishRepository();
	}

	@Test
	void test_save_stores_pizza() {
		// given
		Pizza pizza = new CheesePizza(1, new ThinCrustyDough(), new PlainTomatoSauce());
		// when
		repository.saveOrUpdate(pizza);
		// then
		Optional<Dish> fetchedPizza = repository.findById(1);
		assertTrue(fetchedPizza.isPresent());
	}

	@Test
	void test_save_updates_pizza_using_id() {
		// given
		Pizza pizza = new CheesePizza(1, new ThinCrustyDough(), new PlainTomatoSauce());
		repository.saveOrUpdate(pizza);
		Pizza pizza2 = new CheesePizza(1, new ThinCrustyDough(), new PlainTomatoSauce());
		pizza2.updateState(State.IN_PREPARATION);
		// when
		repository.saveOrUpdate(pizza2);
		// then
		Optional<Dish> fetchedPizza = repository.findById(1);
		assertTrue(fetchedPizza.isPresent());
		assertEquals(State.IN_PREPARATION, fetchedPizza.get().getState());
	}

	@Test
	void test_findAll_returns_all_elements() {
		// given
		repository.saveOrUpdate(new CheesePizza(1, new ThinCrustyDough(), new PlainTomatoSauce()));
		repository.saveOrUpdate(new VeggiePizza(2, new ThinCrustyDough(), new PlainTomatoSauce()));
		// when
		Collection<Dish> allPizzas = repository.findAll();
		// then
		assertEquals(2, allPizzas.size());
		assertTrue(allPizzas.stream().anyMatch(p -> p.getClass().equals(VeggiePizza.class)));
		assertTrue(allPizzas.stream().anyMatch(p -> p.getClass().equals(CheesePizza.class)));
	}

	@Test
	void test_findByState_without_state_returns_empty_result() {
		// given
		Pizza pizza1 = new CheesePizza(1, new ThinCrustyDough(), new PlainTomatoSauce());
		pizza1.updateState(State.IN_PREPARATION);
		repository.saveOrUpdate(pizza1);
		repository.saveOrUpdate(new VeggiePizza(2, new ThinCrustyDough(), new PlainTomatoSauce()));
		// when
		Collection<Dish> dishes = repository.findAllByState();
		// then
		assertTrue(dishes.isEmpty());
	}

	@Test
	void test_findByState_returns_pizza_with_given_state_result() {
		// given
		Pizza pizza1 = new CheesePizza(1, new ThinCrustyDough(), new PlainTomatoSauce());
		pizza1.updateState(State.IN_PREPARATION);
		repository.saveOrUpdate(pizza1);
    	Pizza pizza2 = new PeperoniPizza(3, new ThinCrustyDough(), new PlainTomatoSauce());
		pizza2.updateState(State.READY);
		repository.saveOrUpdate(pizza2);
		repository.saveOrUpdate(new VeggiePizza(7, new ThinCrustyDough(), new PlainTomatoSauce()));
		// when
		Collection<Dish> dishes = repository.findAllByState(State.IN_PREPARATION, State.READY);
		// then
		assertEquals(2, dishes.size());
		assertTrue(dishes.stream().filter(p -> p.getClass().equals(CheesePizza.class)).anyMatch(p -> p.getId() == 1));
		assertTrue(dishes.stream().filter(p -> p.getClass().equals(PeperoniPizza.class)).anyMatch(p -> p.getId() == 3));
	}

	@Test
	void test_deleteById_deletes_the_correct_entry() {
		// given
		repository.saveOrUpdate(new CheesePizza(1, new ThinCrustyDough(), new PlainTomatoSauce()));
		repository.saveOrUpdate(new VeggiePizza(2, new ThinCrustyDough(), new PlainTomatoSauce()));
		// when
		Optional<Dish> deleted = repository.deleteById(2);
		// then
		assertTrue(deleted.isPresent());
		assertEquals(2, deleted.get().getId());
	}

}

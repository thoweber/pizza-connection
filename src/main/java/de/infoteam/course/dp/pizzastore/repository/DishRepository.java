package de.infoteam.course.dp.pizzastore.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import de.infoteam.course.dp.pizzastore.model.Dish;
import de.infoteam.course.dp.pizzastore.model.State;

public class DishRepository {

	private final Map<Long, Dish> storage = new ConcurrentHashMap<>();

	/**
	 * Saves or updates the given {@code Pizza}.
	 * 
	 * @param dish the dish to be saved or updated
	 */
	public void saveOrUpdate(Dish dish) {
		storage.put(dish.getId(), dish);
	}

	/**
	 * Fetches the {@code Dish} with the given ID from this repository.
	 * 
	 * @param id the ID of the dish to be fetched
	 * @return the dish as an optional, or an empty optional, if no pizza
	 *         with the given ID exists within the repository
	 */
	public Optional<Dish> findById(long id) {
		return Optional.ofNullable(storage.get(id));
	}
	
	/**
	 * Returns all {@code Dish}es of the repository.
	 * 
	 * @return all {@code Dish}es of the repository
	 */
	public Collection<Dish> findAll() {
		return storage.values();
	}

	/**
	 * Returns all {@code Dish}es of the repository matching the given state.
	 * 
	 * @param state the state to be matched
	 * @return all {@code Dish}es of the repository matching the given state
	 */
	public Collection<Dish> findAllByState(State... state) {
		if (state.length == 0) {
			return Collections.emptyList();
		}
		List<State> selectedStates = Arrays.asList(state);
		return storage.values().stream().filter(dish -> selectedStates.contains(dish.getState()))
				.collect(Collectors.toList());
	}

	/**
	 * Deletes the {@code Dish} with the given ID.
	 * 
	 * @param id the ID of the pizza to be deleted
	 * @return the deleted dish as an optional, or an empty optional, if no dish
	 *         with the given ID exists within the repository
	 */
	public Optional<Dish> deleteById(long id) {
		return Optional.ofNullable(storage.remove(id));
	}

}

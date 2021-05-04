package de.infoteam.course.dp.pizzastore.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import de.infoteam.course.dp.pizzastore.model.Pizza;
import de.infoteam.course.dp.pizzastore.model.State;

public class PizzaRepository {

	private final Map<Long, Pizza> storage = new ConcurrentHashMap<>();

	/**
	 * Saves or updates the given {@code Pizza}.
	 * 
	 * @param pizza the pizza to be saved or updated
	 */
	public void saveOrUpdate(Pizza pizza) {
		storage.put(pizza.getId(), pizza);
	}

	/**
	 * Fetches the {@code Pizza} with the given ID from this repository.
	 * 
	 * @param id the ID of the pizza to be fetched
	 * @return the pizza as an optional, or an empty optional, if no pizza
	 *         with the given ID exists within the repository
	 */
	public Optional<Pizza> findById(long id) {
		return Optional.ofNullable(storage.get(id));
	}
	
	/**
	 * Returns all {@code Pizza} of the repository.
	 * 
	 * @return all {@code Pizza} of the repository
	 */
	public Collection<Pizza> findAll() {
		return storage.values();
	}

	/**
	 * Returns all {@code Pizza} of the repository matching the given state.
	 * 
	 * @param state the state to be matched
	 * @return all {@code Pizza} of the repository matching the given state
	 */
	public Collection<Pizza> findAllByState(State... state) {
		if (state.length == 0) {
			return Collections.emptyList();
		}
		List<State> selectedStates = Arrays.asList(state);
		return storage.values().stream().filter(pizza -> selectedStates.contains(pizza.getState()))
				.collect(Collectors.toList());
	}

	/**
	 * Deletes the {@code Pizza} with the given ID.
	 * 
	 * @param id the ID of the pizza to be deleted
	 * @return the deleted pizza as an optional, or an empty optional, if no pizza
	 *         with the given ID exists within the repository
	 */
	public Optional<Pizza> deleteById(long id) {
		return Optional.ofNullable(storage.remove(id));
	}

}

package devops.storage.implementations;

import java.util.Collection;
import java.util.HashMap;

import devops.storage.interfaces.Storage;
import devops.storage.interfaces.Unique;

/**
 * A hashmap implementation for object management
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class StorageHash<E extends Unique> implements Storage<E> {

	private final HashMap<String, E> map;

	/**
	 * Creates a new storage with hashmap
	 * 
	 * @preconditions none
	 * @postconditions getAll().isEmpty() == true
	 * 
	 */
	public StorageHash() {
		this.map = new HashMap<String, E>();
	}

	@Override
	public E add(E value) {
		if (value == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}

		return this.map.put(value.getUniqueID(), value);
	}

	@Override
	public E get(String uniqueID) {
		if (uniqueID == null || uniqueID.isBlank()) {
			throw new IllegalArgumentException("Unique ID must not be null or blank");
		}

		return this.map.get(uniqueID);
	}

	@Override
	public E remove(String uniqueID) {
		if (uniqueID == null || uniqueID.isBlank()) {
			throw new IllegalArgumentException("Unique ID must not be null or blank");
		}

		return this.map.remove(uniqueID);
	}

	@Override
	public Collection<E> getAll() {
		return this.map.values();
	}

	@Override
	public E update(E value) {
		if (value == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		if (!this.map.containsKey(value.getUniqueID())) {
			throw new IllegalArgumentException("Value does not exist in storage");
		}

		return this.map.put(value.getUniqueID(), value);
	}
	
}

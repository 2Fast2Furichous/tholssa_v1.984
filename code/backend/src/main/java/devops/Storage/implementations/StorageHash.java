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

	private static final String UNIQUE_ID_MUST_NOT_BE_NULL_OR_BLANK = "Unique ID must not be null or blank";
	private static final String VALUE_CANNOT_BE_NULL = "Value cannot be null";
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
			throw new IllegalArgumentException(VALUE_CANNOT_BE_NULL);
		}

		return this.map.put(value.getUniqueID(), value);
	}

	@Override
	public E get(String uniqueID) {
		if (uniqueID == null || uniqueID.isBlank()) {
			throw new IllegalArgumentException(UNIQUE_ID_MUST_NOT_BE_NULL_OR_BLANK);
		}

		return this.map.get(uniqueID);
	}

	@Override
	public E remove(String uniqueID) {
		if (uniqueID == null || uniqueID.isBlank()) {
			throw new IllegalArgumentException(UNIQUE_ID_MUST_NOT_BE_NULL_OR_BLANK);
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
			throw new IllegalArgumentException(VALUE_CANNOT_BE_NULL);
		}
		if (!this.map.containsKey(value.getUniqueID())) {
			throw new IllegalArgumentException("Value does not exist in storage");
		}

		return this.map.put(value.getUniqueID(), value);
	}
	
}

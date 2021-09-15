package devops.Storage.implementations;

import java.util.Collection;
import java.util.HashMap;

import devops.Storage.interfaces.Storage;
import devops.Storage.interfaces.Unique;

public class StorageHash<E extends Unique> implements Storage<E> {

	private final HashMap<String, E> map;

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

		return this.map.put(value.getUniqueID(), value);
	}
	
}

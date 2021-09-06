package devops.data.implementations;

import java.util.Collection;
import java.util.HashMap;
import devops.data.interfaces.Storage;
import devops.data.interfaces.Unique;

public class StorageHash<E extends Unique> implements Storage<E> {

	private HashMap<String, E> map;

	public StorageHash() {
		this.map = new HashMap<String, E>();
	}

	@Override
	public E add(E value) {
		return this.map.put(value.getUniqueID(), value);
	}

	@Override
	public E get(String uniqueID) {
		return this.map.get(uniqueID);
	}

	@Override
	public E remove(String uniqueID) {
		return this.map.remove(uniqueID);
	}

	@Override
	public Collection<E> getAll() {
		return this.map.values();
	}

	@Override
	public E update(E value) {
		return this.map.put(value.getUniqueID(), value);
	}
	
}

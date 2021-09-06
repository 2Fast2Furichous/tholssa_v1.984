package devops.data.interfaces;

import java.util.Collection;

public interface Storage<E extends Unique> {
	public E get(String uniqueID);

	public E add(E value);

	public E remove(String uniqueID);

	public Collection<E> getAll();

	public E update(E value);
}

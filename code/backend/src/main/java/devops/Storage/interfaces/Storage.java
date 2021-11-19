package devops.storage.interfaces;

import java.util.Collection;

/**
 * A storage interface for object management
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface Storage<E extends Unique> {


	/**
	 * Returns the object with the given uniqueID
	 * 
	 * @preconditions uniqueID != null AND !uniqueID.isBlank()
	 * @postconditions none
	 * 
	 * @param uniqueID the object's uniqueID
	 * @return the object with the given uniqueID
	 * @throws IllegalArgumentException
	 */
	E get(String uniqueID);

	/**
	 * Adds the object
	 * 
	 * @preconditions value != null
	 * @postconditions get(value.getUniqueID()) == value
	 * 
	 * @return the object
	 * @throws IllegalArgumentException
	 */
	E add(E value);

	/**
	 * Removes the object with the given uniqueID
	 * 
	 * @preconditions uniqueID != null AND !uniqueID.isBlank()
	 * @postconditions get(uniqueID) == null
	 * 
	 * @return the object
	 * @throws IllegalArgumentException
	 */
	E remove(String uniqueID);

	/**
	 * Gets collection of all the objects in storage
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return collection of all the objects in storage
	 */
	Collection<E> getAll();

	/**
	 * Updates the object with the given uniqueID
	 * 
	 * @preconditions value != null AND get(value.getUniqueID()) != null
	 * @postconditions get(value.getUniqueID()).equals(value)
	 * 
	 * @return the object
	 * @throws IllegalArgumentException
	 */
	E update(E value);
}

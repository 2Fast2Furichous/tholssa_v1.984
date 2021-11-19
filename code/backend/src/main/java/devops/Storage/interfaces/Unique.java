package devops.storage.interfaces;

/**
 * A object that has a unique key.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface Unique {
	/**
	 * Returns the object's uniqueID
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the object's uniqueID
	 */
	String getUniqueID();
}

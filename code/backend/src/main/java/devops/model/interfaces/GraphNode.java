package devops.model.interfaces;

import java.util.Collection;

import devops.storage.interfaces.Unique;

/**
 * Service for interfacing with storage and edtiing graph nodes and edges
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface GraphNode<E extends Comparable<E>> extends Unique {
	/**
	 * Adds the given edge
	 * 
	 * @preconditions edge != null
	 * @postconditions getEdges().size() == @prev(getEdges()).size() + 1
	 * 
	 * @param edge
	 * @return whether the edge was added
	 * @throws IllegalArgumentException
	 */
	boolean addEdge(String edge);

	/**
	 * Remove the given edge
	 * 
	 * @preconditions edge != null
	 * @postconditions getEdges().size() == @prev(getEdges()).size() - 1
	 * 
	 * @param edge
	 * @return whether the edge was added
	 * @throws IllegalArgumentException
	 */
	boolean removeEdge(String edge);

	/**
	 * Returns the value of the node
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the value of the node
	 */
	E getValue();

	/**
	 * Set the value of the node
	 * 
	 * @preconditions none
	 * @postconditions getValue() == value
	 * 
	 * @param value
	 */
	void setValue(E value);

	/**
	 * Returns the UniqueId
	 * 
	 * @preconditon none
	 * @postconditons none
	 * 
	 * @return the unique id
	 */
	String getUniqueID();

	/**
	 * Gets the collection of connected edges
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the collection of connected edges
	 */
	Collection<String> getEdges();
}

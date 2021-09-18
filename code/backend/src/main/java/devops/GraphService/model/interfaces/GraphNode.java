package devops.GraphService.model.interfaces;

import java.util.Collection;

import devops.GraphService.model.implementations.Person;
import devops.Storage.interfaces.Unique;

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
	public boolean addEdge(GraphEdge<E> edge);

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
	public boolean removeEdge(GraphEdge<E> edge);


	/**
	 * Returns the value of the node
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the value of the node
	 */
	public E getValue();
	
	/**
	 * Set the value of the node
	 * 
	 * @preconditions none
	 * @postconditions getValue() == value
	 * 
	 * @param value
	 */
	public void setValue(E value);

	public String getUniqueID();

	/**
	 * Gets the collection of connected edges
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the collection of connected edges
	 */
	public Collection<GraphEdge<Person>> getEdges();
}

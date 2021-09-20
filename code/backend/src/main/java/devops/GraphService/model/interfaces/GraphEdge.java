package devops.GraphService.model.interfaces;

import devops.Storage.interfaces.Unique;

/**
 * Service for interfacing with storage and edtiing graph nodes and edges
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface GraphEdge<E extends Comparable<E>> extends Unique {

	/**
	 * Returns the source node
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the source node
	 */
	public GraphNode<E> getSource();

	/**
	 * Returns the destination node
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the destination node
	 */
	public GraphNode<E> getDestination();
	
	public String getUniqueID();
}

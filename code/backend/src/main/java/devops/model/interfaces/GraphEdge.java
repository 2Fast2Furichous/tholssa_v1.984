package devops.model.interfaces;

import devops.storage.interfaces.Unique;

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
	public String getSource();

	/**
	 * Returns the destination node
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the destination node
	 */
	public String getDestination();

	/**
	 * Returns the UniqueId
	 * @preconditon none
	 * @postconditons none
	 * 
	 * @return the unique id
	 */
	public String getUniqueID();
}

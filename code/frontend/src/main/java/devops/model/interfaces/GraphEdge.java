package devops.model.interfaces;

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
	String getSource();

	/**
	 * Returns the destination node
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the destination node
	 */
	String getDestination();

	/**
	 * Returns the UniqueId
	 * 
	 * @preconditon none
	 * @postconditons none
	 * 
	 * @return the unique id
	 */
	String getUniqueID();
}

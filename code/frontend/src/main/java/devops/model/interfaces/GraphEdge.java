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
	
	public String getUniqueID();
}

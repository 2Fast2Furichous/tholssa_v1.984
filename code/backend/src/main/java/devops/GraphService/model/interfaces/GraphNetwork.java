package devops.GraphService.model.interfaces;

import java.util.Collection;

/**
 * Network of edges and nodes
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface GraphNetwork<E extends Comparable<E>> {

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
	 * Adds the given node
	 * 
	 * @preconditions node != null
	 * @postconditions getNodes().size() == @prev(getNodes()).size() + 1
	 * 
	 * @param node
	 * @return whether the node was added
	 * @throws IllegalArgumentException
	 */
	public boolean addNode(GraphNode<E> node);

	/**
	 * Returns the collection of edges
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the collection of edges
	 * @throws IllegalArgumentException
	 */
	public Collection<GraphEdge<E>> getEdges();

	/**
	 * Returns the collection of edges
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the collection of edges
	 * @throws IllegalArgumentException
	 */
	public Collection<GraphNode<E>> getNodes();

}

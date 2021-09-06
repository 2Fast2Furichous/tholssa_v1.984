package devops.GraphService.model.interfaces;

import java.util.Collection;

import devops.data.interfaces.Unique;

public interface GraphEdge<E extends Comparable<E>> extends Unique {
	public GraphNode<E> getSource();
	public GraphNode<E> getDestination();

	public void setSource(GraphNode<E> node);
	public void setDestination(GraphNode<E> node);

	public boolean addNeighbor(GraphEdge<E> edge);
	public boolean removeNeighbor(GraphEdge<E> edge);

	public Collection<GraphEdge<E>> getNeighbors();
	public String getUniqueID();
}

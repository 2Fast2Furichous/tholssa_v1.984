package devops.GraphService.model.interfaces;

import devops.Storage.interfaces.Unique;

public interface GraphEdge<E extends Comparable<E>> extends Unique {
	public GraphNode<E> getSource();
	public GraphNode<E> getDestination();
	
	public String getUniqueID();
}

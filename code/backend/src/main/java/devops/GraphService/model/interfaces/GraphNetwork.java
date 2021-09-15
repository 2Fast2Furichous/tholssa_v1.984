package devops.GraphService.model.interfaces;

import java.util.Collection;

public interface GraphNetwork<E extends Comparable<E>> {

	public boolean addEdge(GraphEdge<E> edge);
	public boolean addNode(GraphNode<E> node);

	public Collection<GraphEdge<E>> getEdges();
	public Collection<GraphNode<E>> getNodes();

}

package devops.GraphService.model.interfaces;

import java.util.Collection;

import devops.GraphService.model.implementations.Person;
import devops.Storage.interfaces.Unique;

public interface GraphNode<E extends Comparable<E>> extends Unique {
	public boolean addEdge(GraphEdge<E> node);
	public boolean removeEdge(GraphEdge<E> node);
	public E getValue();
	public void setValue(E value);
	public String getUniqueID();
	public Collection<GraphEdge<Person>> getEdges();
}

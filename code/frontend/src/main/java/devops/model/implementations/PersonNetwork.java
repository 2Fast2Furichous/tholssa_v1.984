package devops.model.implementations;

import java.util.ArrayList;
import java.util.Collection;

import devops.model.interfaces.GraphEdge;
import devops.model.interfaces.GraphNetwork;
import devops.model.interfaces.GraphNode;

/**
 * Network of person nodes and edges
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class PersonNetwork implements GraphNetwork<Person>{

	Collection<GraphEdge<Person>> edges;
	Collection<GraphNode<Person>> nodes;

	/**
	 * Zero parameter constructor
	 * 
	 * @preconditions none
	 * @postconditions getEdges() != null && getNodes() != null
	 * 
	 */
	public PersonNetwork() {
		this.edges = new ArrayList<GraphEdge<Person>>();
		this.nodes = new ArrayList<GraphNode<Person>>();
	}

	/**
	 * Zero parameter constructor
	 * 
	 * @preconditions nodes != null && edges != null
	 * @postconditions getEdges() == edges && getNodes() == nodes
	 * 
	 */
	public PersonNetwork(Collection<GraphNode<Person>> nodes, Collection<GraphEdge<Person>> edges) {
		if (nodes == null) {
			throw new IllegalArgumentException("Nodes must not be null");
		}
		if (edges == null) {
			throw new IllegalArgumentException("Edges must not be null");
		}
		
		this.edges = edges;
		this.nodes = nodes;
	}

	@Override
	public boolean addEdge(GraphEdge<Person> edge) {
		if (edge == null) {
			throw new IllegalArgumentException("Edge must not be null");
		}
		return this.edges.add(edge);
	}

	@Override
	public boolean addNode(GraphNode<Person> node) {
		if (node == null) {
			throw new IllegalArgumentException("Node must not be null");
		}
		return this.nodes.add(node);
	}

	@Override
	public Collection<GraphEdge<Person>> getEdges() {
		return this.edges;
	}

	@Override
	public Collection<GraphNode<Person>> getNodes() {
		return this.nodes;
	}
}

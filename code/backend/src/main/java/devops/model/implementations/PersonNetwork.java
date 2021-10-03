package devops.model.implementations;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Network of person nodes and edges
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class PersonNetwork {

	Collection<PersonEdge> edges;
	Collection<PersonNode> nodes;

	/**
	 * Zero parameter constructor
	 * 
	 * @preconditions none
	 * @postconditions getEdges() != null && getNodes() != null
	 * 
	 */
	public PersonNetwork() {
		this.edges = new ArrayList<PersonEdge>();
		this.nodes = new ArrayList<PersonNode>();
	}

	/**
	 * Zero parameter constructor
	 * 
	 * @preconditions nodes != null && edges != null
	 * @postconditions getEdges() == edges && getNodes() == nodes
	 * 
	 */
	public PersonNetwork(Collection<PersonNode> nodes, Collection<PersonEdge> edges) {
		if (nodes == null) {
			throw new IllegalArgumentException("Nodes must not be null");
		}
		if (edges == null) {
			throw new IllegalArgumentException("Edges must not be null");
		}
		
		this.edges = edges;
		this.nodes = nodes;
	}

	public boolean addEdge(PersonEdge edge) {
		if (edge == null) {
			throw new IllegalArgumentException("Edge must not be null");
		}
		return this.edges.add(edge);
	}


	public boolean addNode(PersonNode node) {
		if (node == null) {
			throw new IllegalArgumentException("Node must not be null");
		}
		return this.nodes.add(node);
	}


	public Collection<PersonEdge> getEdges() {
		return this.edges;
	}


	public Collection<PersonNode> getNodes() {
		return this.nodes;
	}
}

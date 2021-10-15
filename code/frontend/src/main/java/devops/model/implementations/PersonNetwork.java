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
	 * Constructor that creates the Person Network
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
	 * Constructor that creates the Person Network with nodes and edges
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
	/**
	 * adds an edge to a node
	 * @param edge
	 * @return true if the edge is added false otherwise
	 */
	public boolean addEdge(PersonEdge edge) {
		if (edge == null) {
			throw new IllegalArgumentException("Edge must not be null");
		}
		return this.edges.add(edge);
	}

	/**
	 * adds a node to the graph
	 * @param node
	 * @return true if the node is added false otherwise
	 */
	public boolean addNode(PersonNode node) {
		if (node == null) {
			throw new IllegalArgumentException("Node must not be null");
		}
		return this.nodes.add(node);
	}

	/**
	 * gets the edges 
	 * @return a collection of edges
	 */
	public Collection<PersonEdge> getEdges() {
		return this.edges;
	}

	/**
	 * gets the nodes
	 * @return a  collection of nodes
	 */
	public Collection<PersonNode> getNodes() {
		return this.nodes;
	}
}

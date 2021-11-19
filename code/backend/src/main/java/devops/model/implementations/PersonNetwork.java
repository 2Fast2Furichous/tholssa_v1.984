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

	private Collection<PersonEdge> edges;
	private Collection<PersonNode> nodes;

	/**
	 * Constructor for creating the edges and nodes for a network
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
	 * Constructor for setting the edges and nodes for a persons network
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
	 * adds a PersonEdge to the network
	 * @param edge
	 * @return
	 */
	public boolean addEdge(PersonEdge edge) {
		if (edge == null) {
			throw new IllegalArgumentException("Edge must not be null");
		}
		return this.edges.add(edge);
	}

	/**
	 * adds a PersonNode to the network
	 * @param node
	 * @return
	 */
	public boolean addNode(PersonNode node) {
		if (node == null) {
			throw new IllegalArgumentException("Node must not be null");
		}
		return this.nodes.add(node);
	}

	/**
	 * Gets the edges for the network
	 * @return
	 */
	public Collection<PersonEdge> getEdges() {
		return this.edges;
	}


	/**
	 * Gets the nodes for the network
	 * @return
	 */
	public Collection<PersonNode> getNodes() {
		return this.nodes;
	}
}

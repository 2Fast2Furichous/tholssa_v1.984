package devops.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.UUID;
import java.util.function.Predicate;

import devops.Storage.implementations.StorageHash;
import devops.Storage.interfaces.Storage;
import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;
import devops.model.interfaces.GraphEdge;
import devops.model.interfaces.GraphNetwork;
import devops.model.interfaces.GraphNode;

/**
 * Service for interfacing with storage and edtiing graph nodes and edges
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class GraphService {

	private Storage<GraphNode<Person>> nodeStorage;
	private Storage<GraphEdge<Person>> edgeStorage;

	/**
	 * Creates a new graph service
	 * 
	 * @preconditions none
	 * @postconditions getNodeStorage() != null && getEdgeStorage() != null
	 * 
	 */
	public GraphService() {
		this.nodeStorage = new StorageHash<GraphNode<Person>>();
		this.edgeStorage = new StorageHash<GraphEdge<Person>>();
	}

	/**
	 * Returns the node storage
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the node storage
	 */
	public Storage<GraphNode<Person>> getNodeStorage() {
		return this.nodeStorage;
	}

	/**
	 * Returns the edge storage
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the edge storage
	 */
	public Storage<GraphEdge<Person>> getEdgeStorage() {
		return this.edgeStorage;
	}

	/**
	 * Creates a new node with the given person
	 * 
	 * @preconditions none
	 * @postconditions getNode(@return).getValue() == person
	 * 
	 * @param person the person to be added
	 * @return the generated unique ID of the created node
	 * @throws IllegalArgumentException
	 */
	public String createNode(Person person) {
		String uniqueID = UUID.randomUUID().toString();

		PersonNode newNode = new PersonNode(uniqueID, person);
		nodeStorage.add(newNode);
		return uniqueID;
	}

	/**
	 * Creates a new edge between the given nodes
	 * 
	 * @preconditions none
	 * @postconditions getEdge(@return).getSource() == getNode(sourceGuid) &&
	 *                 getEdge(@return).getDestination() == getNode(destinationGuid)
	 *                 getEdge(@return).getSource().get
	 * 
	 * @param sourceGuid
	 * @param destinationGuid
	 * @param relation
	 * @param dateOfConnection
	 * @param dateOfConnectionEnd
	 * @return the generated unique ID of the created edge
	 * @throws IllegalArgumentException
	 */
	public String connectNodes(String sourceGuid, String destinationGuid, Relationship relation,
			LocalDate dateOfConnection, LocalDate dateOfConnectionEnd) {
		String uniqueID = UUID.randomUUID().toString();

		GraphNode<Person> source = this.nodeStorage.get(sourceGuid);
		GraphNode<Person> destination = this.nodeStorage.get(destinationGuid);

		PersonEdge newEdge = new PersonEdge(uniqueID, 
				sourceGuid, destinationGuid, relation, dateOfConnection,
				dateOfConnectionEnd);

		source.addEdge(uniqueID);
		destination.addEdge(uniqueID);

		this.edgeStorage.add(newEdge);
		return uniqueID;
	}

	/**
	 * Returns the node with the given uniqueID
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @param guid Generated unique identifier
	 * @return the node with the given uniqueID
	 * @throws IllegalArgumentException
	 */
	public GraphNode<Person> getNode(String guid) {
		return this.nodeStorage.get(guid);
	}

	/**
	 * Returns the edge with the given uniqueID
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @param guid Generated unique identifier
	 * @return the edge with the given uniqueID
	 * @throws IllegalArgumentException
	 */
	public GraphEdge<Person> getEdge(String guid) {
		return this.edgeStorage.get(guid);
	}

	/**
	 * Updates the specified node with the new person
	 * 
	 * @preconditions none
	 * @postconditions getNode(guid).getValue() == relation
	 * 
	 * @param guid   the uniqueID of the node
	 * @param person the person to be updated
	 * 
	 * @return the updated node
	 * @throws IllegalArgumentException
	 */
	public GraphNode<Person> updateNode(String guid, Person person) {
		GraphNode<Person> updatedNode = this.nodeStorage.get(guid);
		updatedNode.setValue(person);
		return this.nodeStorage.update(updatedNode);
	}

	/**
	 * Updates the specified node with the new person
	 * 
	 * @preconditions none
	 * @postconditions getEdge(guid).getRelation() == relation &&
	 *                 getEdge(guid).getDateOfConnection() == dateOfConnection &&
	 *                 getEdge(guid).getDateOfConnectionEnd() == dateOfConnectionEnd
	 * 
	 * @param guid                the uniqueID of the edge
	 * @param relation
	 * @param dateOfConnection
	 * @param dateOfConnectionEnd
	 * 
	 * @return the updated edge
	 * @throws IllegalArgumentException
	 */
	public GraphEdge<Person> updateEdge(String guid, Relationship relation, LocalDate dateOfConnection,
			LocalDate dateOfConnectionEnd) {
		PersonEdge updatedEdge = (PersonEdge) this.edgeStorage.get(guid);

		updatedEdge.setRelation(relation);
		updatedEdge.setDateOfConnection(dateOfConnection);
		updatedEdge.setDateOfConnectionEnd(dateOfConnectionEnd);

		return this.edgeStorage.update(updatedEdge);
	}

	/**
	 * Removes the specified node and any connected edges
	 * 
	 * @preconditions none
	 * @postconditions @return.getEdges().size() == 0
	 * 
	 * @param guid the uniqueID of the node
	 * 
	 * @return the removed node
	 * @throws IllegalArgumentException
	 */
	public GraphNode<Person> removeNode(String guid) {
		GraphNode<Person> node = this.nodeStorage.remove(guid);

		for (String edge : node.getEdges()) {
			this.edgeStorage.remove(edge);
			node.removeEdge(edge);
		}

		return node;
	}

	/**
	 * Removes the specified edge and disconnects the source node.
	 * 
	 * @preconditions none
	 * @postconditions @return.getSource().getEdges().size()
	 *                 == @prev(@return.getSource().getEdges().size()) - 1
	 * 
	 * @param guid the uniqueID of the edge
	 * 
	 * @return the removed edge
	 * @throws IllegalArgumentException
	 */
	public GraphEdge<Person> removeEdge(String guid) {
		GraphEdge<Person> edge = this.edgeStorage.remove(guid);
		GraphNode<Person> sourceNode = this.nodeStorage.get(edge.getSource());
		sourceNode.removeEdge(guid);
		return edge;
	}

	/**
	 * Returns the graph network from the root node and edge filters.
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @param rootNodeGuid
	 * @param filters
	 * @return graph network given the filters and rootNode
	 * @throws IllegalArgumentException
	 */
	public GraphNetwork<Person> getFilteredNetwork(String rootNodeGuid, Collection<NodeFilter> filters) {
		GraphNetwork<Person> filteredNetwork;

		if (filters.isEmpty()) {
			filteredNetwork = new PersonNetwork(this.nodeStorage.getAll(), this.edgeStorage.getAll());
		} else {
			GraphNode<Person> rootNode = this.nodeStorage.get(rootNodeGuid);
			Predicate<GraphEdge<Person>> nodePredicate = new NodeFilterPredicate(filters);
			filteredNetwork = this.floodFill(rootNode, nodePredicate);
		}

		return filteredNetwork;
	}

	private GraphNetwork<Person> floodFill(GraphNode<Person> rootNode, Predicate<GraphEdge<Person>> nodePredicate) {
		GraphNetwork<Person> newNetwork = new PersonNetwork();
		LinkedList<GraphNode<Person>> queue = new LinkedList<GraphNode<Person>>();
		HashSet<GraphNode<Person>> visited = new HashSet<GraphNode<Person>>();

		queue.add(rootNode);
		visited.add(rootNode);

		while (!queue.isEmpty()) {
			GraphNode<Person> currentNode = queue.poll();
			for (String neighborEdgeID : currentNode.getEdges()) {
				GraphEdge<Person> neighborEdge = this.edgeStorage.get(neighborEdgeID);
				GraphNode<Person> neighborNode = this.nodeStorage.get(neighborEdge.getDestination());
				if (!visited.contains(neighborNode) && nodePredicate.test(neighborEdge)) {
					newNetwork.addEdge(neighborEdge);
					newNetwork.addNode(neighborNode);
					queue.add(neighborNode);
					visited.add(neighborNode);
				}
			}
		}

		return newNetwork;
	}

	private class NodeFilterPredicate implements Predicate<GraphEdge<Person>> {

		private Predicate<GraphEdge<Person>> compositePredicate;

		public NodeFilterPredicate(Collection<NodeFilter> filters) {
			this.compositePredicate = filters.stream().map(NodeFilter::getPredicate).reduce(x -> true, Predicate::and);
		}

		@Override
		public boolean test(GraphEdge<Person> t) {
			return compositePredicate.test(t);
		}
	}
}

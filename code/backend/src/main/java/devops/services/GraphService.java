package devops.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.UUID;
import java.util.function.Predicate;

import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;
import devops.model.implementations.Review;
import devops.model.interfaces.GraphEdge;
import devops.model.interfaces.GraphNode;
import devops.storage.implementations.StorageHash;
import devops.storage.interfaces.Storage;

/**
 * Service for interfacing with storage and edtiing graph nodes and edges
 *
 * @author Furichous Jones IV and Alexander Ayers
 * @version Fall 2021
 */
public class GraphService {

	private Storage<PersonNode> nodeStorage;
	private Storage<PersonEdge> edgeStorage;

	/**
	 * Creates a new graph service
	 * 
	 * @preconditions none
	 * @postconditions getNodeStorage() != null && getEdgeStorage() != null
	 * 
	 */
	public GraphService() {
		this.nodeStorage = new StorageHash<PersonNode>();
		this.edgeStorage = new StorageHash<PersonEdge>();
	}

	/**
	 * Returns the node storage
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the node storage
	 */
	public Storage<PersonNode> getNodeStorage() {
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
	public Storage<PersonEdge> getEdgeStorage() {
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
		this.nodeStorage.add(newNode);
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

		GraphNode<Person> source = this.nodeStorage.get(sourceGuid);

		var existingEdge = source.getEdges().stream().filter((edgeUniqueID) -> {
			PersonEdge currentEdge = this.edgeStorage.get(edgeUniqueID);
			return currentEdge.getDestination().equals(destinationGuid);
		}).findFirst();

		if (existingEdge.isPresent()) {
			return existingEdge.get();
		}

		GraphNode<Person> destination = this.nodeStorage.get(destinationGuid);
		String uniqueID = UUID.randomUUID().toString();

		PersonEdge newEdge = new PersonEdge(uniqueID, sourceGuid, destinationGuid, relation, dateOfConnection,
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
	public PersonNode getNode(String guid) {
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
	public PersonEdge getEdge(String guid) {
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
	public PersonNode updateNode(String guid, Person person) {
		PersonNode updatedNode = this.nodeStorage.get(guid);
		updatedNode.setValue(person);

		person.getReviews().sort((var review1, var review2) -> {
			return review2.getEntryDate().compareTo(review1.getEntryDate());
		});

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
	public PersonEdge updateEdge(String guid, Relationship relation, LocalDate dateOfConnection,
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
	public PersonNode removeNode(String guid) {
		PersonNode node = this.nodeStorage.get(guid);
		for (String edge : node.getEdges()) {
			this.removeEdge(edge);
		}

		this.nodeStorage.remove(guid);
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
	public PersonEdge removeEdge(String guid) {
		PersonEdge edge = this.edgeStorage.remove(guid);
		PersonNode sourceNode = this.nodeStorage.get(edge.getSource());
		PersonNode destinationNode = this.nodeStorage.get(edge.getDestination());
		sourceNode.removeEdge(guid);
		destinationNode.removeEdge(guid);

		return edge;
	}

	/**
	 * Returns the graph network from the root node and edge filters.
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @param rootNodeGuid the guid of the root node to base the filtering off of.
	 * @param filters      the list of filters to apply to the filtering algorithm.
	 * @return graph network given the filters and rootNode
	 * @throws IllegalArgumentException
	 */
	public PersonNetwork getFilteredNetwork(String rootNodeGuid, Collection<NodeFilter> filters, int maxDepth) {
		PersonNetwork filteredNetwork;

		if (rootNodeGuid == null || rootNodeGuid.isBlank() || filters.isEmpty()) {
			filteredNetwork = new PersonNetwork(this.nodeStorage.getAll(), this.edgeStorage.getAll());
		} else {
			PersonNode rootNode = this.nodeStorage.get(rootNodeGuid);
			Predicate<GraphEdge<Person>> nodePredicate = new NodeFilterPredicate(filters);
			filteredNetwork = this.floodFill(rootNode, nodePredicate, maxDepth);
		}

		return filteredNetwork;
	}

	/**
	 * Adds a review to the person with the specified uniqueID.
	 * 
	 * @precondition none
	 * @postcondition @return.getValue().getReviews().size() == @prev + 1
	 * 
	 * @param review the specified review
	 * @param guid   the uniqueID of the person being reviewed.
	 * 
	 * @return the node of the person who was reviewed.
	 */
	public PersonNode addReview(Review review, String guid) {
		var node = this.nodeStorage.get(guid);
		var person = node.getValue();
		person.addReview(review);

		return node;
	}

	private PersonNetwork floodFill(PersonNode rootNode, Predicate<GraphEdge<Person>> nodePredicate, int maxDepth) {
		PersonNetwork newNetwork = new PersonNetwork();
		LinkedList<PersonNode> queue = new LinkedList<PersonNode>();
		HashSet<PersonEdge> visited = new HashSet<PersonEdge>();
		HashMap<PersonNode, Integer> depth = new HashMap<PersonNode, Integer>();
		newNetwork.addNode(rootNode);
		queue.add(rootNode);
		depth.put(rootNode, 0);

		while (!queue.isEmpty()) {
			PersonNode currentNode = queue.poll();
			var currentDepth = depth.get(currentNode);
			if (currentDepth >= maxDepth) {
				continue;
			}
			for (String neighborEdgeID : currentNode.getEdges()) {
				PersonEdge neighborEdge = this.edgeStorage.get(neighborEdgeID);
				PersonNode neighborNode = this.nodeStorage.get(neighborEdge.getDestination());

				if (!visited.contains(neighborEdge) && nodePredicate.test(neighborEdge)) {
					newNetwork.addEdge(neighborEdge);
					newNetwork.addNode(neighborNode);
					queue.add(neighborNode);
					visited.add(neighborEdge);
					depth.put(neighborNode, currentDepth + 1);
				}
			}
		}

		return newNetwork;
	}

	private class NodeFilterPredicate implements Predicate<GraphEdge<Person>> {

		private Predicate<GraphEdge<Person>> compositePredicate;

		NodeFilterPredicate(Collection<NodeFilter> filters) {
			this.compositePredicate = filters.stream().map(NodeFilter::getPredicate).reduce(x -> false, Predicate::or);
		}

		@Override
		public boolean test(GraphEdge<Person> edge) {
			return this.compositePredicate.test(edge);
		}
	}
}

package devops.GraphService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;
import java.util.function.Predicate;

import devops.GraphService.model.implementations.NodeFilter;
import devops.GraphService.model.implementations.Person;
import devops.GraphService.model.implementations.PersonEdge;
import devops.GraphService.model.implementations.PersonNetwork;
import devops.GraphService.model.implementations.PersonNode;
import devops.GraphService.model.implementations.Relationship;
import devops.GraphService.model.interfaces.GraphEdge;
import devops.GraphService.model.interfaces.GraphNetwork;
import devops.GraphService.model.interfaces.GraphNode;
import devops.Storage.implementations.StorageHash;
import devops.Storage.interfaces.Storage;

public class GraphService {

	private Storage<GraphNode<Person>> nodeStorage;
	private Storage<GraphEdge<Person>> edgeStorage;

	public GraphService() {
		this.nodeStorage = new StorageHash<GraphNode<Person>>();
		this.edgeStorage = new StorageHash<GraphEdge<Person>>();
	}


	public Storage<GraphNode<Person>> getNodeStorage() {
		return this.nodeStorage;
	}

	public Storage<GraphEdge<Person>> getEdgeStorage() {
		return this.edgeStorage;
	}

	public String createNode(Person person) {
		String uniqueID = UUID.randomUUID().toString();

		PersonNode newNode = new PersonNode(uniqueID, person);
		nodeStorage.add(newNode);
		return uniqueID;
	}

	public String connectNodes(String sourceGuid, String destinationGuid, Relationship relation,
			LocalDate dateOfConnection, LocalDate dateOfConnectionEnd) {
		String uniqueID = UUID.randomUUID().toString();

		GraphNode<Person> source = this.nodeStorage.get(sourceGuid);
		GraphNode<Person> destination = this.nodeStorage.get(destinationGuid);

		PersonEdge newEdge = new PersonEdge(uniqueID, source, destination, relation, dateOfConnection,
				dateOfConnectionEnd);

		this.edgeStorage.add(newEdge);
		return uniqueID;
	}

	public GraphNode<Person> getNode(String guid) {
		return this.nodeStorage.get(guid);
	}

	public GraphEdge<Person> getEdge(String guid) {
		return this.edgeStorage.get(guid);
	}

	public GraphNode<Person> updateNode(String guid, Person person) {
		GraphNode<Person> updatedNode = this.nodeStorage.get(guid);
		updatedNode.setValue(person);
		return this.nodeStorage.update(updatedNode);
	}

	public GraphEdge<Person> updateEdge(String guid, Relationship relation, LocalDate dateOfConnection,
			LocalDate dateOfConnectionEnd) {
		PersonEdge updatedEdge = (PersonEdge) this.edgeStorage.get(guid);

		updatedEdge.setRelation(relation);
		updatedEdge.setDateOfConnection(dateOfConnection);
		updatedEdge.setDateOfConnectionEnd(dateOfConnectionEnd);

		return this.edgeStorage.update(updatedEdge);
	}

	public GraphNode<Person> removeNode(String guid) {
		GraphNode<Person> node = this.nodeStorage.remove(guid);

		for (GraphEdge<Person> edge : node.getEdges()) {
			this.edgeStorage.remove(edge.getUniqueID());
			node.removeEdge(edge);
		}

		return node;
	}

	public GraphEdge<Person> removeEdge(String guid) {
		GraphEdge<Person> edge = this.edgeStorage.remove(guid);

		edge.getSource().removeEdge(edge);

		return edge;
	}

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

		while (!queue.isEmpty()) {
			GraphNode<Person> currentNode = queue.poll();
			for (GraphEdge<Person> neighborEdge : currentNode.getEdges()) {
				GraphNode<Person> neighborNode = neighborEdge.getDestination();
				if (nodePredicate.test(neighborEdge)) {
					newNetwork.addEdge(neighborEdge);
					newNetwork.addNode(neighborNode);
					queue.add(neighborNode);
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

package devops.GraphService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import devops.GraphService.model.implementations.NodeFilter;
import devops.GraphService.model.implementations.Person;
import devops.GraphService.model.implementations.PersonEdge;
import devops.GraphService.model.implementations.PersonNode;
import devops.GraphService.model.interfaces.GraphEdge;
import devops.GraphService.model.interfaces.GraphNode;
import devops.data.implementations.StorageHash;
import devops.data.interfaces.Storage;

public class GraphService {

	private Storage<GraphNode<Person>> nodeStorage;
	private StorageHash<GraphEdge<Person>> edgeStorage;

	public GraphService() {
		this.nodeStorage = new StorageHash<GraphNode<Person>>();
		this.edgeStorage = new StorageHash<GraphEdge<Person>>();
	}

	public String createNode(Person person) {
		String uniqueID = UUID.randomUUID().toString();
		PersonNode newNode = new PersonNode(uniqueID, person);
		nodeStorage.add(newNode);
		return uniqueID;
	}

	public String connectNodes(String sourceGuid, String destinationGuid) {
		String uniqueID = UUID.randomUUID().toString();

		GraphNode<Person> source = this.nodeStorage.get(sourceGuid);
		GraphNode<Person> destination = this.nodeStorage.get(destinationGuid);

		PersonEdge newEdge = new PersonEdge(uniqueID, source, destination);
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

	public GraphEdge<Person> updateEdge(String guid, String sourceGuid, String destinationGuid) {

		GraphEdge<Person> updatedEdge = this.edgeStorage.get(guid);

		GraphNode<Person> source = this.nodeStorage.get(sourceGuid);
		GraphNode<Person> destination = this.nodeStorage.get(destinationGuid);

		updatedEdge.setSource(source);
		updatedEdge.setDestination(destination);
		return this.edgeStorage.update(updatedEdge);
	}

	public GraphNode<Person> removeNode(String guid) {
		return this.nodeStorage.remove(guid);
	}
	public GraphEdge<Person> removeEdge(String guid) {
		return this.edgeStorage.remove(guid);
	}

	public Collection<GraphNode<Person>> getFilteredNodes(String rootNodeGuid, ArrayList<NodeFilter> filters) {
		Collection<GraphNode<Person>> filteredNodes = new  ArrayList<GraphNode<Person>>();

		if (filters.isEmpty()) {
			filteredNodes = this.nodeStorage.getAll();
		} else {
			// TODO Replace with flood fill process that includes filter
			GraphNode<Person> rootNode = this.nodeStorage.get(rootNodeGuid);
		}

		return filteredNodes;
	}

	public Collection<GraphEdge<Person>> getFilteredEdges(String rootNodeGuid, ArrayList<NodeFilter> filters) {
		Collection<GraphEdge<Person>> filteredEdges = new ArrayList<GraphEdge<Person>>();

		if (filters.isEmpty()) {
			filteredEdges = this.edgeStorage.getAll();
		} else {
			// TODO Replace with flood fill process that includes filter
			GraphNode<Person> rootNode = this.nodeStorage.get(rootNodeGuid);
		}

		return filteredEdges;
	}
}

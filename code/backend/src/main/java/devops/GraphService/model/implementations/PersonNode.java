package devops.GraphService.model.implementations;

import java.util.Collection;
import java.util.HashSet;

import devops.GraphService.model.interfaces.GraphEdge;
import devops.GraphService.model.interfaces.GraphNode;

public class PersonNode implements GraphNode<Person> {

	private final String uniqueID;
	private Person person;
	private final Collection<GraphEdge<Person>> edges;

	public PersonNode(String uniqueID, Person person) {
		if (uniqueID == null || uniqueID.isBlank()) {
			throw new IllegalArgumentException("Unique ID must not be null or blank");
		}
		if (person == null) {
			throw new IllegalArgumentException("Person must not be null");
		}
		this.uniqueID = uniqueID;
		this.person = person;
		this.edges = new HashSet<GraphEdge<Person>>();
	}

	@Override
	public boolean addEdge(GraphEdge<Person> node) {
		return this.edges.add(node);
	}

	@Override
	public boolean removeEdge(GraphEdge<Person> node) {
		return this.edges.remove(node);
	}

	@Override
	public void setValue(Person value) {
		this.person = value;
	}

	@Override
	public Person getValue() {
		return this.person;
	}

	@Override
	public String getUniqueID() {
		return this.uniqueID;
	}

	@Override
	public Collection<GraphEdge<Person>> getEdges() {
		return this.edges;
	}
}

package devops.model.implementations;

import java.util.Collection;
import java.util.HashSet;

import devops.model.interfaces.GraphNode;
/**
 * Node of a person
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class PersonNode implements GraphNode<Person> {

	private final String uniqueID;
	private Person person;
	private final Collection<String> edges;

	/**
	 * 
	 * Creates a new person node given the uniqueID and person
	 * 
	 * @preconditions uniqueID != null && !uniqueID.isBlank() && person != null
	 * @postconditions getUniqueID == uniqueID && getValue() == person && getEdges() != null
	 * 
	 * @param uniqueID
	 * @param person
	 */
	public PersonNode(String uniqueID, Person person) {
		if (uniqueID == null || uniqueID.isBlank()) {
			throw new IllegalArgumentException("Unique ID must not be null or blank");
		}
		if (person == null) {
			throw new IllegalArgumentException("Person must not be null");
		}
		this.uniqueID = uniqueID;
		this.person = person;
		this.edges = new HashSet<String>();
	}

	@Override
	public boolean addEdge(String edge) {
		if (edge == null || edge.isBlank()) {
			throw new IllegalArgumentException("Edge must not be null or blank");
		}
		return this.edges.add(edge);
	}

	@Override
	public boolean removeEdge(String edge) {
		if (edge == null || edge.isBlank()) {
			throw new IllegalArgumentException("Edge must not be null or blank");
		}
		return this.edges.remove(edge);
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
	public Collection<String> getEdges() {
		return this.edges;
	}
}

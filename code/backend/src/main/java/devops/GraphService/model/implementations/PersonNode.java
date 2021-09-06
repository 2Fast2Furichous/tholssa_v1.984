package devops.GraphService.model.implementations;

import java.util.Collection;
import java.util.HashSet;

import devops.GraphService.model.interfaces.GraphEdge;
import devops.GraphService.model.interfaces.GraphNode;

public class PersonNode implements GraphNode<Person> {

	private String uniqueID;
	private Person person;
	private Collection<GraphEdge<Person>> edges;

	public PersonNode(String uniqueID, Person person) {
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
	public Person getValue() {
		return this.person;
	}

	@Override
	public void setValue(Person value) {
		this.person = value;
	}

	@Override
	public String getUniqueID() {
		return this.uniqueID;
	}

	@Override
	public Collection<GraphEdge<Person>> getEdges() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

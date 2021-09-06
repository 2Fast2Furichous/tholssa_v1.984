package devops.GraphService.model.implementations;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import devops.GraphService.model.interfaces.GraphEdge;
import devops.GraphService.model.interfaces.GraphNode;

public class PersonEdge implements GraphEdge<Person> {

	private GraphNode<Person> source;
	private GraphNode<Person> destination;
	private Collection<GraphEdge<Person>> neighbors;

	private String uniqueID;
	private String relation;
	private LocalDate dateOfConnection;
	private LocalDate dateOfConnectionEnd;

	public PersonEdge(String uniqueID, GraphNode<Person> source, GraphNode<Person> destination) {
		this.uniqueID = uniqueID;
		this.neighbors = new HashSet<GraphEdge<Person>>();
		this.source = source;
		this.destination = destination;
	}
	
	@Override
	public GraphNode<Person> getSource() {
		return this.source;
	}

	public LocalDate getDateOfConnectionEnd() {
		return dateOfConnectionEnd;
	}

	public void setDateOfConnectionEnd(LocalDate dateOfConnectionEnd) {
		this.dateOfConnectionEnd = dateOfConnectionEnd;
	}

	public LocalDate getDateOfConnection() {
		return dateOfConnection;
	}

	public void setDateOfConnection(LocalDate dateOfConnection) {
		this.dateOfConnection = dateOfConnection;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Override
	public GraphNode<Person> getDestination() {
		return this.destination;
	}

	@Override
	public void setSource(GraphNode<Person> node) {
		this.source = node;
	}

	@Override
	public void setDestination(GraphNode<Person> node) {
		this.destination = node;
	}

	@Override
	public boolean addNeighbor(GraphEdge<Person> edge) {
		return neighbors.add(edge);
	}

	@Override
	public boolean removeNeighbor(GraphEdge<Person> edge) {
		return neighbors.remove(edge);
	}

	@Override
	public Collection<GraphEdge<Person>> getNeighbors() {
		return this.neighbors;
	}

	@Override
	public String getUniqueID() {
		return this.uniqueID;
	}
	
}

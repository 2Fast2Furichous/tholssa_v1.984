package devops.GraphService.model.implementations;

import java.time.LocalDate;

import devops.GraphService.model.interfaces.GraphEdge;
import devops.GraphService.model.interfaces.GraphNode;

public class PersonEdge implements GraphEdge<Person> {

	private final GraphNode<Person> source;
	private final GraphNode<Person> destination;

	private final String uniqueID;
	private Relationship relation;
	private LocalDate dateOfConnection;
	private LocalDate dateOfConnectionEnd;

	public PersonEdge(String uniqueID, GraphNode<Person> source, GraphNode<Person> destination, Relationship relation, LocalDate dateOfConnection, 
			LocalDate dateOfConnectionEnd) {
		if (uniqueID == null || uniqueID.isBlank()) {
			throw new IllegalArgumentException("Unique ID must not be null or blank");
		}
		if (source == null) {
			throw new IllegalArgumentException("Source must not be null");
		}
		if (destination == null) {
			throw new IllegalArgumentException("Destination must not be null");
		}
		this.uniqueID = uniqueID;
		this.source = source;
		this.destination = destination;
		this.source.addEdge(this);
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

	public Relationship getRelation() {
		return relation;
	}

	public void setRelation(Relationship relation) {
		this.relation = relation;
	}

	@Override
	public GraphNode<Person> getDestination() {
		return this.destination;
	}

	@Override
	public String getUniqueID() {
		return this.uniqueID;
	}
	
}

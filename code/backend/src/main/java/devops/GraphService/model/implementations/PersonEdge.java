package devops.GraphService.model.implementations;

import java.time.LocalDate;

import devops.GraphService.model.interfaces.GraphEdge;
import devops.GraphService.model.interfaces.GraphNode;

/**
 * Edge between person nodes
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class PersonEdge implements GraphEdge<Person> {

	private final GraphNode<Person> source;
	private final GraphNode<Person> destination;

	private final String uniqueID;
	private Relationship relation;
	private LocalDate dateOfConnection;
	private LocalDate dateOfConnectionEnd;

	/**
	 * 
	 * Creates a new person edge given the uniqueID, source, destination, and
	 * connection details
	 * 
	 * @preconditions uniqueID != null && !uniqueID.isBlank() && source != null &&
	 *                destination != null
	 * @postconditions getUniqueID() == uniqueID && getSource() == source &&
	 *                 getDestination() == destination && getRelation() == relation
	 *                 && getDateOfConnection() == dateOfConnection &&
	 *                 getDateOfConnectionEnd() == dateOfConnectionEnd
	 * 
	 * 
	 * @param uniqueID
	 * @param source
	 * @param destination
	 * @param relation
	 * @param dateOfConnection
	 * @param dateOfConnectionEnd
	 */
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
	

	/**
	 * 
	 * Gets the relation between nodes
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the relation between nodes
	 * 
	 */
	public Relationship getRelation() {
		return relation;
	}

	/**
	 * 
	 * Gets the date of connection
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the date of connection
	 * 
	 */
	public LocalDate getDateOfConnection() {
		return dateOfConnection;
	}

	/**
	 * 
	 * Gets the date of connection end
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the date of connection end
	 * 
	 */

	public LocalDate getDateOfConnectionEnd() {
		return dateOfConnectionEnd;
	}

	/**
	 * 
	 * Gets the relation between nodes
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the relation between nodes
	 * 
	 */
	public void setRelation(Relationship relation) {
		this.relation = relation;
	}

	/**
	 * 
	 * Sets the date of connection
	 * 
	 * @precondition none
	 * @postcondition getDateOfConnection() == dateOfConnection
	 * 
	 * @param dateOfConnection
	 * 
	 */
	public void setDateOfConnection(LocalDate dateOfConnection) {
		this.dateOfConnection = dateOfConnection;
	}

	/**
	 * 
	 * Sets the date of connection end
	 * 
	 * @precondition none
	 * @postcondition getDateOfConnectionEnd() == dateOfConnectionEnd
	 * 
	 * @param dateOfConnectionEnd
	 * 
	 */
	public void setDateOfConnectionEnd(LocalDate dateOfConnectionEnd) {
		this.dateOfConnectionEnd = dateOfConnectionEnd;
	}

	@Override
	public GraphNode<Person> getSource() {
		return this.source;
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

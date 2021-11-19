package devops.model.implementations;

import java.time.LocalDate;

import devops.model.interfaces.GraphEdge;

/**
 * Edge between person nodes
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class PersonEdge implements GraphEdge<Person> {

	private final String source;
	private final String destination;

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
	public PersonEdge(String uniqueID, String source, String destination, Relationship relation,
			LocalDate dateOfConnection, LocalDate dateOfConnectionEnd) {
		this.uniqueID = uniqueID;
		this.source = source;
		this.destination = destination;
		this.relation = relation;
		this.dateOfConnection = dateOfConnection;
		this.dateOfConnectionEnd = dateOfConnectionEnd;
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
		return this.relation;
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
		return this.dateOfConnection;
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
		return this.dateOfConnectionEnd;
	}

	/**
	 * 
	 * Gets the relation between nodes
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param relation the relation between nodes
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
	public String getSource() {
		return this.source;
	}

	@Override
	public String getDestination() {
		return this.destination;
	}

	@Override
	public String getUniqueID() {
		return this.uniqueID;
	}

}

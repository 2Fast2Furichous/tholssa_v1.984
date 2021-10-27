package devops.network.interfaces;

import java.time.LocalDate;
import java.util.Collection;

import devops.model.implementations.NodeFilter;
import devops.model.implementations.Relationship;
import devops.model.implementations.ServiceResponse;

public interface GraphService {
	
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
	public ServiceResponse createNode(
			double positionX, 
			double positionY, String nickname, String firstName, String lastName, String address,
			String phoneNumber, LocalDate dateOfBirth, LocalDate dateOfDeath, String occupation, String description);

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
	public ServiceResponse connectNodes(String sourceGuid, String destinationGuid, Relationship relation,
			LocalDate dateOfConnection, LocalDate dateOfConnectionEnd);

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
	public ServiceResponse updateNode(
			double positionX, 
			double positionY, String guid, String nickname, String firstName, String lastName, String address,
			String phoneNumber, LocalDate dateOfBirth, LocalDate dateOfDeath, String occupation, String description);

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
	public ServiceResponse updateEdge(String guid, Relationship relation, LocalDate dateOfConnection,
			LocalDate dateOfConnectionEnd);

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
	public ServiceResponse removeNode(String guid);

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
	public ServiceResponse removeEdge(String guid);

	/**
	 * Returns the graph network from the root node and edge filters.
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @param rootNodeGuid
	 * @param filters
	 * @return graph network given the filters and rootNode
	 * @throws IllegalArgumentException
	 */
	public ServiceResponse getFilteredNetwork(String rootNodeGuid, Collection<NodeFilter> filters);

}

package devops.GraphService.model.implementations;

import java.util.function.Predicate;

import devops.GraphService.model.interfaces.GraphEdge;

/**
 * Service for interfacing with storage and edtiing graph nodes and edges
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public enum NodeFilter {
	Family;

	/**
	 * Returns the predicate given the filter
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the predicate given the filter
	 */
	public Predicate<GraphEdge<Person>> getPredicate() {
		switch(this) {
			case Family:
			return (GraphEdge<Person> edge) -> {
				PersonEdge personEdge = (PersonEdge) edge;
				Relationship relationship = personEdge.getRelation();
				return relationship == Relationship.Child || relationship == Relationship.Parent;
			};
			default:
				return (GraphEdge<Person> edge) -> true;
		}
	}
}

package devops.model.implementations;

import java.util.function.Predicate;

import devops.model.interfaces.GraphEdge;

/**
 * Service for interfacing with storage and edtiing graph nodes and edges
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public enum NodeFilter {
	Family, Business, Friend;

	/**
	 * Returns the predicate given the filter
	 * 
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return the predicate given the filter
	 */
	public Predicate<GraphEdge<Person>> getPredicate() {
		switch (this) {
			case Family:
				return (GraphEdge<Person> edge) -> {
					PersonEdge personEdge = (PersonEdge) edge;
					Relationship relationship = personEdge.getRelation();
					return relationship == Relationship.Child || relationship == Relationship.Parent;
				};
			case Friend:
				return (GraphEdge<Person> edge) -> {
					PersonEdge personEdge = (PersonEdge) edge;
					Relationship relationship = personEdge.getRelation();
					return relationship == Relationship.Friend;
				};
			case Business:
				return (GraphEdge<Person> edge) -> {
					PersonEdge personEdge = (PersonEdge) edge;
					Relationship relationship = personEdge.getRelation();
					return relationship == Relationship.Business;
				};
			default:
				return (GraphEdge<Person> edge) -> true;
		}
	}
}

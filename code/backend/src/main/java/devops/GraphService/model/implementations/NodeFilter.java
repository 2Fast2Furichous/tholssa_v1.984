package devops.GraphService.model.implementations;

import java.util.function.Predicate;

import devops.GraphService.model.interfaces.GraphEdge;

public enum NodeFilter {
	Family;

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

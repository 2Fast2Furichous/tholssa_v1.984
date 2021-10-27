package devops.model.node_filter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.Relationship;
import devops.model.interfaces.GraphEdge;

public class TestGetPredicate {
	@Test
	void testValidPerson() {
		Predicate<GraphEdge<Person>> filter = NodeFilter.Family.getPredicate();
		assertNotNull(filter);

		PersonEdge testEdge = new PersonEdge("123", "123", "1234", Relationship.Child, null, null);

		assertTrue(filter.test(testEdge));
	}
}

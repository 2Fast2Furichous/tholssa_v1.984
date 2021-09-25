package devops.model.PersonEdgeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import devops.model.implementations.PersonEdge;
import devops.model.implementations.Relationship;

public class SetRelation {
	@Test
	void testValidRelation() {
		PersonEdge testEdge = new PersonEdge("123", "123", "1234", null, null, null);
		testEdge.setRelation(Relationship.Child);
		assertEquals(Relationship.Child, testEdge.getRelation());
	}

	@Test
	void testNullRelation() {
		PersonEdge testEdge = new PersonEdge("123", "123", "1234", null, null, null);
		testEdge.setRelation(null);
		assertEquals(null, testEdge.getRelation());
	}
}

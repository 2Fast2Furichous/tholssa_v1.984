package devops.model.person_node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;

public class TestRemoveEdge {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);

	@Test
	void testValidPersonNode() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		PersonNode testNode1 = new PersonNode("123", testPerson1);
		testNode1.addEdge("123");
		assertTrue(testNode1.removeEdge("123"));
		assertEquals(0, testNode1.getEdges().size());
	}

	@Test
	void testDuplicate() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		PersonNode testNode1 = new PersonNode("123", testPerson1);
		assertFalse(testNode1.removeEdge("123"));
		assertEquals(0, testNode1.getEdges().size());
	}

	@Test
	void testNullEdge() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		PersonNode testNode1 = new PersonNode("123", testPerson1);
		assertThrows(IllegalArgumentException.class, () -> {
			testNode1.removeEdge(null);
		});
	}

	@Test
	void testEmptyEdge() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		PersonNode testNode1 = new PersonNode("123", testPerson1);
		assertThrows(IllegalArgumentException.class, () -> {
			testNode1.removeEdge("");
		});
	}
}

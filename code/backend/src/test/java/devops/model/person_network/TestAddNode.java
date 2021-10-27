package devops.model.person_network;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;

public class TestAddNode {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);
	@Test
	void testAddValid() {
		PersonNetwork testNetwork = new PersonNetwork();
		Person testPerson = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		PersonNode testNode = new PersonNode("123", testPerson);
		assertTrue(testNetwork.addNode(testNode));

		assertEquals(1, testNetwork.getNodes().size());
	}

	@Test
	void testAddNull() {
		PersonNetwork testNetwork = new PersonNetwork();

		assertThrows(IllegalArgumentException.class, () -> {
			testNetwork.addNode(null);
		});
	}
}

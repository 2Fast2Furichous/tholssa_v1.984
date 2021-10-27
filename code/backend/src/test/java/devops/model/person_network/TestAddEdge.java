package devops.model.person_network;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;

public class TestAddEdge {
	@Test
	void testAddValid() {
		PersonNetwork testNetwork = new PersonNetwork();
		PersonEdge testEdge = new PersonEdge("123", "123", "1234", null, null, null);

		assertTrue(testNetwork.addEdge(testEdge));

		assertEquals(1, testNetwork.getEdges().size());
	}

	@Test
	void testAddNull() {
		PersonNetwork testNetwork = new PersonNetwork();

		assertThrows(IllegalArgumentException.class, () -> {
			testNetwork.addEdge(null);
		});
	}
}

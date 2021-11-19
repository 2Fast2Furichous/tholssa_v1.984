package devops.model.person_network;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;

public class TestConstructor {

	@Test
	void testValidZeroParameterPersonNetwork() {
		PersonNetwork testNetwork = new PersonNetwork();

		assertNotNull(testNetwork.getNodes());
		assertNotNull(testNetwork.getEdges());

		assertEquals(0, testNetwork.getNodes().size());
		assertEquals(0, testNetwork.getEdges().size());
	}

	@Test
	void testValidTwoParameterPersonNetwork() {
		Collection<PersonNode> nodes = new ArrayList<PersonNode>();
		Collection<PersonEdge> edges = new ArrayList<PersonEdge>();

		PersonNetwork testNetwork = new PersonNetwork(nodes, edges);

		assertEquals(nodes, testNetwork.getNodes());
		assertEquals(edges, testNetwork.getEdges());
	}

	@Test
	void testNullNodes() {
		Collection<PersonEdge> edges = new ArrayList<PersonEdge>();

		assertThrows(IllegalArgumentException.class, () -> {
			new PersonNetwork(null, edges);
		});

	}

	@Test
	void testNullEdges() {
		Collection<PersonNode> nodes = new ArrayList<PersonNode>();

		assertThrows(IllegalArgumentException.class, () -> {
			new PersonNetwork(nodes, null);
		});
	}
}

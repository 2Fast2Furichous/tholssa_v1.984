package devops.model.PersonNetworkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;

public class Constructor {

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
}

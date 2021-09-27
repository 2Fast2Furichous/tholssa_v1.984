package devops.services.graph_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.interfaces.GraphEdge;
import devops.model.interfaces.GraphNode;
import devops.services.GraphService;

public class TestGetEdge {

	private GraphService service;

	@BeforeEach
	public void setupService() {
		this.service = new GraphService();
	}

	@Test
	public void When_Valid_Expect_Edge() {
		Person person1 = new Person(0, 0,"test1", null, null, null, null, null, null, null, null);
		Person person2 = new Person(0, 0,"test2", null, null, null, null, null, null, null, null);
		String nodeGuid1 = this.service.createNode(person1);
		String nodeGuid2 = this.service.createNode(person2);

		GraphNode<Person> node1 = this.service.getNode(nodeGuid1);
		GraphNode<Person> node2 = this.service.getNode(nodeGuid2);

		String edgeGuid = this.service.connectNodes(nodeGuid1, nodeGuid2, null, null, null);
		GraphEdge<Person> edge = this.service.getEdge(edgeGuid);

		assertNotNull(edgeGuid);
		assertEquals(edgeGuid, edge.getUniqueID());
		assertEquals(nodeGuid1, edge.getSource());
		assertEquals(nodeGuid2, edge.getDestination());
	}

	@Test
	public void When_Null_Expect_Exception() {
		assertThrows(IllegalArgumentException.class, () -> this.service.getEdge(null));
	}
}

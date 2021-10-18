package devops.services.graph_service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;
import devops.model.interfaces.GraphEdge;
import devops.services.GraphService;

public class TestGetFilteredNetwork {
	private GraphService service;

	@BeforeEach
	public void setupService() {
		this.service = new GraphService();
	}

	@Test
	public void When_Empty_Filters_Expect_All() {
		Person person1 = new Person(0, 0, "test1", null, null, null, null, null, null, null, null);
		Person person2 = new Person(0, 0, "test2", null, null, null, null, null, null, null, null);
		String nodeGuid1 = this.service.createNode(person1);
		String nodeGuid2 = this.service.createNode(person2);

		PersonNode node1 = this.service.getNode(nodeGuid1);
		PersonNode node2 = this.service.getNode(nodeGuid2);

		String edgeGuid = this.service.connectNodes(nodeGuid1, nodeGuid2, null, null, null);
		GraphEdge<Person> edge = this.service.getEdge(edgeGuid);

		PersonNetwork network = this.service.getFilteredNetwork("", new ArrayList<NodeFilter>());

		assertTrue(network.getNodes().contains(node1));
		assertTrue(network.getNodes().contains(node2));
		assertTrue(network.getEdges().contains(edge));
	}

	@Test
	public void When_Empty_Network_Expect_None() {
		PersonNetwork network = this.service.getFilteredNetwork("", new ArrayList<NodeFilter>());

		assertTrue(network.getNodes().isEmpty());
		assertTrue(network.getNodes().isEmpty());
	}

	@Test
	public void When_None_Match_Network_Expect_Root() {

		Person person1 = new Person(0, 0, "test1", null, null, null, null, null, null, null, null);
		Person person2 = new Person(0, 0, "test2", null, null, null, null, null, null, null, null);
		String nodeGuid1 = this.service.createNode(person1);
		String nodeGuid2 = this.service.createNode(person2);

		PersonNode node1 = this.service.getNode(nodeGuid1);
		PersonNode node2 = this.service.getNode(nodeGuid2);

		String edgeGuid = this.service.connectNodes(nodeGuid1, nodeGuid2, null, null, null);
		GraphEdge<Person> edge = this.service.getEdge(edgeGuid);

		var filters = new ArrayList<NodeFilter>();
		filters.add(NodeFilter.Family);
		PersonNetwork network = this.service.getFilteredNetwork(nodeGuid1, filters);

		assertTrue(network.getNodes().contains(node1));
		assertTrue(network.getEdges().isEmpty());
	}

	@Test
	public void When_Multiple_Match_Network_Expect_Multiple() {

		Person person1 = new Person(0, 0, "test1", null, null, null, null, null, null, null, null);
		Person person2 = new Person(0, 0, "test2", null, null, null, null, null, null, null, null);
		String nodeGuid1 = this.service.createNode(person1);
		String nodeGuid2 = this.service.createNode(person2);

		PersonNode node1 = this.service.getNode(nodeGuid1);
		PersonNode node2 = this.service.getNode(nodeGuid2);

		String edgeGuid = this.service.connectNodes(nodeGuid1, nodeGuid2, Relationship.Parent, null, null);
		GraphEdge<Person> edge = this.service.getEdge(edgeGuid);

		var filters = new ArrayList<NodeFilter>();
		filters.add(NodeFilter.Family);
		PersonNetwork network = this.service.getFilteredNetwork(nodeGuid1, filters);

		assertTrue(network.getNodes().contains(node1));
		assertTrue(network.getNodes().contains(node2));
		assertTrue(network.getEdges().contains(edge));
	}
}

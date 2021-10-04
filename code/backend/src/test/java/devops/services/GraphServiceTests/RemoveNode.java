package devops.services.GraphServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.interfaces.GraphNode;
import devops.services.GraphService;

public class RemoveNode {
	private GraphService service;

	@BeforeEach
	public void setupService() {
		this.service = new GraphService();
	}

	@Test
	public void When_Valid_Expect_Person() {
		Person person = new Person(0, 0,null, null, null, null, null, null, null, null, null);
		String guid = this.service.createNode(person);

		GraphNode<Person> node = this.service.removeNode(guid);
		assertNull(this.service.getNode(guid));
		assertEquals(person, node.getValue());
		
	}

	@Test
	public void When_Null_Expect_Exception() {
		assertThrows(IllegalArgumentException.class, () -> this.service.removeNode(null));
	}

	@Test
	public void When_Duplicate_Created_Expect_Distinct_Nodes() {
		Person person = new Person(0, 0,null, null, null, null, null, null, null, null, null);
		String guid1 = this.service.createNode(person);
		String guid2 = this.service.createNode(person);

		GraphNode<Person> node1 = this.service.removeNode(guid1);
		GraphNode<Person> node2 = this.service.removeNode(guid2);
		assertNull(this.service.getNode(guid1));
		assertNull(this.service.getNode(guid2));
		assertEquals(person, node1.getValue());
		assertEquals(person, node2.getValue());
	}
}

package devops.services.GraphServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.interfaces.GraphNode;
import devops.services.GraphService;

public class GetNode {
	private GraphService service;

	@BeforeEach
	public void setupService() {
		this.service = new GraphService();
	}

	@Test
	public void When_Valid_Expect_Person() {
		Person person = new Person(0, 0,null, null, null, null, null, null, null, null, null);
		String guid = this.service.createNode(person);

		GraphNode<Person> node = this.service.getNode(guid);

		assertNotNull(guid);
		assertEquals(guid, node.getUniqueID());
		assertEquals(person, node.getValue());
	}

	@Test
	public void When_Null_Expect_Exception() {
		assertThrows(IllegalArgumentException.class, () -> this.service.getNode(null));
	}
}

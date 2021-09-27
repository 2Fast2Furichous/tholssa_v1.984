package devops.services.graph_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.services.GraphService;

public class TestCreateNode {
	
	private GraphService service;

	@BeforeEach
	public void setupService() {
		this.service = new GraphService();
	}

	@Test
	public void When_Valid_Expect_Person() {
		Person person = new Person(0, 0,null, null, null, null, null, null, null, null, null);
		String guid = this.service.createNode(person);

		assertNotNull(guid);
		assertEquals(person, this.service.getNode(guid).getValue());
	}

	@Test
	public void When_Null_Expect_Exception() {
		assertThrows(IllegalArgumentException.class, () -> this.service.createNode(null));
	}

	@Test
	public void When_Duplicate_Created_Expect_Distinct_Nodes() {
		Person person = new Person(0, 0,null, null, null, null, null, null, null, null, null);
		String guid = this.service.createNode(person);
		String guid2 = this.service.createNode(person);

		assertNotNull(guid);
		assertNotNull(guid2);
		assertEquals(person, this.service.getNode(guid).getValue());
		assertEquals(person, this.service.getNode(guid2).getValue());
	}
}

package devops.services.graph_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;
import devops.services.GraphService;

public class TestUpdateNode {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);
	private GraphService service;

	@BeforeEach
	public void setupService() {
		this.service = new GraphService();
	}

	@Test
	public void When_Valid_Expect_Edge() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname1", "firstName1", "lastName1", "address1", "1234567890",
				validDate, validDate, "occupation1", "description1");

			Person testPerson2 = new Person(1.0, 1.0, "nickname2", "firstName2", "lastName2", "address2", "1234567890",
			validDate, validDate, "occupation2", "description2");

		String nodeGuid1 = this.service.createNode(testPerson1);

		PersonNode node = this.service.updateNode(nodeGuid1, testPerson2);

		assertEquals(testPerson2, node.getValue());
	}

	@Test
	public void When_Null_ID_Expect_Exception() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname1", "firstName1", "lastName1", "address1", "1234567890",
				validDate, validDate, "occupation1", "description1");

		String nodeGuid1 = this.service.createNode(testPerson1);
		assertThrows(IllegalArgumentException.class,
				() -> this.service.updateNode(null, testPerson1));
	}
}

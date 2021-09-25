package devops.services.GraphServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.Relationship;
import devops.model.interfaces.GraphNode;
import devops.services.GraphService;

public class UpdateEdge {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);
	private GraphService service;

	@BeforeEach
	public void setupService() {
		this.service = new GraphService();
	}

	@Test
	public void When_Valid_Expect_Edge() {
		Person person1 = new Person(0, 0, "test1", null, null, null, null, null, null, null, null);
		Person person2 = new Person(0, 0, "test2", null, null, null, null, null, null, null, null);
		String nodeGuid1 = this.service.createNode(person1);
		String nodeGuid2 = this.service.createNode(person2);

		String edgeGuid = this.service.connectNodes(nodeGuid1, nodeGuid2, null, null, null);

		PersonEdge edge = this.service.updateEdge(edgeGuid, Relationship.Child, validDate, validDate);

		assertEquals(Relationship.Child, edge.getRelation());
		assertEquals(validDate, edge.getDateOfConnection());
		assertEquals(validDate, edge.getDateOfConnectionEnd());
	}

	@Test
	public void When_Null_ID_Expect_Exception() {
		assertThrows(IllegalArgumentException.class, () -> this.service.updateEdge(null, Relationship.Child, 
				validDate,
				validDate));
	}
}

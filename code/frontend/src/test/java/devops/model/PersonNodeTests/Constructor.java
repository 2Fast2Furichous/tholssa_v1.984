package devops.model.PersonNodeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;

public class Constructor {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);

	@Test
	void testValidPersonNode() {
		Person testPerson = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		PersonNode testNode = new PersonNode("123", testPerson);
		assertEquals("123", testNode.getUniqueID());
		assertEquals(testPerson, testNode.getValue());
		assertEquals(0, testNode.getEdges().size());
	}
}

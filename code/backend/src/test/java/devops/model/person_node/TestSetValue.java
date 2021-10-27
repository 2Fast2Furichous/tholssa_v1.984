package devops.model.person_node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;

public class TestSetValue {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);

	@Test
	void testValidPersonNode() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		Person testPerson2 = new Person(1.0, 1.0, "nickname", "firstName", "lastName", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		PersonNode testNode = new PersonNode("123", testPerson1);
		testNode.setValue(testPerson2);

		assertEquals(testPerson2, testNode.getValue());
	}
}

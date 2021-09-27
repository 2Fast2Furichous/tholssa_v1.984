package devops.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;

public class TestConstructor {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);

	@Test
	void testValidPerson() {
		Person testPerson = new Person(1.0,1.0,"nickname", "firstName", "lastName", "address", "1234567890", validDate, validDate, "occupation", "description");

		assertEquals(1.0, testPerson.getPositionX());
		assertEquals(1.0, testPerson.getPositionY());
		assertEquals("nickname", testPerson.getNickname());
		assertEquals("firstName", testPerson.getFirstName());
		assertEquals("lastName", testPerson.getLastName());
		assertEquals("address", testPerson.getAddress());
		assertEquals("1234567890", testPerson.getPhoneNumber());
		assertEquals(validDate, testPerson.getDateOfBirth());
		assertEquals(validDate, testPerson.getDateOfDeath());
		assertEquals("occupation", testPerson.getOccupation());
		assertEquals("description", testPerson.getDescription());
	}
}

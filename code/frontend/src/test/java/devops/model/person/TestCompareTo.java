package devops.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;

public class TestCompareTo {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);

	@Test
	void testFirstDifferent() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName1", "lastName2", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		Person testPerson2 = new Person(1.0, 1.0, "nickname", "firstName2", "lastName1", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		assertEquals(1, testPerson1.compareTo(testPerson2));
	}

	@Test
	void testFirstSame() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName1", "lastName2", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		Person testPerson2 = new Person(1.0, 1.0, "nickname", "firstName1", "lastName1", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		assertEquals(1, testPerson1.compareTo(testPerson2));
	}

	@Test
	void testLastSame() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName1", "lastName1", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		Person testPerson2 = new Person(1.0, 1.0, "nickname", "firstName2", "lastName1", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		assertEquals(0, testPerson1.compareTo(testPerson2));
	}

	@Test
	void testLastDifferent() {
		Person testPerson1 = new Person(1.0, 1.0, "nickname", "firstName1", "lastName2", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		Person testPerson2 = new Person(1.0, 1.0, "nickname", "firstName2", "lastName1", "address", "1234567890",
				validDate, validDate, "occupation", "description");

		assertEquals(1, testPerson1.compareTo(testPerson2));
	}
}

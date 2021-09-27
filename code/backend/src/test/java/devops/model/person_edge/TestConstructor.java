package devops.model.person_edge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;

public class TestConstructor {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);

	@Test
	void testValidPersonEdge() {
		PersonEdge testEdge = new PersonEdge("123", "123", "1234", Relationship.Child, validDate, validDate);

		assertEquals("123", testEdge.getUniqueID());
		assertEquals("123", testEdge.getSource());
		assertEquals("1234", testEdge.getDestination());
		assertEquals(Relationship.Child, testEdge.getRelation());
		assertEquals(validDate, testEdge.getDateOfConnection());
		assertEquals(validDate, testEdge.getDateOfConnectionEnd());
	}

	@Test
	void testEmptyUniqueID() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PersonEdge("", "123", "1234", Relationship.Child, validDate, validDate);
		});
	}

	@Test
	void testNullUniqueID() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PersonEdge(null, "123", "1234", Relationship.Child, validDate, validDate);
		});
	}

	@Test
	void testEmptySource() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PersonEdge("123", "", "1234", Relationship.Child, validDate, validDate);
		});
	}

	@Test
	void testNullSource() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PersonEdge("123", null, "1234", Relationship.Child, validDate, validDate);
		});
	}

	@Test
	void testEmptyDestination() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PersonEdge("123", "123", "", Relationship.Child, validDate, validDate);
		});
	}

	@Test
	void testNullDestination() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PersonEdge("123", "123", null, Relationship.Child, validDate, validDate);
		});
	}
}

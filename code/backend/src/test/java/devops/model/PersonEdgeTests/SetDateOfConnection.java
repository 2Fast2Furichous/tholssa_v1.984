package devops.model.PersonEdgeTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.PersonEdge;

public class SetDateOfConnection {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);
	
	@Test
	void testValidDateOfConnection() {
		PersonEdge testEdge = new PersonEdge("123", "123", "1234", null, null, null);
		testEdge.setDateOfConnection(validDate);
		assertEquals(validDate, testEdge.getDateOfConnection());
	}

	@Test
	void testNullDateOfConnection() {
		PersonEdge testEdge = new PersonEdge("123", "123", "1234", null, null, null);
		testEdge.setDateOfConnection(null);
		assertEquals(null, testEdge.getDateOfConnection());
	}
}

package devops.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.User;

public class TestSetLastX {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);
	
	@Test
	void testValidX() {
		User actual = new User("Mark", "Ronson", validDate, "1234567890", "001");
		actual.setLastX(1.0);
		
		assertEquals(1.0, actual.getLastX(), 0.001);
	}
}

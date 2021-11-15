package devops.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.User;

public class TestSetLastY {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);
	
	@Test
	void testValidY() {
		User actual = new User("Mark", "Ronson", validDate, "1234567890", "001");
		actual.setLastY(1.0);
		
		assertEquals(1.0, actual.getLastY(), 0.001);
	}
}

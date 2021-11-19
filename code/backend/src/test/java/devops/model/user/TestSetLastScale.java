package devops.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.User;

public class TestSetLastScale {
	private LocalDate validDate = LocalDate.of(1970, 10, 17);
	
	@Test
	void testValidScale() {
		User actual = new User("Mark", "Ronson", validDate, "1234567890", "001");
		actual.setLastScale(2.0);
		
		assertEquals(2.0, actual.getLastScale(), 0.001);
	}
}

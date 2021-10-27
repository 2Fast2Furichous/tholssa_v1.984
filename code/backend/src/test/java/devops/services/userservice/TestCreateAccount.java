package devops.services.userservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.User;
import devops.services.UserService;

public class TestCreateAccount {
    @Test
    void testNullUserExpectException(){
        UserService service = new UserService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.createAccount(null);
        });
    }

    @Test
    void testValidUserExpectUserReturn(){
        UserService service = new UserService();
        User actual = service.createAccount(new User("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001"));
        User expected =  new User("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");

        assertEquals(expected, actual);
    }

    @Test
    void testValidUserParametersExpectUserReturn(){
        UserService service = new UserService();
        User actual = service.createAccount("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");
        User expected =  new User("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");

        assertEquals(expected, actual);
    }
}

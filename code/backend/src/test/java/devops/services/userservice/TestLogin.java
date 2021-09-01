package devops.services.userservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.User;
import devops.services.UserService;

public class TestLogin {
    
    @Test
    void testValidLogin(){
        UserService service = new UserService();
        User expected = service.createAccount("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");

        User actual = service.login("001");
        assertEquals(expected, actual);
    }
}

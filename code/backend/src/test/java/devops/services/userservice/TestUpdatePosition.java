package devops.services.userservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.User;
import devops.services.UserService;

public class TestUpdatePosition {
    
    @Test
    void testValidLogin(){
        UserService service = new UserService();
        User user = service.createAccount("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");

        service.updateLastPosition("001", 10, 11, 12);
        assertEquals(10, user.getLastX(), 0.001);
        assertEquals(11, user.getLastY(), 0.001);
        assertEquals(12, user.getLastScale(), 0.001);
    }
}

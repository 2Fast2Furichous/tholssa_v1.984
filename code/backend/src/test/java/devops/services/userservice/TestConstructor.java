package devops.services.userservice;

import org.junit.jupiter.api.Test;

import devops.services.UserService;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstructor {
    
    @Test
    void testDefaultConstructor(){
        UserService service = new UserService();

        assertNotNull(service.getUsers());
    }
}

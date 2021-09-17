package devops.model.credentials;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.model.Credentials;

public class TestConstructor {
    @Test
    void testPasswordNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credentials(null, "bob123");
        });
    }

    @Test
    void testPasswordBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credentials("", "bob123");
        });
    }

    @Test
    void testUsernameNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credentials("pswrd", null);
        });
    }

    @Test
    void testUsernameBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credentials("pswrd", "");
        });
    }

    @Test
    void testValidDataExpectInstantiation(){
        Credentials cred = new Credentials("pswrd", "bob123");

        assertEquals("pswrd", cred.getPassword());
        assertEquals("bob123", cred.getUsername());
    }
}

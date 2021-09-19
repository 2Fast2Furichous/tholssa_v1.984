package devops.model.credential;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Credential;

public class TestCredential {
    @Test
    void testPasswordNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credential(null, "bob123");
        });
    }

    @Test
    void testPasswordBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credential("", "bob123");
        });
    }

    @Test
    void testUsernameNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credential("pswrd", null);
        });
    }

    @Test
    void testUsernameBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Credential("pswrd", "");
        });
    }

    @Test
    void testValidDataExpectInstantiation(){
        Credential cred = new Credential("pswrd", "bob123");

        assertEquals("pswrd", cred.getPassword());
        assertEquals("bob123", cred.getUsername());
    }
}

package devops.data.implementations.credentialstorage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.data.implementations.CredentialStorage;
import devops.model.Credentials;

public class TestAdd {
    
    @Test
    void testAddToEmptyExpectTrue(){
        CredentialStorage credentials = new CredentialStorage();

        assertTrue(credentials.add(new Credentials("pass1", "user1"), "001"));
        assertEquals(1, credentials.getAccessKeys().size());
    }

    @Test
    void testAddToSingletonNoMatchExpectTrue(){
        CredentialStorage credentials = new CredentialStorage();
        credentials.add(new Credentials("pass1", "user1"), "001");

        assertTrue(credentials.add(new Credentials("pass2", "user2"), "002"));
        assertEquals(2, credentials.getAccessKeys().size());
    }
    @Test
    void testAddToSingletonKeyMatchExpectFalse(){
        CredentialStorage credentials = new CredentialStorage();
        credentials.add(new Credentials("pass1", "user1"), "001");

        assertFalse(credentials.add(new Credentials("pass1", "user1"), "002"));
        assertEquals(1, credentials.getAccessKeys().size());
    }
    @Test
    void testAddToSingletonValueMatchExpectFalse(){
        CredentialStorage credentials = new CredentialStorage();
        credentials.add(new Credentials("pass1", "user1"), "001");

        assertFalse(credentials.add(new Credentials("pass2", "user2"), "001"));
        assertEquals(1, credentials.getAccessKeys().size());
    }


    @Test
    void testAddToStorageWithManyNoMatchExpectTrue(){
        CredentialStorage credentials = new CredentialStorage();
        credentials.add(new Credentials("pass1", "user1"), "001");
        credentials.add(new Credentials("pass2", "user2"), "002");
        credentials.add(new Credentials("pass3", "user3"), "003");

        assertTrue(credentials.add(new Credentials("pass4", "user4"), "004"));
        assertEquals(4, credentials.getAccessKeys().size());
    }

    @Test
    void testAddToStorageWithManyKeyMatchExpectFalse(){
        CredentialStorage credentials = new CredentialStorage();
        credentials.add(new Credentials("pass1", "user1"), "001");
        credentials.add(new Credentials("pass2", "user2"), "002");
        credentials.add(new Credentials("pass3", "user3"), "003");

        assertFalse(credentials.add(new Credentials("pass1", "user1"), "004"));
        assertEquals(3, credentials.getAccessKeys().size());
    }

    @Test
    void testAddToStorageWithManyValueMatchExpectFalse(){
        CredentialStorage credentials = new CredentialStorage();
        credentials.add(new Credentials("pass1", "user1"), "001");
        credentials.add(new Credentials("pass2", "user2"), "002");
        credentials.add(new Credentials("pass3", "user3"), "003");

        assertFalse(credentials.add(new Credentials("pass4", "user4"), "001"));
        assertEquals(3, credentials.getAccessKeys().size());
    }
}

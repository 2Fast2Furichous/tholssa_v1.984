package devops.data.implementations.credentialstorage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.data.implementations.CredentialStorage;
import devops.model.Credentials;

public class TestGet {
    private Credentials cred = new Credentials("pass1", "user1");
    private Credentials cred2 = new Credentials("pass2", "user2");
    private Credentials cred3 = new Credentials("pass3", "user3");
    
    @Test
    void testGetFromEmptyStorageExpectNull(){
        CredentialStorage storage = new CredentialStorage();

        assertNull(storage.get(cred));
    }

    @Test
    void testGetFromSingleItemNoMatchExpectNull(){
        CredentialStorage storage = new CredentialStorage();
        storage.add(cred3, "003");

        assertNull(storage.get(cred));
    }

    @Test
    void testGetFromSingleItemMatchExpectValue(){
        CredentialStorage storage = new CredentialStorage();
        storage.add(cred, "001");
        
        assertEquals("001", storage.get(cred));
    }

    @Test
    void testGetFromManyItemNoMatchExpectNull(){
        CredentialStorage storage = new CredentialStorage();
        storage.add(cred2, "002");
        storage.add(cred3, "003");

        assertNull(storage.get(cred));
    }

    @Test
    void testGetFromManyItemMatchExpectValue(){
        CredentialStorage storage = new CredentialStorage();
        storage.add(cred, "001");
        storage.add(cred3, "003");
        
        assertEquals("001", storage.get(cred));
    }
}

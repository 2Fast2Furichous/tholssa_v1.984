package devops.data.implementations.credentialstorage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.data.implementations.CredentialStorage;

public class TestConstructor {
    @Test
    void testDefaultConstructorExpectInitialization(){
        CredentialStorage storage = new CredentialStorage();

        assertNotNull(storage.getAccessKeys());
    }
}

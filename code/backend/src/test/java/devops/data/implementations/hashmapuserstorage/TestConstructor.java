package devops.data.implementations.hashmapuserstorage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.data.implementations.HashMapUserStorage;


public class TestConstructor {
    @Test
    void testDefaultConstructorExpectInitialization(){
        HashMapUserStorage storage = new HashMapUserStorage();

        assertNotNull(storage.getAccounts());
    }
}

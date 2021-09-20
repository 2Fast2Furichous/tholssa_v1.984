package devops.data.implementations.hashmapuserstorage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.data.implementations.HashMapUserStorage;
import devops.data.interfaces.UserStorage;
import devops.model.implementations.User;

public class TestGet {
    @Test
    void testGetFromEmptyStorageExpectNull(){
        UserStorage storage = new HashMapUserStorage();
        
        assertNull(storage.get("001"));
    }

    @Test
    void testGetFromSingleItemMatchExpectValue(){
        UserStorage storage = new HashMapUserStorage();
        User tom = new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");
        storage.add(tom);

        User expected = new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");
        assertEquals(expected, storage.get("001"));
    }

    @Test
    void testGetFromSingleItemNoMatchExpectNull(){
        UserStorage storage = new HashMapUserStorage();
        storage.add(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001"));

        assertNull(storage.get("002"));
    }

    @Test
    void testGetFromManyItemNoMatchExpectNull(){
        UserStorage storage = new HashMapUserStorage();
        storage.add(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001"));
        storage.add(new User("Bob", "Barker", LocalDate.of(1972, 7, 12), "1237567896", "003"));
        storage.add(new User("John", "Ronson", LocalDate.of(1977, 10, 19), "4534567896", "004"));

        assertNull(storage.get("002"));
    }

    @Test
    void testGetFromManyItemMatchExpectValue(){
        UserStorage storage = new HashMapUserStorage();
        storage.add(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001"));
        storage.add(new User("Bob", "Barker", LocalDate.of(1972, 7, 12), "1237567896", "003"));
        storage.add(new User("John", "Ronson", LocalDate.of(1977, 10, 19), "4534567896", "004"));

        assertEquals(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001"), storage.get("001"));
    }
}

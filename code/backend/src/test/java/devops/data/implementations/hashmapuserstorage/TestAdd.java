package devops.data.implementations.hashmapuserstorage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.data.implementations.HashMapUserStorage;
import devops.data.interfaces.UserStorage;
import devops.model.implementations.User;

public class TestAdd {
    
    @Test
    void testAddToEmptyExpectTrue(){
        UserStorage storage = new HashMapUserStorage();

        assertTrue(storage.add(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001")));
    }

    @Test
    void testAddToSingleItemNoMatchExpectTrue(){
        UserStorage storage = new HashMapUserStorage();
        storage.add(new User("Mark", "Ronson", LocalDate.of(1973, 10, 19), "1234567896", "002"));

        assertTrue(storage.add(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001")));
    }

    @Test
    void testAddToSingleItemMatchExpectFalse(){
        UserStorage storage = new HashMapUserStorage();
        storage.add(new User("Mark", "Ronson", LocalDate.of(1973, 10, 19), "1234567896", "001"));

        assertFalse(storage.add(new User("Mark", "Ronson", LocalDate.of(1973, 10, 19), "1234567896", "001")));

    }

    @Test
    void testAddToManyItemNoMatchExpectTrue(){
        UserStorage storage = new HashMapUserStorage();
        storage.add(new User("Mark", "Ronson", LocalDate.of(1973, 10, 19), "1234567896", "002"));
        storage.add(new User("Bob", "Barker", LocalDate.of(1972, 7, 12), "1237567896", "003"));
        storage.add(new User("John", "Ronson", LocalDate.of(1977, 10, 19), "4534567896", "004"));

        assertTrue(storage.add(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001")));
    }

    @Test
    void testAddToManyItemMatchExpectFalse(){
        UserStorage storage = new HashMapUserStorage();
        storage.add(new User("Mark", "Ronson", LocalDate.of(1973, 10, 19), "1234567896", "002"));
        storage.add(new User("Bob", "Barker", LocalDate.of(1972, 7, 12), "1237567896", "003"));
        storage.add(new User("John", "Ronson", LocalDate.of(1977, 10, 19), "4534567896", "004"));

        assertFalse(storage.add(new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "002")));
    }
}

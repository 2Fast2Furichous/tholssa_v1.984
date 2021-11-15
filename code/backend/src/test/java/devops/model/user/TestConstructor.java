package devops.model.user;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.model.implementations.User;

import java.time.LocalDate;

public class TestConstructor {
    private LocalDate validDate = LocalDate.of(1970, 10, 17);

    @Test
    void testFirstNameNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User(null, "Ronson", validDate, "1234567890", "001");
        });
    }
    
    @Test
    void testFirstNameBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("", "Ronson", validDate, "1234567890", "001");
        });
    }
    
    @Test
    void testLastNameBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Mark", "", validDate, "1234567890", "001");
        });
    }
    
    @Test
    void testLastNameIsNulExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Mark", null, validDate, "1234567890", "001");
        });
    }
    
    @Test
    void testDateOfBirthNullExpectException(){
            assertThrows(IllegalArgumentException.class, () -> {
                new User("Mark", "Ronson", null, "1234567890", "001");
            });
    }
    @Test
    void testPhoneNumberNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Mark", "Ronson", validDate, null, "001");
        });
    }

    @Test
    void testPhoneNumberIsBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Mark", "Ronson", validDate, "", "001");
        });
    }

    @Test
    void testUniqueIdNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Mark", "Ronson", validDate, "1234567890", null);
        });
    }

    @Test
    void testUniqueIdBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new User("Mark", "Ronson", validDate, "1234567890", "");
        });
    }

    @Test
    void testValidDataExpectInstantiation(){
        User actual = new User("Mark", "Ronson", validDate, "1234567890", "001");
        
        assertEquals("Mark", actual.getFirstName());
        assertEquals("Ronson", actual.getLastName());
        assertEquals(validDate, actual.getDateOfBirth());
        assertEquals("1234567890", actual.getPhoneNumber());
        assertEquals("001", actual.getUniqueId());
        assertEquals(0, actual.getLastX(), 0.001);
        assertEquals(0, actual.getLastY(), 0.001);
        assertEquals(1, actual.getLastScale(), 0.001);
    }
    
}
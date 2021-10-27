package devops.model.user_account;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.model.implementations.UserAccount;

import java.time.LocalDate;

public class TestConstructor {
    private LocalDate validDate = LocalDate.of(1970, 10, 17);

    @Test
    void testFirstNameNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccount(null, "Ronson", validDate, "1234567890");
        });
    }
    
    @Test
    void testFirstNameBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccount("", "Ronson", validDate, "1234567890");
        });
    }
    
    @Test
    void testLastNameBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccount("Mark", "", validDate, "1234567890");
        });
    }
    
    @Test
    void testLastNameIsNulExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccount("Mark", null, validDate, "1234567890");
        });
    }
    
    @Test
    void testDateOfBirthNullExpectException(){
            assertThrows(IllegalArgumentException.class, () -> {
                new UserAccount("Mark", "Ronson", null, "1234567890");
            });
    }
    @Test
    void testPhoneNumberNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccount("Mark", "Ronson", validDate, null);
        });
    }

    @Test
    void testPhoneNumberIsBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccount("Mark", "Ronson", validDate, "");
        });
    }

    @Test
    void testValidDataExpectInstantiation(){
        UserAccount actual = new UserAccount("Mark", "Ronson", validDate, "1234567890");
        
        assertEquals("Mark", actual.getFirstName());
        assertEquals("Ronson", actual.getLastName());
        assertEquals(validDate, actual.getDateOfBirth());
        assertEquals("1234567890", actual.getPhoneNumber());
    }
}

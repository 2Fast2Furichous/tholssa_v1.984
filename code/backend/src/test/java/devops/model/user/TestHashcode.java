package devops.model.user;

import org.junit.jupiter.api.Test;

import devops.model.User;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestHashcode {
    
    @Test
    void testHashCodeExpectMatchingValues(){
        User tom =  new User("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");

        assertEquals("001".hashCode(), tom.hashCode());
    }
}

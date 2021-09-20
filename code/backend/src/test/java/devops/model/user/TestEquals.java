package devops.model.user;

import org.junit.jupiter.api.Test;

import devops.model.implementations.User;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestEquals {
    private User tom =  new User("Mark", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");
    
    @Test
    void testNullUserExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            tom.equals(null);
        });
    }
    
    @Test
    void testUserDoesNotMatchExpectFalse(){
       User mark =  new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "002");
       assertFalse(tom.equals(mark));
    }

    @Test
    void testUserDoesMatchExpectTrue(){
        User mark =  new User("Tom", "Ronson", LocalDate.of(1970, 10, 19), "1234567890", "001");
        assertTrue(tom.equals(mark));
    }
}   

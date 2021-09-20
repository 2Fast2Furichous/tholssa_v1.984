package devops.model.credentials;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.model.implementations.Credentials;

public class TestEquals {
    private Credentials cred1 = new Credentials("p", "u");

    @Test
    void testNullValueExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            cred1.equals(null);
        });
    }

    @Test
    void testPasswordSameUsernameDifferentExpectFalse(){
        assertFalse(cred1.equals(new Credentials("p", "w")));
    }

    @Test
    void testPasswordDifferentUsernameSameExpectFalse(){
        assertFalse(cred1.equals(new Credentials("y", "u")));
    }

    @Test
    void testNoMatchExpectFalse(){
        assertFalse(cred1.equals(new Credentials("q", "w")));
    }

    @Test
    void testEverythingMatchExpectTrye(){
        assertTrue(cred1.equals(new Credentials("p", "u")));
    }
}

package devops.model.credentials;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.model.Credentials;

public class TestHashCode {
    @Test
    void testHashCodeExpectMatchingValues(){
        Credentials cred = new Credentials("p", "q");

        assertEquals("p".hashCode() ^ "q".hashCode(), cred.hashCode());
    }
}

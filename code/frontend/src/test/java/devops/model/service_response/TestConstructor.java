package devops.model.service_response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import devops.model.implementations.ServiceResponse;


public class TestConstructor {

    @Test
    void testOneParameterConstructorDataNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new ServiceResponse(null);
        });
    }

    @Test
    void testOneParameterConstructorValidDataExpectInstantiation() {
        ServiceResponse expected = new ServiceResponse("expected");

        assertEquals("expected", expected.getData());
        assertEquals("No message", expected.getMessage());
    }

    @Test
    void testTwoParameterConstructorDataNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new ServiceResponse("Message", null);
        });
    }

    @Test
    void testTwoParameterConstructorMessageNullExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new ServiceResponse(null, "Data");
        });
    }

    @Test
    void testTwoParameterConstructorMessageBlankExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new ServiceResponse("", "Data");
        });
    }
    
    @Test
    void testTwoParameterConstructorValidDataExpectInstantiation() {
        ServiceResponse expected = new ServiceResponse("Message", "expected");

        assertEquals("expected", expected.getData());
        assertEquals("Message", expected.getMessage());
    }

}

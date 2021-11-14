package devops.model.review;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Review;

public class TestConstructor {
    @Test
    public void testValidDataExpectInstantiation(){
        var review = new Review("Furichous Jones IV", "Burn this heathen!", 2);
        
        assertEquals("Furichous Jones IV", review.getName());
        assertEquals("Burn this heathen!", review.getContent());
        assertEquals(2, review.getScore());
        assertEquals(LocalDateTime.now().getSecond(), review.getEntryDate().getSecond());
    }
}

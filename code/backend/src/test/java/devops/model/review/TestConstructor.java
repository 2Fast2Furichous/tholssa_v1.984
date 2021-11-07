package devops.model.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Review;

public class TestConstructor {
    
    @Test
    public void testNullNameExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Review(null, "Burn this heathen!", 2);
        });
    }

    @Test
    public void testBlankNameExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("", "Burn this heathen!", 2);
        });
    }

    @Test
    public void testNullContentExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("Furichous Jones IV", null, 2);
        });
    }

    @Test
    public void testBlankContentExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("Furichous Jones IV", "", 2);
        });
    }

    @Test
    public void testScoreBelowOneExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("Furichous Jones IV", "Burn this heathen!", -1);
        });
    }

    @Test
    public void testScoreAboveFiveExpectException(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Review("Furichous Jones IV", "Burn this heathen!", 6);
        });
    }

    @Test
    public void testValidDataExpectInstantiation(){
        var review = new Review("Furichous Jones IV", "Burn this heathen!", 2);
        
        assertEquals("Furichous Jones IV", review.getName());
        assertEquals("Burn this heathen!", review.getContent());
        assertEquals(2, review.getScore());
    }
}

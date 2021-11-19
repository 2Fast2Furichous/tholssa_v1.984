package devops.model.review;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Review;

public class TestEquals {
    private Review review = new Review("test", "test2", 2);

    @Test
    public void whenNullReviewExpectException() {
        assertFalse(review.equals(null));
    }

    @Test
    public void whenItemNotOfSameTypeExpectException() {
        assertThrows(IllegalArgumentException.class, () -> {
            review.equals("test");
        });
    }

    @Test
    public void whenNameNotSameEverythingElseSameExpectFalse() {
        assertFalse(review.equals(new Review("name", "test2", 2)));
    }

    @Test
    public void whenContentNotSameEverythingElseSameExpectFalse() {
        assertFalse(review.equals(new Review("test", "test", 2)));
    }

    @Test
    public void whenScoreNotSameEverythingElseSameExpectFalse() {
        assertFalse(review.equals(new Review("test", "test2", 1)));
    }

    @Test
    public void whenScoreAndNameDifferentExpectFalse() {
        assertFalse(review.equals(new Review("test1", "test2", 1)));
    }

    @Test
    public void whenScoreAndContentDifferentExpectFalse() {
        assertFalse(review.equals(new Review("test", "test", 1)));
    }

    @Test
    public void whenContentAndNameDifferentExpectFalse() {
        assertFalse(review.equals(new Review("test1", "test", 2)));
    }

    @Test
    public void whenAllSameExpectTrue() {
        assertTrue(review.equals(new Review("test", "test2", 2)));
    }
}

package devops.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.Review;

public class TestAddReview {
    private LocalDate validDate = LocalDate.of(1970, 10, 17);
    
    @Test
    public void testNullReviewExpectException(){
        Person testPerson = new Person(1.0,1.0,"nickname", "firstName", "lastName", "address", "1234567890", validDate, validDate, "occupation", "description");
        assertThrows(IllegalArgumentException.class, ()-> {
            testPerson.addReview(null);
        } );
    }

    @Test
    public void testAddReviewToEmptyCollectionExpectSizeIncrease(){
        Person testPerson = new Person(1.0,1.0,"nickname", "firstName", "lastName", "address", "1234567890", validDate, validDate, "occupation", "description");
        var review = new Review("Furichous Jones IV", "Burn this heathen!", 2);

        testPerson.addReview(review);
        assertEquals(1, testPerson.getReviews().size());
    }

    @Test
    public void testAddReviewToCollectionWithSingleItemExpectSizeIncrease(){
        Person testPerson = new Person(1.0,1.0,"nickname", "firstName", "lastName", "address", "1234567890", validDate, validDate, "occupation", "description");
        var review = new Review("Furichous Jones IV", "Burn this heathen!", 2);

        testPerson.addReview(review);
        testPerson.addReview(review);

        assertEquals(2, testPerson.getReviews().size());
    }

    @Test
    public void testAddReviewToCollectionWithManyItemsExpectSizeIncrease(){
        Person testPerson = new Person(1.0,1.0,"nickname", "firstName", "lastName", "address", "1234567890", validDate, validDate, "occupation", "description");
        var review = new Review("Furichous Jones IV", "Burn this heathen!", 2);

        testPerson.addReview(review);
        testPerson.addReview(review);
        testPerson.addReview(review);

        testPerson.addReview(review);

        assertEquals(4, testPerson.getReviews().size());
    }
}

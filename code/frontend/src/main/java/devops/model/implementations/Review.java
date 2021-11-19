package devops.model.implementations;

import java.time.LocalDateTime;

/**
 * Review of a Person
 * 
 * @version Fall 2021
 * @author Alexander Ayers
 */
public class Review {

    public static final int MAXIMUM_SCORE = 5;
    public static final int MINIMUM_SCORE = 1;
    private String name;
    private String content;
    private int score;
    private LocalDateTime entryDate;

    /**
     * Three-paramater constructor.
     * 
     * @precondition name != null && !name.isBlank() && content != null &&
     *               !content.isBlank() && score >= 1 && score <= 5
     * @postcondition getName() == name && getContent() == content && getScore() ==
     *                score
     * @param name    the name.
     * @param content the content.
     * @param score   the score.
     */
    public Review(String name, String content, int score) {
        this.name = name;
        this.content = content;
        this.score = score;
        this.entryDate = LocalDateTime.now();
    }

    /**
     * Gets the name.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the content.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Gets the score.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the entry date.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the entry date
     */
    public LocalDateTime getEntryDate() {
        return this.entryDate;
    }

    @Override
    public boolean equals(Object item) {
        if (item == null) {
            return false;
        }
        if (!(item instanceof Review)) {
            throw new IllegalArgumentException("Comparison must be applied to object of type Review.");
        }
        var review = (Review) item;
        return this.name.equals(review.getName()) && this.content.equals(review.getContent())
                && this.score == review.getScore();
    }
}

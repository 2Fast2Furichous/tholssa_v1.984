package devops.model.implementations;

/**
 * Review of a Person
 * 
 * @version Fall 2021
 * @author Alexander Ayers
 */
public class Review {

    private static final int MAXIMUM_SCORE = 5;
    public static final int MINIMUM_SCORE = 1;
    private String name;
    private String content;
    private int score;

    /**
     * Three-paramater constructor.
     * 
     * @precondition name != null && !name.isBlank() && content != null && !content.isBlank() && score >= 1 && score <= 5
     * @postcondition getName() == name && getContent() == content && getScore() == score
     * @param name the name.
     * @param content the content.
     * @param score the score.
     */
    public Review(String name, String content, int score) {
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if (name.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if(content == null){
            throw new IllegalArgumentException("Content cannot be null.");
        }
        if(content.isBlank()){
            throw new IllegalArgumentException("Content cannot be blank.");
        }
        if (score < MINIMUM_SCORE || score > MAXIMUM_SCORE){
            throw new IllegalArgumentException("Score must be between 1 and 5.");
        }
        this.name = name;
        this.content = content;
        this.score = score;
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

    @Override
    public boolean equals(Object item){
        if (item == null){
            throw new IllegalArgumentException("The review cannot be null.");
        }
        if (!(item instanceof Review)){
            throw new IllegalArgumentException("Comparison must be applied to object of type Review.");
        }
        var review = (Review) item;
        return this.name.equals(review.getName()) && this.content.equals(review.getContent()) && this.score == review.getScore();
    }
}

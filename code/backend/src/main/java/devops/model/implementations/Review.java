package devops.model.implementations;

/**
 * Review of a Person
 * 
 * @version Fall 2021
 * @author Alexander Ayers
 */
public class Review {
    
    private String name;
    private String content;
    private int score;

    /**
     * Three-paramater constructor.
     * 
     * @precondition
     * @postcondition
     * @param name
     * @param content
     * @param score
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
        if (score < 1 || score > 5){
            throw new IllegalArgumentException("Score must be between 1 and 5.");
        }
        this.name = name;
        this.content = content;
        this.score = score;
    }
    public String getName() {
        return this.name;
    }
    public String getContent() {
        return this.content;
    }
    public int getScore() {
        return this.score;
    }
}

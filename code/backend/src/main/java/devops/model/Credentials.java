package devops.model;
/**
 * Holds a set of credentials for an account in the system.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class Credentials {
    private String password;
    private String username;

    /**
     * Two-parameter constructor.
     * 
     * @precondition !password.isBlank() AND password != null AND& !username.isBlank() AND username != null
     * @postcondition getPassword() == password && getUsername() == username
     * @param password the password for this account
     * @param userName the username for this account
     */
    public Credentials(String password, String username){
        if (password.isBlank()){
            throw new IllegalArgumentException("Password cannot be blank.");
        }

        if(password.equals(null)){
            throw new IllegalArgumentException("Password cannot be null");
        }
        if(username.isBlank()){
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if(username.equals(null)){
            throw new IllegalArgumentException("Username cannot be null");
        }
        this.password = password;
        this.username = username;
    }

    /**
     * Gets the username.
     * 
     * @precondition none
     * @postcondition none
     * @return the username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password.
     * 
     * @precondition none
     * @postcondition none
     * @return the password.
     */
    public String getPassword(){
        return this.password;
    }
}

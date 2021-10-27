package devops.model.implementations;

import devops.resources.ErrorMessages;

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
     * Constructor for Cradentials for the password and username
     * 
     * @precondition !password.isBlank() AND password != null AND& !username.isBlank() AND username != null
     * @postcondition getPassword() == password && getUsername() == username
     * @param password the password for this account
     * @param userName the username for this account
     */
    public Credentials(String password, String username){
        if(password == null) {
            throw new IllegalArgumentException(ErrorMessages.PASSWORD_CANNOT_BE_NULL);
        }

        if (password.isBlank()){
            throw new IllegalArgumentException(ErrorMessages.PASSWORD_CANNOT_BE_BLANK);
        }
        
        if(username == null){
            throw new IllegalArgumentException(ErrorMessages.USERNAME_CANNOT_BE_NULL);
        }

        if(username.isBlank()){
            throw new IllegalArgumentException(ErrorMessages.USERNAME_CANNOT_BE_BLANK);
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

    @Override
    public int hashCode(){
        return this.username.hashCode() ^ this.password.hashCode();
    }

    @Override
    public boolean equals(Object credentials){
        if (credentials == null){
            throw new IllegalArgumentException();
        }

        return ((Credentials) credentials).getPassword().equals(this.password) && ((Credentials) credentials).getUsername().equals(this.username);
    }
}

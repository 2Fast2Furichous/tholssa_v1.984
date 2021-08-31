package devops.services;

import devops.data.implementations.HashMapUserStorage;
import devops.data.interfaces.UserStorage;
import devops.model.User;
import java.util.Date;

/**
 * Services for creating a user and accessing a user account.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class UserService {
    private UserStorage users;

    /**
     * Zero-paramerer constructor.
     * 
     * @precondition none
     * @postcondition getUsers() != null
     */
    public UserService(){
        this.users = new HashMapUserStorage();
    }

    /**
     * Gets the collection of users.
     * 
     * @precondition none
     * @postcondition none
     * @return the collection of users
     */
    public UserStorage getUsers() {
        return this.users;
    }

    /**
     * Creates an account from the specified user.
     * 
     * @precondition none
     * @postcondition getUsers().size() == @prev + 1
     * 
     * @param newUser the new user to the system
     * @return the user that was added.
     */
    public User createAccount(User newUser){
        if (newUser.equals(null)){
            throw new IllegalArgumentException("The user cannot be null");
        }
        this.users.add(newUser);
        return newUser;
    }

    /**
     * Creates a new user from specified detals and then adds them to the collection.
     * 
     * @precondition none
     * @postcondition getUsers().size() == @prev + 1
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param phoneNumber
     * @param uniqueId
     * @return the user that was added.
     */
    public User createAccount(String firstName, String lastName, Date dateOfBirth, String phoneNumber, String uniqueId){
        User newUser = new User(firstName, lastName, dateOfBirth, phoneNumber, uniqueId);
        return this.createAccount(newUser);
    }

    /**
     * Logs the user into the service through the user's unique ID to the system.
     * 
     * @precondition none
     * @postcondition none
     * @param uniqueId the id that pertains to be desired user information.
     * @return the desired user that connects to the unique id.
     */
    public User login(String uniqueId){
        return this.users.get(uniqueId);
    }
}

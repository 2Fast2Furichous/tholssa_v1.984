package devops.data;

import java.util.HashMap;

import devops.model.Credentials;
import devops.model.User;

/**
 * Stores accounts into a database with the access being the credentials for the user.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class AccountStorage {
    private HashMap<Credentials, User> accounts;

    /**
     * Zero-parameter constructor
     * 
     * @precondition none
     * @postcondition getAccounts() != null
     */
    public AccountStorage(){
        this.accounts = new HashMap<Credentials, User>();
    }

    /**
     * Gets the accounts stored in the system.
     * 
     * @precondition none
     * @postcondition none
     * @return the accounts stored in the system.
     */
    public HashMap<Credentials, User> getAccounts(){
        return this.accounts;
    }

   /**
    *  Adds the specified user to the storage. If the user is already in the system, the user is not added.
    *  
    * @precondition none
    * @postcondition !getAccounts().containsValue(newUser) -> getAccounts().size() == @prev + 1
    * @param newUser the user to be added
    * @return if the user was successfully added
    */
    public boolean add(User newUser){
        if (this.accounts.putIfAbsent(newUser.getCredentials(), newUser).equals(null)) {
            return false;
        } else {
            return true;
        }
    }
}

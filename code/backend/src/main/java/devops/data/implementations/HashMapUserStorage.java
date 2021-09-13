package devops.data.implementations;

import java.util.HashMap;

import devops.data.interfaces.UserStorage;
import devops.model.User;

/**
 * Stores accounts into a database with the access being the credentials for the user.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class HashMapUserStorage implements UserStorage{
    private HashMap<String, User> accounts;

    /**
     * Zero-parameter constructor
     * 
     * @precondition none
     * @postcondition getAccounts() != null
     */
    public HashMapUserStorage(){
        this.accounts = new HashMap<String, User>();
    }

    /**
     * Gets the accounts stored in the system.
     * 
     * @precondition none
     * @postcondition none
     * @return the accounts stored in the system.
     */
    public HashMap<String, User> getAccounts(){
        return this.accounts;
    }

    @Override
    public boolean add(User newUser){
        if (this.accounts.containsKey(newUser.getUniqueId())) {
            return false;
        } else {

            this.accounts.put(newUser.getUniqueId(), newUser);
            return true;
        }
    }

    @Override
    public User get(String key) {
        return this.accounts.get(key);
    }
}

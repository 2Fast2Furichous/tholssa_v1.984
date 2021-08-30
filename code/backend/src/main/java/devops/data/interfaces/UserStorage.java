package devops.data.interfaces;

import devops.model.*;

public interface UserStorage {
    /**
    *  Adds the specified user to the storage. If the user is already in the system, the user is not added.
    *  
    * @precondition none
    * @postcondition !getAccounts().containsValue(newUser) -> getAccounts().size() == @prev + 1
    * @param newUser the user to be added
    * @return if the user was successfully added
    */
    public boolean add(User associateduser);

    /**
     * Gets the user associated with the key provided.
     * 
     * @precondition none
     * @postcondition none
     * @param key the specified key
     * @return the user from the key provided.
     */
    public User get(Object key);

    
}
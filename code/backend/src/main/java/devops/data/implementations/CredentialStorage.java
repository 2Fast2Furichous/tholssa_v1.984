package devops.data.implementations;

import java.util.HashMap;

import devops.model.Credentials;

/**
 * Stores credentials and unique IDs for system access.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class CredentialStorage {
    private HashMap<Credentials, String> accessKeys;

    /**
     * Zero-parameter constructor
     * 
     * @precondition none
     * @postcondition getAccounts() != null
     */
    public CredentialStorage(){
        this.accessKeys = new HashMap<Credentials, String>();
    }

    /**
     * Gets the access keys stored in the system.
     * 
     * @precondition none
     * @postcondition none
     * @return the access keys stored in the system. stored in the system.
     */
    public HashMap<Credentials, String> getAccessKeys(){
        return this.accessKeys;
    }

    /**
     * Adds a specified unique ID to the access keys and binds it to the specified credentials.
     * 
     * @precondition none
     * @postcondition none
     * @param key
     * @param uniqueId
     * @return if the access key was successfully added or not.
     */
    public boolean add(Credentials key, String uniqueId){
        if(this.accessKeys.containsKey(key) || this.accessKeys.containsValue(uniqueId)){
            return false;
        }

        this.accessKeys.put(key, uniqueId);
        return true;
        }

/**
  * Gets the unique ID associated with the key provided.
  * 
  * @precondition none
  * @postcondition none
  * @param key the specified key
  * @return the unique ID from the key provided. Null if the value is not found.
  */
public String get(Credentials key) {
    return this.accessKeys.get(key);
}
}


package devops.data.implementations;

import java.util.HashMap;

import devops.model.Credentials;

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
    public HashMap<Credentials, String> getAccounts(){
        return this.accessKeys;
    }

    /**
     * 
     * @param key
     * @param uniqueId
     * @return
     */
    public boolean add(Credentials key, String uniqueId){
        if (this.accessKeys.putIfAbsent(key, uniqueId) == null) {
            return false;
        } else {
            return true;
        }
    }

public String get(Credentials key) {
    return this.accessKeys.get(key);
}
}


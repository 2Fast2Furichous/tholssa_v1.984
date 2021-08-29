package devops.network.implementations;

import devops.network.interfaces.Key;

/**
 * The server generated key used for all service requests, to uniquely identify
 * the logged in user
 * 
 * NOTE: In future may be used for decryption/encryption via a public/private
 * key structure.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class ClientKey implements Key {

	private String uniqueID;
	
	/**
	 * Creates a new key given the uniqueID.
	 * 
	 * @preconditions uniqueID != null AND !uniqueID.isBlank()
	 * 
	 * @postconditions getUniqueID() == uniqueID
	 * 
	 * 
	 * @param uniqueID Generated unique identifier
	 */
	public ClientKey(String uniqueID) {
		if (uniqueID == null) {
			throw new IllegalArgumentException("Unique ID cannot be null");
		}
		if (uniqueID.isBlank()) {
			throw new IllegalArgumentException("Unique ID cannot be blank");
		}
		this.uniqueID = uniqueID;
	}

	@Override
	public String getUniqueID() {
		return this.uniqueID;
	}
}

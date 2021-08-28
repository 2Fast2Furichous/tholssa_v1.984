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

	private String guid;
	
	/**
	 * Creates a new key given the guid.
	 * 
	 * @param guid Generated unique identifier
	 */
	public ClientKey(String guid) {
		if (guid == null) {
			throw new IllegalArgumentException("guid cannot be null");
		}
		if (guid.isBlank()) {
			throw new IllegalArgumentException("guid cannot be blank");
		}
		this.guid = guid;
	}

	@Override
	public String getGUID() {
		return this.guid;
	}

}

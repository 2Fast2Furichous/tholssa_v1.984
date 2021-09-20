package devops.model.implementations;

/**
 * The read-only data class for the login credential for a account.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class Credential {
	private String password;
	private String username;

	/**
	 * Instantiates a new Credential given a username and password
	 * 
	 * @preconditions username != null AND !username.isBlank() AND password != null
	 *                AND !password.isBlank()
	 * 
	 * @postconditions getFirstName() == firstName AND getPassword() == password
	 * 
	 * 
	 * @param username the username of the credential
	 * @param password the password of the credential
	 */
	public Credential(String password, String username) {
		if (username == null) {
			throw new IllegalArgumentException("User name cannot be null.");
		}
		if (username.isBlank()) {
			throw new IllegalArgumentException("User name cannot be blank.");
		}
		if (password == null) {
			throw new IllegalArgumentException("Password cannot be null.");
		}
		if (password.isBlank()) {
			throw new IllegalArgumentException("Password cannot be blank.");
		}

		this.password = password;
		this.username = username;
	}

	/**
	 * Returns the credential's password
	 * 
	 * @return the credential's password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns the credential's username
	 * 
	 * @return the credential's username
	 */
	public String getUsername() {
		return this.username;
	}

}

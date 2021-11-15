package devops.network.interfaces;

import devops.model.implementations.Credential;
import devops.model.implementations.ServiceResponse;
import devops.model.interfaces.Account;

/**
 * The interface for communicating with the User Service on the server. This
 * provides a library indepdent interface in case we change libraries.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface UserService {

	/**
	 * 
	 * Sends a request to create a new account, and returns the newly created
	 * account.
	 * 
	 * @param newAccount     The requested account to be created
	 * @param newCredentials the credentials associated with the new account.
	 * @return The create process's response, containing the UserAccount, or otherwise a error.
	 */
	public ServiceResponse createAccount(Account newAccount, Credential newCredentials);

	/**
	 * 
	 * Sends a request for the account associated with the entered credentials.
	 * 
	 * @param loginCredentials The login credentials
	 * @return The login process's response, containing the UserAccount or otherwise a error.
	 */
	public ServiceResponse login(Credential loginCredentials);

	public ServiceResponse updateLastPosition(double lastX, double lastY, double lastScale);
}

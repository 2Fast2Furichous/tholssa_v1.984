package devops.network.implementations;

import devops.model.implementations.Credential;
import devops.model.implementations.ServiceResponse;
import devops.model.interfaces.Account;
import devops.network.interfaces.UserService;

/**
 * The UserService implementation using the JeroMQ library.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class JeroUserService implements UserService {

	@Override
	public ServiceResponse createAccount(Account newAccount, Credential newCredentials) {
		// TODO Add API request for createAccount
		return new ServiceResponse(null);
	}

	@Override
	public ServiceResponse login(Credential loginCredentials) {
		// TODO Add API request for login
		return new ServiceResponse(null);
	}

}

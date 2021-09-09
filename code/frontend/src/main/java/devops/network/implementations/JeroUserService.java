package devops.network.implementations;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import devops.model.implementations.Credential;
import devops.model.implementations.ServiceResponse;
import devops.model.interfaces.Account;
import devops.network.interfaces.UserService;

/**
 * The UserService implementation using the JeroMQ library.
 *
 * @author Furichous Jones IV and Alexander Ayers
 * @version Fall 2021
 */
public class JeroUserService implements UserService {
	private Gson gson;

	/**
	 * Default constructor.
	 * 
	 * @precondition none
	 * @postcondition Default values have been initialized.
	 */
	public JeroUserService(){
		this.gson = new Gson();
	}

	@Override
	public ServiceResponse createAccount(Account newAccount, Credential newCredentials) {
		if(newAccount == null){
			throw new IllegalArgumentException("Account cannot be null");
		}
		if(newCredentials == null){
			throw new IllegalArgumentException("Credentials cannot be null");
		}
		JsonObject account = new JsonObject();
		JsonElement accountElement = this.gson.toJsonTree(newAccount, Account.class);
		JsonElement credentialsElement = this.gson.toJsonTree(newCredentials, Credential.class);

		this.addCreateAccountProperties(account, accountElement, credentialsElement);

		String accountJson = this.gson.toJson(account);
		
		// TODO Add Network Request for Create Account

		return new ServiceResponse(null);
	}

	private void addCreateAccountProperties(JsonObject account, JsonElement accountElement, JsonElement credentialsElement) {
		account.addProperty("type", "Create Account");
		account.add("user", accountElement);
		account.add("credentials", credentialsElement);
	}

	@Override
	public ServiceResponse login(Credential loginCredentials) {
		if(loginCredentials == null){
			throw new IllegalArgumentException("Login Credentials cannot be null");
		}

		JsonObject credentials = new JsonObject();
		JsonElement credentialsElement = this.gson.toJsonTree(credentials, Credential.class);

		credentials.addProperty("type", "Login");
		credentials.add("credentials", credentialsElement);

		String loginJson = this.gson.toJson(credentials);

		// TODO Add API request for login
		return new ServiceResponse(null);
	}

}

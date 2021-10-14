package devops.network.implementations;

import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import devops.model.implementations.Credential;
import devops.model.implementations.ServiceResponse;
import devops.model.implementations.UserAccount;
import devops.model.interfaces.Account;
import devops.network.interfaces.UserService;
import devops.network.utils.ServerCommunicator;

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
		JsonElement credentialsElement = this.gson.toJsonTree(newCredentials, Credential.class);

		this.addCreateAccountProperties(account, newAccount, credentialsElement);

		String accountJson = this.gson.toJson(account);
		String responseJson = ServerCommunicator.sendRequest(accountJson);
		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if(response.get("type").toString().equals("error")){
			return ServerCommunicator.handleError(this.gson, response);
		} 

		return new ServiceResponse(newAccount);
	}

	private void addCreateAccountProperties(JsonObject account, Account accountElement, JsonElement credentialsElement) {
		account.addProperty("type", "Create Account");
		account.addProperty("userFirstName", accountElement.getFirstName());
		account.addProperty("userLastName", accountElement.getLastName());
		account.addProperty("userDateOfBirth", accountElement.getDateOfBirth().toString());
		account.addProperty("userPhoneNumber", accountElement.getPhoneNumber());
		account.add("credentials", credentialsElement);
	}

	@Override
	public ServiceResponse login(Credential loginCredentials) {
		if(loginCredentials == null){
			throw new IllegalArgumentException("Login Credentials cannot be null");
		}
		
		JsonObject credentialsJson = new JsonObject();
		JsonElement credentialsElement = this.gson.toJsonTree(loginCredentials, Credential.class);

		credentialsJson.addProperty("type", "Login");
		credentialsJson.add("credentials", credentialsElement);

		String credentialsRequest = this.gson.toJson(credentialsJson);

		String responseJson = ServerCommunicator.sendRequest(credentialsRequest);
		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		
		if(response.get("type").toString().equals("error")){
			return ServerCommunicator.handleError(this.gson, response);
		}
	
		if(this.gson.fromJson(response.get("userDateOfBirth"), String.class) == null){
			return ServerCommunicator.handleError(this.gson, response);
		}
		
	
		String firstName = this.gson.fromJson(response.get("userFirstName"), String.class);
		String lastName = this.gson.fromJson(response.get("userLastName"), String.class);
		System.out.println(this.gson.fromJson(response.get("userDateOfBirth"), String.class));
		LocalDate dateOfBirth = LocalDate.parse(this.gson.fromJson(response.get("userDateOfBirth"), String.class));
		String phoneNumber = this.gson.fromJson(response.get("userPhoneNumber"), String.class);
		return new ServiceResponse(new UserAccount(firstName, lastName, dateOfBirth, phoneNumber));
	}

}

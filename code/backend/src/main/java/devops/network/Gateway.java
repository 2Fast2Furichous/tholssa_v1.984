package devops.network;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import devops.data.implementations.CredentialStorage;
import devops.model.Credentials;
import devops.model.User;
import devops.services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Gateway for the Server to the Rest of the Back-End
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class Gateway extends Thread {
	private Gson gson;
	private UserService userService;
	private CredentialStorage credStorage;

	/**
	 * Default constructor.
	 * 
	 * @precondition none
	 * @postcondition All default values have been initialized.
	 */
	public Gateway() {
		this.gson = new Gson();
		this.userService = new UserService();
		this.credStorage = new CredentialStorage();
	}

	/**
	 * Gets the User Service for the Gateway.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the user service.
	 */
	public UserService getUserService() {
		return userService;
	}

	@Override
	public void run() {
		Context context = ZMQ.context(10);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5555");

        while (!Thread.currentThread().isInterrupted()) {
			try {
				String request = socket.recvStr();
				JsonObject extractedJson = this.gson.fromJson(request, JsonObject.class);

				if (this.gson.fromJson(extractedJson.get("type"), String.class).equals("Create Account")){
					this.handleCreateAccount(socket, extractedJson);
				} else if(this.gson.fromJson(extractedJson.get("type"), String.class).equals("Login")){
					this.handleLogin(socket, extractedJson);
				}
			
			} catch(Exception e){
				JsonObject error = new JsonObject();
				error.addProperty("type", "error");
				error.addProperty("content", e.getMessage());
				String errorJson = this.gson.toJson(error);
				socket.send(errorJson.getBytes(ZMQ.CHARSET));
			} 
        }

        socket.close();
        context.term();
		
	}

	private void handleLogin(Socket socket, JsonObject extractedJson) {
		Credentials loginInfo = this.gson.fromJson(extractedJson.get("credentials"), Credentials.class);
		String uniqueId = this.credStorage.get(loginInfo);
		User account = this.userService.login(uniqueId);

		JsonObject response = new JsonObject();
		if (account != null){
			response.addProperty("type", "success");
			response.addProperty("userFirstName", account.getFirstName());
			response.addProperty("userLastName", account.getLastName());
			response.addProperty("userDateOfBirth", account.getDateOfBirth().toString());
			response.addProperty("userPhoneNumber", account.getPhoneNumber());
		} else {
			response.addProperty("type", "error");
			response.addProperty("content", "Unsuccessful login. Please try again.");
		}
		String responseJson = this.gson.toJson(response);
		socket.send(responseJson.getBytes(ZMQ.CHARSET));
	}

	private void handleCreateAccount(Socket socket, JsonObject extractedJson) {
		Credentials newCredentials = this.gson.fromJson(extractedJson.get("credentials"), Credentials.class);
		String uniqueId = UUID.randomUUID().toString();

		credStorage.add(newCredentials, uniqueId);

		String firstName = this.gson.fromJson(extractedJson.get("userFirstName"), String.class);
		String lastName = this.gson.fromJson(extractedJson.get("userLastName"), String.class);
		LocalDate dateOfBirth = LocalDate.parse(this.gson.fromJson(extractedJson.get("userDateOfBirth"), String.class));
		String phoneNumber = this.gson.fromJson(extractedJson.get("userPhoneNumber"), String.class);
		
		JsonObject response = new JsonObject();
		if (userService.createAccount(firstName, lastName, dateOfBirth, phoneNumber, uniqueId) != null){
			response.addProperty("type", "success");
			response.addProperty("content", "user was added successfully");
		} else {
			response.addProperty("type", "error");
			response.addProperty("content", "User was not successfully added.");
		}
		String responseJson = this.gson.toJson(response);
		socket.send(responseJson.getBytes(ZMQ.CHARSET));
	}
}

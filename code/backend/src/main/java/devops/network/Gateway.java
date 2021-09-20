package devops.network;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import devops.data.implementations.CredentialStorage;
import devops.model.implementations.Credentials;
import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.User;
import devops.model.interfaces.GraphEdge;
import devops.services.GraphService;
import devops.services.UserService;

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
	private Socket socket;
	private GraphService graphService;
	/**
	 * Default constructor.
	 * 
	 * @precondition none
	 * @postcondition All default values have been initialized.
	 */
	public Gateway() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();

		JsonSerializer<LocalDate> dateSerializer = new JsonSerializer<LocalDate>() {
			@Override
			public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject jsonMerchant = new JsonObject();

				jsonMerchant.addProperty("date", src.toString());

				return jsonMerchant;
			}
		};

		JsonDeserializer<LocalDate> dateDeserializer = new JsonDeserializer<LocalDate>() {
			@Override
			public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();

				return LocalDate.parse(jsonObject.get("date").getAsString());
			}
		};

		gsonBuilder.registerTypeAdapter(LocalDate.class, dateSerializer);
		gsonBuilder.registerTypeAdapter(LocalDate.class, dateDeserializer);
		
		this.gson = gsonBuilder.create();

		this.userService = new UserService();
		this.credStorage = new CredentialStorage();
		this.graphService = new GraphService();
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
		this.socket = context.socket(ZMQ.REP);
		this.socket.bind("tcp://127.0.0.1:5555");

        while (!Thread.currentThread().isInterrupted()) {
			try {
				String request = this.socket.recvStr();
				JsonObject extractedJson = this.gson.fromJson(request, JsonObject.class);
				JsonObject response = null;
				try {
					String requstType = this.gson.fromJson(extractedJson.get("type"), String.class);
					System.out.println(requstType);
					System.out.println(extractedJson);
					switch (requstType) {
						case "Create Account":
							response = this.handleCreateAccount(extractedJson);
							break;
						case "Login":
							response = this.handleLogin(extractedJson);
							break;
						case "Remove_Edge":
							response = this.handleRemoveEdge(extractedJson);
							break;
						case "Remove_Node":
							response = this.handleRemoveNode(extractedJson);
							break;
						case "Update_Edge":
							response = this.handleUpdateEdge(extractedJson);
							break;
						case "Update_Node":
							response = this.handleUpdateNode(extractedJson);
							break;
						case "Connect_Nodes":
							response = this.handleConnectNodes(extractedJson);
							break;
						case "Create_Node":
							response = this.handleCreateNode(extractedJson);
							break;
						case "Filter_Network":
							response = this.handleFilteredNetwork(extractedJson);
							break;
						default:
							throw new IllegalArgumentException("That request does not exist");
					}
				} catch (Exception e) {
					response = new JsonObject();
					response.addProperty("type", "error");
					response.addProperty("content", e.getMessage());
				}
				
				String responseJson = this.gson.toJson(response);
				this.socket.send(responseJson.getBytes(ZMQ.CHARSET));

				System.out.println(responseJson);
			
			} catch(Exception e){
				JsonObject error = new JsonObject();
				error.addProperty("type", "error");
				error.addProperty("content", e.getMessage());
				String errorJson = this.gson.toJson(error);
				this.socket.send(errorJson.getBytes(ZMQ.CHARSET));
			} 
        }

        this.socket.close();
        context.close();
		
	}

	private JsonObject handleLogin(JsonObject extractedJson) {
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
			throw new IllegalArgumentException("Unsuccessful login. Please try again.");
		}
		return response;
	}

	private JsonObject handleCreateAccount(JsonObject extractedJson) {
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
			throw new IllegalArgumentException("User was not successfully added.");
		}
		return response;
	}

	private JsonObject handleCreateNode(JsonObject extractedJson) {
		Person person = this.gson.fromJson(extractedJson.get("content"), Person.class);
		
		String nodeID = this.graphService.createNode(person);

		PersonNode node = (PersonNode) this.graphService.getNode(nodeID);
		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(node));

		return response;

	}

	private JsonObject handleConnectNodes(JsonObject extractedJson) {
		PersonEdge newEdge = this.gson.fromJson(extractedJson.get("content"), PersonEdge.class);

		String edgeID = this.graphService.connectNodes(newEdge.getSource(), newEdge.getDestination(), newEdge.getRelation(), newEdge.getDateOfConnection(), newEdge.getDateOfConnectionEnd());

		PersonEdge edge = (PersonEdge) this.graphService.getEdge(edgeID);
		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(edge));

		return response;
	}

	private JsonObject handleRemoveNode(JsonObject extractedJson) {
		String guid = this.gson.fromJson(extractedJson.get("content"), String.class);

		PersonNode node = (PersonNode) this.graphService.removeNode(guid);
		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(node));

		return response;
	}

	private JsonObject handleRemoveEdge(JsonObject extractedJson) {
		String guid = this.gson.fromJson(extractedJson.get("content"), String.class);

		PersonEdge edge = (PersonEdge) this.graphService.removeEdge(guid);
		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(edge));

		return response;
	}
	
	private JsonObject handleUpdateNode(JsonObject extractedJson) {
		PersonNode updateNode = this.gson.fromJson(extractedJson.get("content"), PersonNode.class);

		PersonNode node = (PersonNode) this.graphService.updateNode(updateNode.getUniqueID(), updateNode.getValue());
		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(node));

		return response;
	}

	private JsonObject handleUpdateEdge(JsonObject extractedJson) {
		PersonEdge updateEdge = this.gson.fromJson(extractedJson.get("content"), PersonEdge.class);

		PersonEdge edge = (PersonEdge) this.graphService.updateEdge(
				updateEdge.getUniqueID(),
				updateEdge.getRelation(), updateEdge.getDateOfConnection(), updateEdge.getDateOfConnectionEnd());

		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(edge));

		return response;
	}

	private JsonObject handleFilteredNetwork(JsonObject extractedJson) {
		JsonObject content = this.gson.fromJson(extractedJson.get("content"), JsonObject.class);
		String rootNodeGuid =  this.gson.fromJson(content.get("rootNodeGuid"), String.class);

		Type filterListType = new TypeToken<Collection<NodeFilter>>(){}.getType();
		Collection<NodeFilter> filters = this.gson.fromJson(content.get("filters"), filterListType);

		PersonNetwork network = (PersonNetwork) this.graphService.getFilteredNetwork(rootNodeGuid, filters);

		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(network));

		return response;
	}

}

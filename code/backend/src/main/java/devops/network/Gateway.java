package devops.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
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
import devops.model.implementations.Relationship;
import devops.model.implementations.Review;
import devops.model.implementations.User;
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
	private boolean serverIsActive;

	/**
	 * Constructor that create the Gateway for the system
	 * 
	 * @precondition none
	 * @postcondition All default values have been initialized.
	 */
	public Gateway() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();

		JsonSerializer<LocalDate> localDateSerializer = new JsonSerializer<LocalDate>() {
			@Override
			public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject jsonMerchant = new JsonObject();

				jsonMerchant.addProperty("date", src.toString());

				return jsonMerchant;
			}
		};

		JsonDeserializer<LocalDate> localDateDeserializer = new JsonDeserializer<LocalDate>() {
			@Override
			public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();

				return LocalDate.parse(jsonObject.get("date").getAsString());
			}
		};

		gsonBuilder.registerTypeAdapter(LocalDate.class, localDateSerializer);
		gsonBuilder.registerTypeAdapter(LocalDate.class, localDateDeserializer);

		JsonSerializer<LocalDateTime> localDateTimeSerializer = new JsonSerializer<LocalDateTime>() {
			@Override
			public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject jsonMerchant = new JsonObject();

				jsonMerchant.addProperty("datetime", src.toString());

				return jsonMerchant;
			}
		};

		JsonDeserializer<LocalDateTime> localDateTimeDeserializer = new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();

				return LocalDateTime.parse(jsonObject.get("datetime").getAsString());
			}
		};

		gsonBuilder.registerTypeAdapter(LocalDateTime.class, localDateTimeSerializer);
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, localDateTimeDeserializer);

		this.gson = gsonBuilder.create();

		this.userService = new UserService();
		this.credStorage = new CredentialStorage();
		this.graphService = new GraphService();
		this.serverIsActive = true;

		var testCredentials = new Credentials("pass", "user");
		String uniqueId = UUID.randomUUID().toString();

		this.credStorage.add(testCredentials, uniqueId);
		this.userService.createAccount("User", "Testing", LocalDate.now(), "1234567890", uniqueId);

		var node1Guid = this.graphService.createNode(new Person(50, 50, "nickname1", "firstname1", "lastname1",
				"address1", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation1", "description1"));

		this.graphService.addReview(new Review("user1", "Ooga booga", 5), node1Guid);
		this.graphService.addReview(new Review("user2", "Ooga booga boo", 3), node1Guid);

		var node2Guid = this.graphService.createNode(new Person(80, 200, "nickname2", "firstname2", "lastname2",
				"address2", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation2", "description2"));

		var node3Guid = this.graphService.createNode(new Person(150, -150, "nickname3", "firstname3", "lastname3",
				"address3", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation3", "description3"));

		this.graphService.addReview(new Review("user5", "Ooga booga gee", 5), node3Guid);
		this.graphService.addReview(new Review("user3", "Ooga booga boozy", 3), node3Guid);

		var node4Guid = this.graphService.createNode(new Person(-300, 200, "nickname4", "firstname4", "lastname4",
				"address4", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation4", "description4"));

		var node5Guid = this.graphService.createNode(new Person(-180, 280, "nickname5", "firstname5", "lastname5",
				"address5", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation5", "description5"));

		var node6Guid = this.graphService.createNode(new Person(280, -180, "nickname6", "firstname6", "lastname6",
				"address6", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation6", "description6"));

		this.graphService.addReview(new Review("user2", "Ooga booga bee", 1), node6Guid);
		this.graphService.addReview(new Review("user7", "Ooga booga boogie", 4), node6Guid);

		var node7Guid = this.graphService.createNode(new Person(0, 0, "nickname7", "firstname7", "lastname7",
				"address7", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation7", "description7"));

		var node8Guid = this.graphService.createNode(new Person(0, 0, "nickname8", "firstname8", "lastname8",
				"address8", "1234567890", LocalDate.of(1996, 11, 13), null, "occupation8", "description8"));

		this.graphService.addReview(new Review("user1", "Ooga booga bee", 1), node8Guid);

		var edge1Guid = this.graphService.connectNodes(node7Guid, node8Guid, Relationship.Parent, 
				LocalDate.now().minusDays(200), null);
		var edge2Guid = this.graphService.connectNodes(node8Guid, node7Guid, Relationship.Child, 
				LocalDate.now().minusDays(200), null);

		var edge3Guid = this.graphService.connectNodes(node7Guid, node3Guid, Relationship.Parent, 
				LocalDate.now().minusDays(800), null);
		var edge4Guid = this.graphService.connectNodes(node3Guid, node7Guid, Relationship.Child, 
				LocalDate.now().minusDays(800), null);

		var edge5Guid = this.graphService.connectNodes(node8Guid, node5Guid, Relationship.Parent, 
				LocalDate.now().minusDays(1000), null);
		var edge6Guid = this.graphService.connectNodes(node5Guid, node8Guid, Relationship.Child, 
				LocalDate.now().minusDays(1000), null);

		var edge7Guid = this.graphService.connectNodes(node3Guid, node6Guid, Relationship.Business, 
				LocalDate.now().minusDays(5000), null);
		var edge8Guid = this.graphService.connectNodes(node6Guid, node3Guid, Relationship.Business, 
				LocalDate.now().minusDays(5000), null);

		var edge9Guid = this.graphService.connectNodes(node1Guid, node2Guid, Relationship.Friend, 
				LocalDate.now().minusDays(2000), null);
		var edge10Guid = this.graphService.connectNodes(node2Guid, node1Guid, Relationship.Business, 
				LocalDate.now().minusDays(2000), null);

		var edge11Guid = this.graphService.connectNodes(node2Guid, node4Guid, Relationship.Parent, 
				LocalDate.now().minusDays(300), null);
		var edge12Guid = this.graphService.connectNodes(node4Guid, node2Guid, Relationship.Child, 
				LocalDate.now().minusDays(300), null);

		//var edge13Guid = this.graphService.connectNodes(node4Guid, node6Guid, Relationship.Parent, null, null);
		//var edge14Guid = this.graphService.connectNodes(node6Guid, node4Guid, Relationship.Child, null, null);

	}

	/**
	 * Gets the User Service for the Gateway.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the user service.
	 */
	public UserService getUserService() {
		return this.userService;
	}

	@Override
	public void run() {
		Context context = ZMQ.context(10);
		this.socket = context.socket(ZMQ.REP);
		this.socket.bind("tcp://127.0.0.1:5555");
		// Thread shutdownThread = new Thread(() -> {
		// this.handleShutdownServer();
		// });

		// Thread serverThread = new Thread(() -> {
		this.handleServerRequests();
		// });

		// shutdownThread.setDaemon(true);
		// serverThread.setDaemon(true);
		// serverThread.start();
		// shutdownThread.start();
		// try {
		// shutdownThread.join();

		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		this.socket.close();
		context.close();

	}

	private void handleShutdownServer() {
		while (true) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
				System.out.println("Enter shutdown to shut down the server: ");

				if (reader.readLine().equalsIgnoreCase("shutdown")) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleServerRequests() {
		while (true) {
			try {
				String request = this.socket.recvStr();
				JsonObject extractedJson = this.gson.fromJson(request, JsonObject.class);
				JsonObject response = null;
				try {
					String requstType = this.gson.fromJson(extractedJson.get("type"), String.class);
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
						case "Update_Last_Root_Node":
							response = this.handleUpdateLastRootNode(extractedJson);
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

			} catch (Exception e) {
				JsonObject error = new JsonObject();
				error.addProperty("type", "error");
				error.addProperty("content", e.getMessage());
				String errorJson = this.gson.toJson(error);
				this.socket.send(errorJson.getBytes(ZMQ.CHARSET));
			}
		}
	}

	private JsonObject handleLogin(JsonObject extractedJson) {
		Credentials loginInfo = this.gson.fromJson(extractedJson.get("credentials"), Credentials.class);
		String uniqueId = this.credStorage.get(loginInfo);
		User account = this.userService.login(uniqueId);

		JsonObject response = new JsonObject();
		if (account != null) {
			response.addProperty("type", "success");
			response.addProperty("userFirstName", account.getFirstName());
			response.addProperty("userLastName", account.getLastName());
			response.addProperty("userDateOfBirth", account.getDateOfBirth().toString());
			response.addProperty("userPhoneNumber", account.getPhoneNumber());
			response.addProperty("lastX", account.getLastX());
			response.addProperty("lastY", account.getLastY());
			response.addProperty("lastScale", account.getLastScale());
		} else {
			throw new IllegalArgumentException("Unsuccessful login. Please try again.");
		}
		return response;
	}

	private JsonObject handleCreateAccount(JsonObject extractedJson) {
		Credentials newCredentials = this.gson.fromJson(extractedJson.get("credentials"), Credentials.class);
		String uniqueId = UUID.randomUUID().toString();

		this.credStorage.add(newCredentials, uniqueId);

		String firstName = this.gson.fromJson(extractedJson.get("userFirstName"), String.class);
		String lastName = this.gson.fromJson(extractedJson.get("userLastName"), String.class);
		LocalDate dateOfBirth = LocalDate.parse(this.gson.fromJson(extractedJson.get("userDateOfBirth"), String.class));
		String phoneNumber = this.gson.fromJson(extractedJson.get("userPhoneNumber"), String.class);

		JsonObject response = new JsonObject();
		if (this.userService.createAccount(firstName, lastName, dateOfBirth, phoneNumber, uniqueId) != null) {
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

		String edgeID = this.graphService.connectNodes(newEdge.getSource(), newEdge.getDestination(),
				newEdge.getRelation(), newEdge.getDateOfConnection(), newEdge.getDateOfConnectionEnd());

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

		PersonEdge edge = (PersonEdge) this.graphService.updateEdge(updateEdge.getUniqueID(), updateEdge.getRelation(),
				updateEdge.getDateOfConnection(), updateEdge.getDateOfConnectionEnd());

		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(edge));

		return response;
	}

	private JsonObject handleFilteredNetwork(JsonObject extractedJson) {
		JsonObject content = this.gson.fromJson(extractedJson.get("content"), JsonObject.class);
		String rootNodeGuid = this.gson.fromJson(content.get("rootNodeGuid"), String.class);

		Type filterListType = new TypeToken<Collection<NodeFilter>>() {
		}.getType();
		Collection<NodeFilter> filters = this.gson.fromJson(content.get("filters"), filterListType);
		int depth = this.gson.fromJson(content.get("depth"), int.class);

		PersonNetwork network = (PersonNetwork) this.graphService.getFilteredNetwork(rootNodeGuid, filters, depth);

		JsonObject response = new JsonObject();

		response.addProperty("type", "success");
		response.add("content", this.gson.toJsonTree(network));

		return response;
	}

	private JsonObject handleUpdateLastRootNode(JsonObject extractedJson) {
		Credentials loginInfo = this.gson.fromJson(extractedJson.get("credentials"), Credentials.class);
		String uniqueId = this.credStorage.get(loginInfo);
		double lastX = this.gson.fromJson(extractedJson.get("lastX"), Double.class);
		double lastY = this.gson.fromJson(extractedJson.get("lastY"), Double.class);
		double lastScale = this.gson.fromJson(extractedJson.get("lastScale"), Double.class);

		this.userService.updateLastPosition(uniqueId, lastX, lastY, lastScale);

		JsonObject response = new JsonObject();

		response.addProperty("type", "success");

		return response;
	}

}

package devops.network.implementations;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import devops.model.implementations.NodeFilter;
import devops.model.implementations.Person;
import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNetwork;
import devops.model.implementations.PersonNode;
import devops.model.implementations.Relationship;
import devops.model.implementations.Review;
import devops.model.implementations.ServiceResponse;
import devops.network.interfaces.GraphService;
import devops.network.utils.ServerCommunicator;

public class JeroGraphService implements GraphService {
	private Gson gson;
	/**
	 * Constructor thata creates a Jero Graph service that serializes
	 * and deserializes 
	 */
	public JeroGraphService(){
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
	}

	@Override
	public ServiceResponse createNode(
			double positionX, 
			double positionY, String nickname, String firstName, String lastName, String address,
			String phoneNumber, LocalDate dateOfBirth, LocalDate dateOfDeath, String occupation, String description) {

		Person person = new Person(positionX, positionY, nickname, firstName, lastName, address, phoneNumber, dateOfBirth, dateOfDeath, occupation, description);
		
		JsonObject personRequest = new JsonObject();

		personRequest.addProperty("type", "Create_Node");
		personRequest.add("content", this.gson.toJsonTree(person));

		String requestJson = this.gson.toJson(personRequest);
		String responseJson = ServerCommunicator.sendRequest(requestJson);

		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if (response.get("type").getAsString().equals("error")) {
			return ServerCommunicator.handleError(this.gson, response);
		}

		PersonNode nodeResponse = this.gson.fromJson(response.get("content"), PersonNode.class);

		return new ServiceResponse(nodeResponse);
	}

	@Override
	public ServiceResponse connectNodes(String sourceGuid, String destinationGuid, Relationship relation,
			LocalDate dateOfConnection, LocalDate dateOfConnectionEnd) {

		JsonObject edgeRequest = new JsonObject();

		PersonEdge edge = new PersonEdge(null, sourceGuid, destinationGuid, relation, dateOfConnection, dateOfConnectionEnd);
		edgeRequest.addProperty("type", "Connect_Nodes");
		edgeRequest.add("content", this.gson.toJsonTree(edge));

		String requestJson = this.gson.toJson(edgeRequest);
		String responseJson = ServerCommunicator.sendRequest(requestJson);

		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if (response.get("type").getAsString().equals("error")) {
			return ServerCommunicator.handleError(this.gson, response);
		}

		PersonEdge edgeResponse = this.gson.fromJson(response.get("content"), PersonEdge.class);
		return new ServiceResponse(edgeResponse);
	}

	@Override
	public ServiceResponse updateNode(
			double positionX, 
			double positionY, String guid, String nickname, String firstName, String lastName, String address,
			String phoneNumber, LocalDate dateOfBirth, LocalDate dateOfDeath, String occupation, String description, List<Review> reviews) {

		Person person = new Person(positionX, positionY, nickname, firstName, lastName, address, phoneNumber, dateOfBirth, dateOfDeath,
				occupation, description, reviews);

		PersonNode updatedNode = new PersonNode(guid, person);
				
		JsonObject personRequest = new JsonObject();
		personRequest.addProperty("type", "Update_Node");
		personRequest.add("content", this.gson.toJsonTree(updatedNode));

		String requestJson = this.gson.toJson(personRequest);
		String responseJson = ServerCommunicator.sendRequest(requestJson);

		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if (response.get("type").getAsString().equals("error")) {
			return ServerCommunicator.handleError(this.gson, response);
		}

		PersonNode nodeResponse = this.gson.fromJson(response.get("content"), PersonNode.class);
		return new ServiceResponse(nodeResponse);
	}

	@Override
	public ServiceResponse updateEdge(String guid, Relationship relation, LocalDate dateOfConnection,
			LocalDate dateOfConnectionEnd) {
		JsonObject edgeRequest = new JsonObject();

		PersonEdge edge = new PersonEdge(guid, null, null, relation, dateOfConnection,
				dateOfConnectionEnd);
		edgeRequest.addProperty("type", "Update_Edge");
		edgeRequest.add("content", this.gson.toJsonTree(edge));

		String requestJson = this.gson.toJson(edgeRequest);
		String responseJson = ServerCommunicator.sendRequest(requestJson);

		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if (response.get("type").getAsString().equals("error")) {
			return ServerCommunicator.handleError(this.gson, response);
		}

		PersonEdge edgeResponse = this.gson.fromJson(response.get("content"), PersonEdge.class);
		return new ServiceResponse(edgeResponse);
	}

	@Override
	public ServiceResponse removeNode(String guid) {
		JsonObject networkRequest = new JsonObject();
		networkRequest.addProperty("type", "Remove_Node");
		networkRequest.addProperty("content", guid);

		String requestJson = this.gson.toJson(networkRequest);
		String responseJson = ServerCommunicator.sendRequest(requestJson);

		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if (response.get("type").getAsString().equals("error")) {
			return ServerCommunicator.handleError(this.gson, response);
		}

		PersonNode nodeResponse = this.gson.fromJson(response.get("content"), PersonNode.class);
		return new ServiceResponse(nodeResponse);
	}

	@Override
	public ServiceResponse removeEdge(String guid) {
		JsonObject networkRequest = new JsonObject();
		networkRequest.addProperty("type", "Remove_Edge");
		networkRequest.addProperty("content", guid);

		String requestJson = this.gson.toJson(networkRequest);
		String responseJson = ServerCommunicator.sendRequest(requestJson);

		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if (response.get("type").getAsString().equals("error")) {
			return ServerCommunicator.handleError(this.gson, response);
		}

		PersonEdge edgeResponse = this.gson.fromJson(response.get("content"), PersonEdge.class);
		return new ServiceResponse(edgeResponse);
	}

	@Override
	public ServiceResponse getFilteredNetwork(String rootNodeGuid, Collection<NodeFilter> filters) {

		JsonObject filterRequest = new JsonObject();
		filterRequest.addProperty("rootNodeGuid", rootNodeGuid);
		filterRequest.add("filters", this.gson.toJsonTree(filters));

		JsonObject networkRequest = new JsonObject();
		networkRequest.addProperty("type", "Filter_Network");
		networkRequest.add("content", filterRequest);

		String requestJson = this.gson.toJson(networkRequest);
		String responseJson = ServerCommunicator.sendRequest(requestJson);

		JsonObject response = this.gson.fromJson(responseJson, JsonObject.class);

		if (response.get("type").getAsString().equals("error")) {
			return ServerCommunicator.handleError(this.gson, response);
		}

		PersonNetwork networkResponse = this.gson.fromJson(response.get("content"), PersonNetwork.class);
		return new ServiceResponse(networkResponse);
	}
}

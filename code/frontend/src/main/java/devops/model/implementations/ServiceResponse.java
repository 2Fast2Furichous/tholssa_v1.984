package devops.model.implementations;

/**
 * The read-only data class for the server service response.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class ServiceResponse {

	/**
	 *
	 */
	private static final String DATA_CANNOT_BE_NULL = "data cannot be null";
	private String message;
	private Object data;

	/**
	 * 
	 * Instantiates a new ServiceResponse given a message and data
	 * 
	 * @preconditions message != null AND !message.isBlank() AND data != null
	 * 
	 * @postconditions getMessage() == message AND getData() == data
	 * 
	 * @param message the service response's message
	 * @param data    the service response's data
	 */
	public ServiceResponse(String message, Object data) {
		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		if (message.isBlank()) {
			throw new IllegalArgumentException("message cannot be blank");
		}
		if (data == null) {
			throw new IllegalArgumentException(DATA_CANNOT_BE_NULL);
		}
		this.message = message;
		this.data = data;
	}

	/**
	 * 
	 * Instantiates a new ServiceResponse given a message and data
	 * 
	 * @preconditions data != null
	 * 
	 * @postconditions getMessage() == "No message" AND getData() == data
	 * 
	 * @param data the service response's data
	 */
	public ServiceResponse(Object data) {
		if (data == null) {
			throw new IllegalArgumentException(DATA_CANNOT_BE_NULL);
		}
		this.message = "No message";
		this.data = data;
	}

	/**
	 * Returns the response's message
	 * 
	 * @return the response's message
	 */
	public String getMessage() {
		return this.message;
	};

	/**
	 * Returns the response's data
	 * 
	 * @return the response's data
	 */
	public Object getData() {
		return this.data;
	};
}
package devops.model.implementations;

import java.util.Date;

import devops.model.interfaces.Account;
import devops.network.implementations.ClientKey;
import javafx.scene.image.Image;

/**
 * The read-only Account class that represents the detailed user account with username and
 * ClientKey included
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class UserAccount implements Account {
	private String firstName;
	private String lastName;
	private String username;
	private Date dateOfBirth;
	private String phoneNumber;
	private Image photo;
	private ClientKey userKey;

	/**
	 * Instantiates a new UserAccount
	 * 
	 * @preconditions firstName != null AND !firstName.isBlank() AND lastName !=
	 *                null AND !lastName.isBlank() AND username != null AND
	 *                !username.isBlank() AND dateOfBirth != null
	 * 
	 * @postconditions getFirstName() == firstName AND getLastName() == lastName AND
	 *                 getUserName() == username AND getDateOfBirth() == dateOfBirth
	 *                 AND getPhoneNumber() == phoneNumber AND getPhoto() == photo
	 *                 AND getUserKey() == userKey
	 * 
	 * @param firstName   First name of user
	 * @param lastName    Last name of user
	 * @param username    Login name of user
	 * @param dateOfBirth Date of birth of user
	 * @param phoneNumber Phone number of user
	 * @param photo       Photo of user
	 * @param userKey     ClientKey of user
	 */
	public UserAccount(String firstName, String lastName, String username, Date dateOfBirth, String phoneNumber,
			Image photo, ClientKey userKey) {
		if (firstName == null) {
			throw new IllegalArgumentException("firstName cannot be null");
		}
		if (firstName.isBlank()) {
			throw new IllegalArgumentException("firstName cannot be blank");
		}

		if (lastName == null) {
			throw new IllegalArgumentException("lastName cannot be null");
		}
		if (lastName.isBlank()) {
			throw new IllegalArgumentException("lastName cannot be blank");
		}

		if (username == null) {
			throw new IllegalArgumentException("username cannot be null");
		}
		if (username.isBlank()) {
			throw new IllegalArgumentException("username cannot be blank");
		}

		if (dateOfBirth == null) {
			throw new IllegalArgumentException("dateOfBirth cannot be null");
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.photo = photo;
		this.userKey = userKey;
	}

	/**
	 * Returns the account's client key
	 * 
	 * @return the account's client key
	 */
	public ClientKey getUserKey() {
		return this.userKey;
	}

	@Override
	public Image getPhoto() {
		return this.photo;
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@Override
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * Returns the account's username
	 * 
	 * @return the account's username
	 */
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}
}

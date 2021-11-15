package devops.model.implementations;

import java.time.LocalDate;

import devops.model.interfaces.Account;

/**
 * The read-only Account class that represents the detailed user account.
 *
 * @author Furichous Jones IV and Alexander Ayers
 * @version Fall 2021
 */
public class UserAccount implements Account {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private double lastX;
	private double lastY;
	private double lastScale;
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
	 * @param dateOfBirth Date of birth of user
	 * @param phoneNumber Phone number of user
	 */
	public UserAccount(String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber) {
		if (firstName == null) {
			throw new IllegalArgumentException("First name cannot be null.");
		}
		if (firstName.isBlank()) {
			throw new IllegalArgumentException("First name cannot be blank.");
		}

		if (lastName == null) {
			throw new IllegalArgumentException("Last name cannot be null.");
		}
		if (lastName.isBlank()) {
			throw new IllegalArgumentException("Last name cannot be blank.");
		}

		if (dateOfBirth == null) {
			throw new IllegalArgumentException("Date of birth cannot be null.");
		}

		if (phoneNumber == null){
			throw new IllegalArgumentException("Phone Number cannot be null.");
		}

		if (phoneNumber.isBlank()){
			throw new IllegalArgumentException("Phone Number cannot be blank.");
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@Override
	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public double getLastX() {
		return this.lastX;
	}

	@Override
	public double getLastY() {
		return this.lastY;
	}

	@Override
	public double getLastScale() {
		return this.lastScale;
	}

	@Override
	public void setLastX(double lastX) {
		this.lastX = lastX;
	}

	@Override
	public void setLastY(double lastY) {
		this.lastY = lastY;
	}

	@Override
	public void setLastScale(double lastScale) {
		this.lastScale = lastScale;
	}
}

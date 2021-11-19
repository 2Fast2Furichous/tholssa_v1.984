package devops.model.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The object representing a person
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public class Person implements Comparable<Person> {
	private String nickname;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private LocalDate dateOfBirth;
	private LocalDate dateOfDeath;
	private String occupation;
	private String description;
	private double positionX;
	private double positionY;
	private List<Review> reviews;

	/**
	 * Creates a new person with the given details
	 * 
	 * @preconditions none
	 * @postconditions getDescription() == description && getOccupation() ==
	 *                 occupation && getDateOfDeath() == dateOfDeath &&
	 *                 getDateOfBirth() == dateOfBirth && getAddress() == address &&
	 *                 getPhoneNumber() == phoneNumber && getLastName() == lastName
	 *                 && getFirstName() == firstName && getNickname() == nickname
	 *                 && getReviews() != null
	 * 
	 * @param nickname
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phoneNumber
	 * @param dateOfBirth
	 * @param dateOfDeath
	 * @param occupation
	 * @param description
	 */
	public Person(double positionX, double positionY, String nickname, String firstName, String lastName,
			String address, String phoneNumber, LocalDate dateOfBirth, LocalDate dateOfDeath, String occupation,
			String description) {
		this.nickname = nickname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.occupation = occupation;
		this.description = description;
		this.positionX = positionX;
		this.positionY = positionY;
		this.reviews = new ArrayList<Review>();
	}

	/**
	 * Creates a new person with the given details
	 * 
	 * @preconditions none
	 * @postconditions getDescription() == description && getOccupation() ==
	 *                 occupation && getDateOfDeath() == dateOfDeath &&
	 *                 getDateOfBirth() == dateOfBirth && getAddress() == address &&
	 *                 getPhoneNumber() == phoneNumber && getLastName() == lastName
	 *                 && getFirstName() == firstName && getNickname() == nickname
	 *                 && getReviews() == reviews
	 * 
	 * @param nickname
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phoneNumber
	 * @param dateOfBirth
	 * @param dateOfDeath
	 * @param occupation
	 * @param description
	 */
	public Person(double positionX, double positionY, String nickname, String firstName, String lastName,
			String address, String phoneNumber, LocalDate dateOfBirth, LocalDate dateOfDeath, String occupation,
			String description, List<Review> reviews) {
		this.nickname = nickname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.occupation = occupation;
		this.description = description;
		this.positionX = positionX;
		this.positionY = positionY;
		this.reviews = reviews;
	}

	/**
	 * Gets the Y position
	 * 
	 * @return the positionY
	 */
	public double getPositionY() {
		return this.positionY;
	}

	/**
	 * Gets the X position
	 * 
	 * @return the positionX
	 */
	public double getPositionX() {
		return this.positionX;
	}

	/**
	 * 
	 * Gets the description of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description of the person
	 * 
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * Gets the occupation of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the occupation of the person
	 * 
	 */
	public String getOccupation() {
		return this.occupation;
	}

	/**
	 * 
	 * Gets the date of death of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the date of death of the person
	 * 
	 */
	public LocalDate getDateOfDeath() {
		return this.dateOfDeath;
	}

	/**
	 * 
	 * Gets the date of birth of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the date of birth of the person
	 * 
	 */
	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * 
	 * Gets the address of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the address of the person
	 * 
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * Gets the phone number of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the phone number of the person
	 * 
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 
	 * Gets the last name of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the last name of the person
	 * 
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * Gets the first name of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the first name of the person
	 * 
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * 
	 * Gets the nickname of the person
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the nickname of the person
	 * 
	 */
	public String getNickname() {
		return this.nickname;
	}

	/**
	 * Gets the collection of reviews.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the collection of reviews
	 */
	public List<Review> getReviews() {
		return this.reviews;
	}

	/**
	 * Adds the specified review to the node.
	 * 
	 * @precondition review != null
	 * @postcondition getReviews().size() == @prev + 1
	 * 
	 * @param review the added review
	 */
	public void addReview(Review review) {
		if (review == null) {
			throw new IllegalArgumentException("The added review cannot be null");
		}

		this.reviews.add(review);
	}

	/**
	 * Gets the full name of the person with their nickname Displays as firstName
	 * lastname | nickname
	 * 
	 * @preconditions none
	 * @postcondition none
	 * 
	 * @return the full name of the person with their nickname
	 */
	public String getFullNameWithNickname() {
		return this.firstName + " " + this.lastName + " | " + this.nickname;
	}

	@Override
	public int compareTo(Person otherPerson) {
		return this.getLastName().compareTo(otherPerson.getLastName());
	}
}

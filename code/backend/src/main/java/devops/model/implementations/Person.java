package devops.model.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The object representing a person
 *
 * @author Furichous Jones IV and Alexander Ayers
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
	private Collection<Review> reviews;

	/**
	 * Creates a new person with the given details
	 * 
	 * @preconditions none
	 * @postconditions 	getDescription() == description &&
	 * 					getOccupation() == occupation &&
* 						getDateOfDeath() == dateOfDeath &&
	 *                 	getDateOfBirth() == dateOfBirth &&
	 * 					getAddress() == address &&
	 *                 	getPhoneNumber() == phoneNumber &&
	 * 					getLastName() == lastName
	 *                 	getFirstName() == firstName &&
	 * 					getNickname() == nickname && 
	 * 					getReviews() != null
	 * 
	 * @param nickname		the specified nickname
	 * @param firstName		the specified first name
	 * @param lastName		the specified last name
	 * @param address		the specified address
	 * @param phoneNumber	the specified phone number
	 * @param dateOfBirth	the specified date of birth
	 * @param dateOfDeath	the specified date of death
	 * @param occupation	the specified occupation
	 * @param description	the specified description
	 */
	public Person(
			double positionX, 
			double positionY, String nickname,String firstName,String lastName,String address,String phoneNumber,LocalDate dateOfBirth,LocalDate dateOfDeath,String occupation, String description)  {
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
	 * @return the positionY
	 */
	public double getPositionY() {
		return this.positionY;
	}

	/**
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
		return description;
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
		return occupation;
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
		return dateOfDeath;
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
		return dateOfBirth;
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
		return address;
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
		return phoneNumber;
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
		return lastName;
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
		return firstName;
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
		return nickname;
	}

	/**
	 * Gets the collection of reviews.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the collection of reviews
	 */
	public Collection<Review> getReviews(){
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
	public void addReview(Review review){
		if (review == null){
			throw new IllegalArgumentException("The added review cannot be null");
		}

		this.reviews.add(review);
	}

	@Override
	public int compareTo(Person o) {
		return this.getLastName().compareTo(o.getLastName());
	}
}

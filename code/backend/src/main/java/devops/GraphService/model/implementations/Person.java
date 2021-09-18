package devops.GraphService.model.implementations;

import java.time.LocalDate;

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
	 * 					getNickname() == nickname
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
	public Person(String nickname,String firstName,String lastName,String address,String phoneNumber,LocalDate dateOfBirth,LocalDate dateOfDeath,String occupation, String description)  {
		this.nickname = nickname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.occupation = occupation;
		this.description = description;
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

	@Override
	public int compareTo(Person o) {
		return this.getLastName().compareTo(o.getLastName());
	}
}

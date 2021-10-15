package devops.model.implementations;

import java.time.LocalDate;

import devops.resources.ErrorMessages;
/**
 * Models a User of the System.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class User {      
    private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
    private String uniqueId;
    

    /**
     * 
     * Constructor that creates a user with a firstName, lastName,
     * dateOfBirth, phoneNumber and a uniqueId
     * 
     * 
     * @precondition firstName != null AND !firstName.isBlank() AND lastName !=
	 *                null AND !lastName.isBlank()
	 *               AND dateOfBirth != null AND uniqueId != null AND
     *               uniqueId.isBlank() AND phoneNumber != null AND !phoneNumber.isBlank()
	 * 
	 * @postcondition getFirstName() == firstName AND getLastName() == lastName AND
	 *                 getUserName() == username AND getDateOfBirth() == dateOfBirth
	 *                 AND getPhoneNumber() == phoneNumber AND getUniqueId() == uniqueId
	 *          
     * @param firstName the first name of the account.
     * @param lastName the last name of the user.
     * @param dateOfBirth the date of birth of the user.
     * @param phoneNumber the phone number of the user.
     * @param uniqueId the uniqueId for the user.
     */
    public User(String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String uniqueId){
    if(uniqueId == null) {
            throw new IllegalArgumentException(ErrorMessages.UNIQUE_ID_CANNOT_BE_NULL);
    }
    if(uniqueId.isBlank()) {
        throw new IllegalArgumentException(ErrorMessages.THE_UNIQUE_ID_CANNOT_BE_BLANK);
    }
    if (phoneNumber == null) {
        throw new IllegalArgumentException(ErrorMessages.PHONE_NUMBER_CANNOT_BE_NULL);
    }
    if (firstName == null) {
			throw new IllegalArgumentException(ErrorMessages.FIRST_NAME_CANNOT_BE_NULL);
     }
    if (firstName.isBlank()) {
        throw new IllegalArgumentException(ErrorMessages.FIRST_NAME_CANNOT_BE_BLANK);
    }
    if (lastName == null) {
        throw new IllegalArgumentException(ErrorMessages.LAST_NAME_CANNOT_BE_NULL);
    }
    if (lastName.isBlank()) {
        throw new IllegalArgumentException(ErrorMessages.LAST_NAME_CANNOT_BE_BLANK);
    }
    if (dateOfBirth == null) {
        throw new IllegalArgumentException(ErrorMessages.DATE_OF_BIRTH_CANNOT_BE_NULL);
    }
    if (phoneNumber.isBlank()) {
        throw new IllegalArgumentException(ErrorMessages.PHONE_NUMBER_CANNOT_BE_BLANK);
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.uniqueId = uniqueId;
    }

    /**
     * Gets the unique id.
     * 
     * @precondition none
     * @postcondition none
     * @return the unique id
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Gets the first name.
     * 
     * @precondition none
     * @postcondition none
     * @return the first name
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Gets the last name.
     * 
     * @precondition none
     * @postcondition none
     * @return the last name
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Gets the date of birth.
     * 
     * @precondition none
     * @postcondition none
     * @return the date of birth
     */
    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }

    /**
     * Gets the phone number.
     * 
     * @precondition none
     * @postcondition none
     * @return the phone number
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    @Override
    public boolean equals(Object user){
        if (user == null){
            throw new IllegalArgumentException(ErrorMessages.THE_USER_CANNOT_BE_NULL);
        }
        return this.getUniqueId().equals(((User) user).getUniqueId());
    }
    
    @Override
    public int hashCode(){
        return this.uniqueId.hashCode();
    }
}

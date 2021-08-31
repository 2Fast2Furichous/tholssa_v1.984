package devops.model;

import java.util.Date;
/**
 * Models a User of the System.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class User {
    private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String phoneNumber;
    private String uniqueId;
    

    /**
     * 
     * 5-parameter constructor.
     * 
     * 
     * @precondition firstName != null AND !firstName.isBlank() AND lastName !=
	 *                null AND !lastName.isBlank()  AND
	 *               !username.isBlank() AND dateOfBirth != null AND uniqueId != null AND
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
    public User(String firstName, String lastName, Date dateOfBirth, String phoneNumber, String uniqueId){
    if(uniqueId.equals(null)) {
            throw new IllegalArgumentException("The unique id cannot be null.");
    }
    if(uniqueId.isBlank()){
        throw new IllegalArgumentException("The unique id cannot be blank.");
    }
    if (firstName.equals(null)) {
			throw new IllegalArgumentException("First Name cannot be null");
     }
    if (firstName.isBlank()) {
        throw new IllegalArgumentException("First Name cannot be blank");
    }
    if (lastName.equals(null)) {
        throw new IllegalArgumentException("Last Name cannot be null");
    }
    if (lastName.isBlank()) {
        throw new IllegalArgumentException("Last Name cannot be blank");
    }
    if (dateOfBirth == null) {
        throw new IllegalArgumentException("Date of Birth cannot be null");
    }
    if (phoneNumber.isBlank()){
        throw new IllegalArgumentException("Phone Number cannot be blank");
    }
    if (phoneNumber.equals(null)) {
        throw new IllegalArgumentException("Phone Number cannot be null");
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
    public Date getDateOfBirth(){
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
    /**
     * Sees if a user is equal to this user.
     * 
     * @precondition none
     * @postcondition none
     * @param user
     * @return whether the users 
     */
    public boolean equals(User user){
        if (user == null){
            throw new IllegalArgumentException("User cannot be null.");
        }
        return this.getUniqueId().equals(user.getUniqueId());
    }
}

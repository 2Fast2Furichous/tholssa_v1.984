package devops.model.interfaces;

import java.time.LocalDate;

/**
 * The base interface for which all Accounts share and are publicly visible on
 * the application, read-only
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface Account {

	/**
	 * Returns the account's phone number
	 * 
	 * @return the account's phone number
	 */
	public String getPhoneNumber();

	/**
	 * Returns the account's date of birth
	 * 
	 * @return the account's date of birth
	 */
	public LocalDate getDateOfBirth();

	/**
	 * Returns the account's last name
	 * 
	 * @return the account's last name
	 */
	public String getLastName();

	/**
	 * Returns the account's first name
	 * 
	 * @return the account's first name
	 */
	public String getFirstName();
}

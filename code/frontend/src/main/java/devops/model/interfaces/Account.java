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
     * Set the account's last X coordinate
     * 
     * @param the account's last X coordinate
     */
	public void setLastX(double lastX);

	/**
     * Set the account's last X coordinate
     * 
     * @param the account's last X coordinate
     */
	public void setLastY(double lastY);

	/**
     * Set the account's last X coordinate
     * 
     * @param the account's last X coordinate
     */
	public void setLastScale(double lastScale);

	/**
	 * Get the account's last X coordinate
	 * 
	 * @return the account's last X coordinate
	 */
	public double getLastX();

	/**
	 * Get the account's last Y coordinate
	 * 
	 * @return the account's last Y coordinate
	 */
	public double getLastY();

	/**
	 * Get the account's last zoom scale
	 * 
	 * @return the account's last zoom scale
	 */
	public double getLastScale();

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

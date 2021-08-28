package devops.network.interfaces;

/**
 * The base key used by server to uniquely identify a user.
 *
 * @author Furichous Jones IV
 * @version Fall 2021
 */
public interface Key {
	/**
	 * Returns the key's guid
	 * 
	 * @return the key's guid
	 */
	public String getGUID();
}

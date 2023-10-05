package application.Exception;

/**
 * Custom exception for found the username exists
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class UsernameExistException extends Exception {
	public UsernameExistException(String msg) {
		super(msg);
	}
}

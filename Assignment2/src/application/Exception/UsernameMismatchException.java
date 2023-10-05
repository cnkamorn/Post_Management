package application.Exception;

/**
 * Custom exception for retype username mismatch Input from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class UsernameMismatchException extends Exception {

	public UsernameMismatchException(String msg) {
		super(msg);
	}
}

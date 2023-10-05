package application.Exception;

/**
 * Custom exception for wrong password Input from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class WrongPasswordException extends Exception {

	public WrongPasswordException(String msg) {
		super(msg);
	}
}

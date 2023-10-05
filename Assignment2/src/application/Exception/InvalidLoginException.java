package application.Exception;

/**
 * Custom exception for invilid login Input from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class InvalidLoginException extends Exception {

	public InvalidLoginException(String msg) {
		super(msg);
	}
}

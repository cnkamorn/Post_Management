package application.Exception;

/**
 * Custom exception for validate Input from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class InputWhiteSpaceException extends Exception {
	public InputWhiteSpaceException(String msg) {
		super(msg);
	}
}

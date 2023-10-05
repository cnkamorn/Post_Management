package application.Exception;

/**
 * Custom exception for no header found in the file from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class InputHeaderException extends Exception {
	public InputHeaderException(String msg) {
		super(msg);
	}
}

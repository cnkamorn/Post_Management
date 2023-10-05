package application.Exception;

/**
 * Custom exception for uploaded file from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class InvalidFileTypeException extends Exception {

	public InvalidFileTypeException(String msg) {
		super(msg);
	}
}

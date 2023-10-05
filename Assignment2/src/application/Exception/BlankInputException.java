package application.Exception;

/**
 * Custom exception for Blank Input from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class BlankInputException extends Exception {
	public BlankInputException(String msg) {
		super(msg);
	}
}

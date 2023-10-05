package application.Exception;

/**
 * Custom exception for invalid retype data from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class RetypeException extends Exception {

	public RetypeException(String msg) {
		super(msg);
	}
}

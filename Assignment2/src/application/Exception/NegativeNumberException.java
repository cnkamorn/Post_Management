package application.Exception;

/**
 * Custom exception for negative number Input from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class NegativeNumberException extends Exception {
	public NegativeNumberException(String msg) {
		super(msg);
	}
}

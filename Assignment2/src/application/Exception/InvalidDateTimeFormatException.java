package application.Exception;

/**
 * Custom exception for invalid date time format Input from the user
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class InvalidDateTimeFormatException extends Exception {

	public InvalidDateTimeFormatException(String msg) {
		super(msg);
	}
}

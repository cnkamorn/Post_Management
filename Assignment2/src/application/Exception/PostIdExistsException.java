package application.Exception;

/**
 * Custom exception for found the post ID in the database
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class PostIdExistsException extends Exception {
	public PostIdExistsException(String msg) {
		super(msg);
	}
}

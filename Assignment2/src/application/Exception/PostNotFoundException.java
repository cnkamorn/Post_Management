package application.Exception;

/**
 * Custom exception for no post found
 *
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class PostNotFoundException extends Exception {
	public PostNotFoundException(String msg) {
		super(msg);
	}
}

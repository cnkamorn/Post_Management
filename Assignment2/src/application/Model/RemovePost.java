package application.Model;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;
import application.Model.DAO.PostDAO;
import application.Model.DAO.UserDAO;

/**
 * This class is a remove post class It's an extended class from the main post
 * dash board class.
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class RemovePost {

	private static RemovePost Instance;

	private RemovePost() {
	};

	public static RemovePost getInstance() {
		if (Instance == null) {
			Instance = new RemovePost();
		}
		return Instance;
	}

	/**
	 * Method to check if there is any blank field
	 * 
	 * @param postId
	 * @throws BlankInputException
	 */
	public void checkBlankField(String postId) throws BlankInputException {
		if (postId.isBlank()) {
			throw new BlankInputException("Error:Input from the post ID is blank");
		}
	}

	/**
	 * Method to remove the post from the system
	 * 
	 * @param username
	 * @param postId
	 * @throws PostNotFoundException
	 */
	public void removePost(String username, String postId) throws PostNotFoundException {
		PostDAO postDB = PostDAO.getInstance();
		UserDAO userDB = UserDAO.getInstance();
		String authorId = userDB.queryUserId(username); // search for ID
		if (!postDB.removePost(authorId, postId)) {
			throw new PostNotFoundException("Post is not exist or this user has no access to it");
		}
	}
}

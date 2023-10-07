package application.Model;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;
import application.Model.DAO.PostDatabase;
import application.Model.DAO.UserDatabase;

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

	public void checkBlankField(String postId) throws BlankInputException {
		if (postId.isBlank()) {
			throw new BlankInputException("Error:Input from the post ID is blank");
		}
	}

	public void removePost(String username, String postId) throws PostNotFoundException {
		PostDatabase postDB = PostDatabase.getInstance();
		UserDatabase userDB = UserDatabase.getInstance();
		String authorId = userDB.queryUserId(username); // search for ID
		if (!postDB.removePost(authorId, postId)) {
			throw new PostNotFoundException("Post is not exist or this user has no access to it");
		}
	}
}

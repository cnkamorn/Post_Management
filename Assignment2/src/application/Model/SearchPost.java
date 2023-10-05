package application.Model;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;

/**
 * This class is a search post class It's an extended class from the main post
 * dash board class.
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class SearchPost {

	private static SearchPost Instance;

	private SearchPost() {
	};

	public static SearchPost getInstance() {
		if (Instance == null) {
			Instance = new SearchPost();
		}
		return Instance;
	}

	public void checkBlankField(String postId) throws BlankInputException {
		if (postId.isBlank()) {
			throw new BlankInputException("Error:Input from the search field is blank");
		}
	}

	public Post retrievePost(String username, String postId) throws PostNotFoundException {
		PostDatabase postDB = PostDatabase.getInstance();
		UserDatabase userDB = UserDatabase.getInstance();
		String authorId = userDB.queryUserId(username); // search for ID
		Post post = postDB.retrievePost(authorId, postId);
		return post;
	}
}

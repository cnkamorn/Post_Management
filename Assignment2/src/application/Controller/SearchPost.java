package application.Controller;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;
import application.Model.Post;

/**
 * This class is a search post class It's an extended class from the main post
 * dash board class.
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class SearchPost extends PostDashboardController {

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

	public Post retrievePost(String postId) throws PostNotFoundException {
		PostDatabase postDB = PostDatabase.getInstance();
		UserDatabase userDB = UserDatabase.getInstance();
		String authorId = userDB.queryUserId(currentUserAccount.getUsername()); // search for ID
		Post post = postDB.retrievePost(authorId, postId);
		return post;
	}
}

package application.Model;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;
import application.Model.DAO.PostDAO;
import application.Model.DAO.UserDAO;

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

	/**
	 * Method to check blank field
	 * 
	 * @param postId
	 * @throws BlankInputException
	 */
	public void checkBlankField(String postId) throws BlankInputException {
		if (postId.isBlank()) {
			throw new BlankInputException("Error:Input from the search field is blank");
		}
	}

	/**
	 * Method to retrieve a post from the database
	 * 
	 * @param username
	 * @param postId
	 * @return
	 * @throws PostNotFoundException
	 */
	public Post retrievePost(String username, String postId) throws PostNotFoundException {
		PostDAO postDB = PostDAO.getInstance();
		UserDAO userDB = UserDAO.getInstance();
		String authorId = userDB.queryUserId(username); // search for ID
		Post post = postDB.retrievePost(authorId, postId);
		return post;
	}
}

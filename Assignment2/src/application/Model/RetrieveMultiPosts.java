package application.Model;

import java.util.ArrayList;
import java.util.Collections;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;
import application.Model.DAO.PostDAO;
import application.Model.DAO.UserDAO;

/**
 * This class is a retrieve multi posts class It's an extended class from the
 * main post dash board class.
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class RetrieveMultiPosts {

	private static RetrieveMultiPosts Instance;

	private RetrieveMultiPosts() {
	};

	public static RetrieveMultiPosts getInstance() {
		if (Instance == null) {
			Instance = new RetrieveMultiPosts();
		}
		return Instance;
	}

	/**
	 * Method to check the blank field
	 * 
	 * @param input
	 * @throws BlankInputException
	 */
	public void checkBlankField(String input) throws BlankInputException {
		if (input.isBlank()) {
			throw new BlankInputException("Error:Input from the post number is blank");
		}
	}

	/**
	 * Method to retrieve Post collection from the database
	 * 
	 * @param username
	 * @return ArrayList of Post
	 * @throws PostNotFoundException
	 */
	public ArrayList<Post> retrievePostsCollection(String username) throws PostNotFoundException {
		PostDAO postDB = PostDAO.getInstance();
		UserDAO userDB = UserDAO.getInstance();
		String authorId = userDB.queryUserId(username);
		// get all the posts from the current user
		ArrayList<Post> posts = postDB.retrieveUserPosts(authorId);
		// sort by likes
		posts.sort(new LikesComparator());
		Collections.reverse(posts);
		// return sorted Post
		return posts;
	}
}

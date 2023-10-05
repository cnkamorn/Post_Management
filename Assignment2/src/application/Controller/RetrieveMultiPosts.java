package application.Controller;

import java.util.ArrayList;
import java.util.Collections;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;
import application.Model.Post;

/**
 * This class is a retrieve multi posts class It's an extended class from the
 * main post dash board class.
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class RetrieveMultiPosts extends PostDashboardController {

	private static RetrieveMultiPosts Instance;

	private RetrieveMultiPosts() {
	};

	public static RetrieveMultiPosts getInstance() {
		if (Instance == null) {
			Instance = new RetrieveMultiPosts();
		}
		return Instance;
	}

	public void checkBlankField(String input) throws BlankInputException {
		if (input.isBlank()) {
			throw new BlankInputException("Error:Input from the post number is blank");
		}
	}

	public ArrayList<Post> retrievePostsCollection() throws PostNotFoundException {
		PostDatabase postDB = PostDatabase.getInstance();
		UserDatabase userDB = UserDatabase.getInstance();
		String authorId = userDB.queryUserId(currentUserAccount.getUsername());
		// get all the posts from the current user
		ArrayList<Post> posts = postDB.retrieveUserPosts(authorId);
		// sort by likes
		posts.sort(new LikesComparator());
		Collections.reverse(posts);
		// return sorted Post
		return posts;
	}
}

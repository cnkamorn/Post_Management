package application.Controller;

import application.Exception.BlankInputException;
import application.Exception.PostNotFoundException;

public class RemovePost extends PostDashboardController {

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

	public void removePost(String postId) throws PostNotFoundException {
		PostDatabase postDB = PostDatabase.getInstance();
		UserDatabase userDB = UserDatabase.getInstance();
		String authorId = userDB.queryUserId(currentUserAccount.getUsername()); // search for ID
		if (!postDB.removePost(authorId, postId)) {
			throw new PostNotFoundException("Post is not exist or this user has no access to it");
		}
	}
}

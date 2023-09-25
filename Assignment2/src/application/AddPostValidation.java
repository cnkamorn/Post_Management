package application;

import application.Exception.BlankInputException;

public class AddPostValidation extends PostDashboardController {
	private static AddPostValidation Instance;
	private String postId;
	private String postContent;
	private String postLikes;
	private String postShares;
	private String postDateTime;

	private AddPostValidation() {
	}

	public static AddPostValidation getInstance() {
		if (Instance == null) {
			Instance = new AddPostValidation();
		}
		return Instance;
	}

	public void checkBlankField(String postId, String postContent, String postLikes, String postShares,
			String postDateTime) throws BlankInputException {
		if (postId.isBlank() || postContent.isBlank() || postLikes.isBlank() || postShares.isBlank()
				|| postDateTime.isBlank()) {
			throw new BlankInputException("Error: Blank Input found");
		}
	}

	public boolean checkWhiteSpace(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isWhitespace(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean checkInputWhiteSpace(String postId, String postLikes, String postShares) {
		if (checkWhiteSpace(postId)) {
			alert.alertWhiteSpaceFound("Post ID");
			return true;
		} else if (checkWhiteSpace(postLikes)) {
			alert.alertWhiteSpaceFound("Post Likes");
			return true;
		} else if (checkWhiteSpace(postShares)) {
			alert.alertWhiteSpaceFound("Post Shares");
			return true;
		}
		return false;
	}

	public void checkPostId() {

	}

	public void setPostLikes(String postLikes) {
		this.postLikes = postLikes;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public void setPostShares(String postShares) {
		this.postShares = postShares;
	}

	public void setPostDateTime(String postDateTime) {
		this.postDateTime = postDateTime;
	}

	public String getPostId() {
		return this.postId;
	}

}

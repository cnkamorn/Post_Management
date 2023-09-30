package application.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exception.BlankInputException;
import application.Exception.PostIdExistsException;

public class AddPostValidation extends PostDashboardController {
	private static AddPostValidation Instance;

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

	public void checkPostIdExist(String postId) throws PostIdExistsException {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "SELECT post_id FROM post WHERE post_id = '" + postId + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				throw new PostIdExistsException("Post is already exists");
			}
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

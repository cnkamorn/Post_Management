package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exception.PostIdExistsException;

public class PostDatabase {

	private static final String DB_URL = "jdbc:sqlite:data.db"; // driver
	final static String TABLE_NAME = "Post";
	// singleton
	private static PostDatabase Instance;

	private PostDatabase() {
	};

	public static PostDatabase getInstance() {
		if (Instance == null) {
			Instance = new PostDatabase();
		}
		return Instance;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL); // connection
	}

	public static void createTablePost() {
		try (Connection con = getConnection(); Statement stmt = con.createStatement();) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
					+ "(post_id VARCHAR(20) PRIMARY KEY, post_content VARCHAR(20) NOT NULL,"
					+ "post_likes VARCHAR(20) NOT NULL, post_shares VARCHAR(20) NOT NULL,"
					+ "post_date_time VARCHAR(20) NOT NULL,user_id INTEGER, CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES User(user_id));");
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// SELECT a.* FROM post AS b INNER JOIN user as A ON (b.user_id=a.user_id);
	public boolean insertPost(Post post) {
		String sql = "INSERT INTO " + TABLE_NAME
				+ " (post_id, post_content, post_likes, post_shares,post_date_time,user_id)"
				+ " VALUES (?, ?, ?, ?, ?,?)";
		try (Connection con = UserDatabase.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, Integer.toString(post.getPostID()));
			stmt.setString(2, post.getPostContent());
			stmt.setString(3, Integer.toString(post.getLikes()));
			stmt.setString(4, Integer.toString(post.getShares()));
			stmt.setString(5, post.getPostDateTime());
			stmt.setString(6, post.getPostAuthorID());
			int result = stmt.executeUpdate();
			if (result == 1) {
				return true;
			}
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.getMessage();
		}
		return false;
	}

	public void checkPostIdExist(String postId) throws PostIdExistsException {
		try (Connection con = UserDatabase.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "SELECT post_id FROM " + TABLE_NAME + " WHERE post_id = '" + postId + "';";
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

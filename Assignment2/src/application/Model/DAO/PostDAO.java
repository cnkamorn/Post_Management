package application.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Exception.PostNotFoundException;
import application.Model.Post;

/**
 * This class is a post database class It contains related methods about the
 * post database
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class PostDAO {

	private static final String DB_URL = "jdbc:sqlite:data.db"; // driver
	final static String TABLE_NAME = "Post";
	// singleton
	private static PostDAO Instance;

	private PostDAO() {
	};

	public static PostDAO getInstance() {
		if (Instance == null) {
			Instance = new PostDAO();
		}
		return Instance;
	}

	/**
	 * Method to create a connect to the post table
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL); // connection
	}

	/**
	 * Method to create a post table
	 */
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

	/**
	 * Method to insert a post to the table
	 * 
	 * @param post
	 * @return boolean
	 */
	public boolean insertPost(Post post) {
		String sql = "INSERT INTO " + TABLE_NAME
				+ " (post_id, post_content, post_likes, post_shares,post_date_time,user_id)"
				+ " VALUES (?, ?, ?, ?, ?,?)";
		try (Connection con = PostDAO.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
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

	/**
	 * Method to retrieve a post
	 * 
	 * @param authorID
	 * @param postID
	 * @return Post
	 * @throws PostNotFoundException
	 */
	public Post retrievePost(String authorID, String postID) throws PostNotFoundException {
		Post post = Post.getInstance();
		try (Connection con = PostDAO.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "SELECT * FROM Post WHERE post_id ='" + postID + "' AND user_id ='" + authorID + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				// found
				post.setPostID(Integer.parseInt(rs.getString("post_id")));
				post.setPostContent(rs.getString("post_content"));
				post.setLikes(Integer.parseInt(rs.getString("post_likes")));
				post.setShares(Integer.parseInt(rs.getString("post_shares")));
				post.setPostDateTime(rs.getString("post_date_time"));
				post.setPostAuthorID(rs.getString("user_id"));
			} else {
				throw new PostNotFoundException("Post is not exist or this user has no access to it");
			}
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}

	/**
	 * Method to retrieve a user's post
	 * 
	 * @param authorID
	 * @return Arraylist of Post
	 * @throws PostNotFoundException
	 */
	public ArrayList<Post> retrieveUserPosts(String authorID) throws PostNotFoundException {
		ArrayList<Post> posts = new ArrayList();
		try (Connection con = PostDAO.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "SELECT * FROM Post WHERE user_id ='" + authorID + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Post post = new Post(Integer.parseInt(rs.getString("post_id")), rs.getString("post_content"),
						rs.getString("user_id"), Integer.parseInt(rs.getString("post_likes")),
						Integer.parseInt(rs.getString("post_shares")), rs.getString("post_date_time"));
				posts.add(post);
			}
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (posts.size() == 0) {
			throw new PostNotFoundException("This user has no post");
		}
		return posts;
	}

	/**
	 * Method to remove the post from the database
	 * 
	 * @param authorID
	 * @param postID
	 * @return
	 * @throws PostNotFoundException
	 */
	public boolean removePost(String authorID, String postID) throws PostNotFoundException {
		try (Connection con = PostDAO.getConnection(); Statement stmt = con.createStatement();) {
			String sql = "DELETE FROM Post WHERE post_id ='" + postID + "' AND user_id ='" + authorID + "';";
			int result = stmt.executeUpdate(sql);
			if (result == 1) {
				return true;
			}
			con.close();
			stmt.close();
			throw new PostNotFoundException("Post is not exist or this user has no access to it");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

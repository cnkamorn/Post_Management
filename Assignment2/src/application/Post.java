package application;

/**
 * Class which stores information of Post
 *
 * @author Chanakan Amornpatchara
 * @version ver1.0.0
 */

public class Post {

	private int postID;
	private String postContent;
	private int likes;
	private int shares;
	private String postDateTime;
	private String postAuthorID;

	private static Post Instance;

	public static Post getInstance() {
		if (Instance == null) {
			Instance = new Post();
		}
		return Instance;
	}

	/*
	 * Default constructor which creates the object of the class Post.
	 */
	public Post() {
	};

	/*
	 * Non-default constructor which creates the object of the class Post.
	 */
	public Post(int postID, String postContent, String postAuthorID, int likes, int shares, String postDateTime) {
		this.postID = postID;
		this.postContent = postContent;
		this.postAuthorID = postAuthorID;
		this.likes = likes;
		this.shares = shares;
		this.postDateTime = postDateTime;
	}

	/**
	 * Accessor method to get the post id of the post object
	 * 
	 * @return integer the post id
	 */
	public int getPostID() {
		return postID;
	}

	/**
	 * Mutator method to set the post id of the post object
	 */
	public void setPostID(int postID) {
		this.postID = postID;
	}

	/**
	 * Accessor method to get the post content of the post object
	 * 
	 * @return String the post content
	 */
	public String getPostContent() {
		return postContent;
	}

	/**
	 * Mutator method to set the post content of the post object
	 */
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	/**
	 * Accessor method to get the post author id of the post object
	 * 
	 * @return String the post author id
	 */
	public String getPostAuthorID() {
		return postAuthorID;
	}

	/**
	 * Mutator method to set the post author id of the post object
	 */
	public void setPostAuthorID(String postAuthorID) {
		this.postAuthorID = postAuthorID;
	}

	/**
	 * Accessor method to get the number of like of the post object
	 * 
	 * @return int the number of like of the object
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * Mutator method to set the number of like of the post object
	 */

	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * Accessor method to get the number of share of the post object
	 * 
	 * @return int the number of share of the object
	 */
	public int getShares() {
		return shares;
	}

	/**
	 * Mutator method to set the number of share of the post object
	 */
	public void setShares(int shares) {
		this.shares = shares;
	}

	/**
	 * Accessor method to get the post's date and time of the post object
	 * 
	 * @return String the post's date and time object
	 */

	public String getPostDateTime() {
		return this.postDateTime;
	}

	/**
	 * Mutator method to set the post's date and time of the post object
	 */
	public void setPostDateTime(String postDateTime) {
		this.postDateTime = postDateTime;
	}

}

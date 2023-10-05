package application.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import application.Exception.PostNotFoundException;
import application.Model.Post;

/**
 * This class is a class for account setting page It's an extended class from
 * the main Post dash board controller class
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class AnalyticsChart extends PostDashboardController {
	private static AnalyticsChart Instance;

	private AnalyticsChart() {
	};

	public static AnalyticsChart getInstance() {
		if (Instance == null) {
			Instance = new AnalyticsChart();
		}
		return Instance;
	}

	/**
	 * Methong to calculate the number of shares from the current user
	 * 
	 * @return HashMap<String,Integer>
	 * @throws PostNotFoundException
	 */
	public HashMap<String, Integer> calculateShares() throws PostNotFoundException {
		HashMap<String, Integer> shares = new HashMap<String, Integer>();
		shares.put("0-99", 0);
		shares.put("100-999", 0);
		shares.put("1000+", 0);
		RetrieveMultiPosts findPosts = RetrieveMultiPosts.getInstance();
		ArrayList<Post> userPosts = findPosts.retrievePostsCollection();
		// loop each post to get shares
		for (Post post : userPosts) {
			if (post.getShares() >= 0 && post.getShares() <= 99) {
				shares.put("0-99", shares.get("0-99") + 1);
			} else if (post.getShares() >= 100 && post.getShares() <= 999) {
				shares.put("100-999", shares.get("100-999") + 1);
			} else {
				shares.put("1000+", shares.get("1000+") + 1);
			}
		}
		return shares;
	}
}

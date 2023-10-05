package application.Model;

import java.util.Comparator;

/**
 * This class is a comparator class It contains related methods about comparing
 * the number of likes
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class LikesComparator implements Comparator<Post> {

	@Override
	public int compare(Post p1, Post p2) {
		// TODO Auto-generated method stub
		return Integer.compare(p1.getLikes(), p2.getLikes());
	}
}

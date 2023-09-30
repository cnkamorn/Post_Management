package application.Controller;

import java.util.Comparator;

import application.Model.Post;

public class LikesComparator implements Comparator<Post> {

	@Override
	public int compare(Post p1, Post p2) {
		// TODO Auto-generated method stub
		return Integer.compare(p1.getLikes(), p2.getLikes());
	}
}

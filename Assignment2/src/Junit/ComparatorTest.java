package Junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import application.Model.LikesComparator;
import application.Model.Post;

class ComparatorTest {

	LikesComparator lc = new LikesComparator();

	@Test
	void sortLikesCheckAscending1() {
		Post[] posts = { new Post(1, "A", "A", 30, 0, "A"), new Post(2, "A", "A", 20, 0, "A"),
				new Post(3, "A", "A", 10, 0, "A") };
		ArrayList<Post> postList = new ArrayList();
		postList.addAll(Arrays.asList(posts));
		postList.sort(lc);
		assertTrue(postList.get(0).getPostID() == 3);
	}

	@Test
	void sortLikesCheckAscending2() {
		Post[] posts = { new Post(1, "A", "A", 30, 0, "A"), new Post(2, "A", "A", 20, 0, "A"),
				new Post(3, "A", "A", 10, 0, "A") };
		ArrayList<Post> postList = new ArrayList();
		postList.addAll(Arrays.asList(posts));
		postList.sort(lc);
		assertTrue(postList.get(2).getPostID() == 1);
	}

	@Test
	void sortLikesCheckDescending1() {
		Post[] posts = { new Post(1, "A", "A", 30, 0, "A"), new Post(2, "A", "A", 20, 0, "A"),
				new Post(3, "A", "A", 10, 0, "A") };
		ArrayList<Post> postList = new ArrayList();
		postList.addAll(Arrays.asList(posts));
		postList.sort(Collections.reverseOrder(lc));
		assertTrue(postList.get(0).getPostID() == 1);
	}

	@Test
	void sortLikesCheckDescending2() {
		Post[] posts = { new Post(1, "A", "A", 20, 0, "A"), new Post(2, "A", "A", 30, 0, "A"),
				new Post(3, "A", "A", 10, 0, "A") };
		ArrayList<Post> postList = new ArrayList();
		postList.addAll(Arrays.asList(posts));
		postList.sort(Collections.reverseOrder(lc));
		assertTrue(postList.get(0).getPostID() == 2);
	}
}

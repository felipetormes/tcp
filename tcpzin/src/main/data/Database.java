package data;

import java.util.List;

import business.domain.Conference;
import business.domain.Paper;
import business.domain.Researcher;
import business.domain.Review;

public class Database {
	private static List<Researcher> researchers;
	private static List<Conference> conferences;
	private static List<Paper> papers;
	private static List<Review> reviews;
	
	public static void initData() {
		researchers = null;
		conferences = null;
		papers = null;
		reviews = null;
	}
	
	public static List<Researcher> getResearchers() {
		return researchers;
	}
	
	public static List<Conference> getConferences() {
		return conferences;
	}
	
	public static List<Paper> getPapers() {
		return papers;
	}
	
	public static List<Review> getReviews() {
		return reviews;
	}
}

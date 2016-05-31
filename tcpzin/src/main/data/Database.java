package main.data;

import java.util.List;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;

public class Database {
	private static List<Researcher> researchers;
	private static List<Conference> conferences;
	private static List<Paper> papers;
	private static List<Review> reviews;
	
	public Database(boolean initData) {
		if (initData) {
			initData();
		}
	}
	
	public Database() {
		this(true);
	}
	
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

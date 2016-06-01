package main.business.domain;

import java.util.List;

public class Paper implements Comparable<Paper>{
	private int id;
	private String title;
	private Researcher author;
	private Conference conference;
	private Topic researchTopic;
	private List<Review> reviews;
	
	private static int last_id = 0;
	
	public Paper (String title, Researcher author, Topic researchTopic) {
		this.title = title;
		this.author = author;
		this.id = last_id++;
		this.researchTopic = researchTopic;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Researcher getAuthor() {
		return author;
	}
	
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	
	public Conference getConference() {
		return conference;
	}
	
	public Topic getResearchTopic () {
		return researchTopic;
	}
	
	public void addReview(Review review) {
		reviews.add(review);            // Add review in reviews list

	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public double getAverageGrade() {
        /*
        int allGrade = 0;
        
		for(Review review : reviews){
        	allGrade += review.getGrade();
        }
        return allGrade / reviews.size();
		*/
		return -1;
	}

	@Override
	public int compareTo(Paper other) {
			if (this.getId() > other.getId()){
				return 1;
			}
			else if (this.getId() < other.getId()){
				return -1;
			}
			else {
				return 0;
			}

	}
}

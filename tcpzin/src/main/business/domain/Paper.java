package main.business.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Paper implements Comparable<Paper>{
	private int id;
	private String title;
	private Researcher author;
	private Conference conference;
	private Topic researchTopic;
	private List<Review> reviews;
	
	private static int lastId = 0;
	
	public static Comparator<Paper> ascendingGradeComparator = new Comparator<Paper>() {
		public int compare(Paper p, Paper q) {
			Double diff = p.getAverageGrade() - q.getAverageGrade();
			if (diff > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	};
	
	public static Comparator<Paper> descendingGradeComparator = new Comparator<Paper>() {
		public int compare(Paper p, Paper q) {
			Double diff = p.getAverageGrade() - q.getAverageGrade();
			if (diff < 0) {
				return 1;
			} else {
				return -1;
			}
		}
	};
	
	public Paper(int id, String title, Researcher author, Topic researchTopic, Conference conference, List<Review> reviews) {
		this.title = title;
		this.author = author;
		this.researchTopic = researchTopic;
		this.setConference(conference);
		this.reviews = reviews;
		this.id = id;
		
		/* last id must always be the largest id */
		lastId = (id > lastId)? id : lastId;
	}
	
	public Paper(String title, Researcher author, Topic researchTopic) {
		this(lastId++, title, author, researchTopic, null, new ArrayList<Review>());
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
		conference.addPaper(this);
		this.conference = conference;
	}
	
	public Conference getConference() {
		return conference;
	}
	
	public Topic getResearchTopic () {
		return researchTopic;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);            // Add review in reviews list

	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public double getAverageGrade() {
  
        int allGrade = 0;
        
		for(Review review : reviews){
        	allGrade += review.getGrade();
        }
        return allGrade / reviews.size();
        
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
	
	@Override
	public String toString() {
		String output =
				"id: " + String.valueOf(getId()) +
				", titulo: " + getTitle() +
				", autor: " + getAuthor().getId() +
				", conferencias: " + getConference().getInitials() + 
				", topicos de pesquisa: " + getResearchTopic();
				
		return output;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Paper) {
			Paper other = (Paper) obj;
			return this.getId() == other.getId();
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return this.getId();
	}
}

package main.business.domain;
import java.util.List;
import java.util.ArrayList;

public class ReviewerRole extends Role {
	private List<Review> reviews;
	
	public ReviewerRole(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public ReviewerRole() {
		this(new ArrayList<Review>());
	}
	
	public void addReview(Review review) {
		reviews.add(review);
	}
	
	public int getNumReviews() {
		return reviews.size();
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
}

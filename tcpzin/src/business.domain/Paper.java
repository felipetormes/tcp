public class Paper implements Comparable_Paper_ {

	private int id;

	private String title;

	private Researcher author;

	private Conference conference;

	private Topic researchTopic;

	private ArrayList<Review> reviews;

	private Review[] review;

	public Paper() {

	}

	public void setId(int id) {

	}

	public int getId() {
		return 0;
	}

	public void setTitle(String title) {

	}

	public String getTitle() {
		return null;
	}

	public void setAuthor(Researcher author) {

	}

	public Researcher getAuthor() {
		return null;
	}

	public void setConference(Conference conference) {

	}

	public Conference getConference() {
		return null;
	}

	public void setResearchTopic(Topic topic) {

	}

	public Topic getResearchTopic() {
		return null;
	}

	public void addReview(Review review) {

	}

	public ArrayList<Review> getReviews() {
		return null;
	}

	public Double getAverageGrade() {
		return null;
	}


	/**
	 * @see Comparable<Paper>#compareTo(Paper)
	 */
	public int compareTo(Paper o) {
		return 0;
	}

}

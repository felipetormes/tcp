public interface PapersManagementService {

	public abstract void allocPapersToReviewers(Conference conference, int numReviewers);

	public abstract void setGradeToPaper(Paper paper, Reviewer reviewer, Double grade);

	public abstract Map<Paper,boolean> selectPapersByAverage(Conference conference);

	public abstract ArrayList<Conference> GetAllConferences();

	public abstract ArrayList<Paper> GetAllPapers();

}

public class PapersManagementServiceImpl  implements PapersManagementService {

	private Database database;

	private Database database;

	public PapersManagementServiceImpl(Database database) {

	}

	private ArrayList<CommitteeMember> getCommitteeMembersCandidates(Conference conference) {
		return null;
	}

	private ArrayList<Reviewer> getReviewersFromCandidates(ArrayList<CommitteeMember> candidates) {
		return null;
	}


	/**
	 * @see PapersManagementService#allocPapersToReviewers(Conference, int)
	 */
	public void allocPapersToReviewers(Conference conference, int numReviewers) {

	}


	/**
	 * @see PapersManagementService#setGradeToPaper(Paper, Reviewer, Double)
	 */
	public void setGradeToPaper(Paper paper, Reviewer reviewer, Double grade) {

	}


	/**
	 * @see PapersManagementService#selectPapersByAverage(Conference)
	 */
	public Map<Paper,boolean> selectPapersByAverage(Conference conference) {
		return null;
	}


	/**
	 * @see PapersManagementService#GetAllConferences()
	 */
	public ArrayList<Conference> GetAllConferences() {
		return null;
	}


	/**
	 * @see PapersManagementService#GetAllPapers()
	 */
	public ArrayList<Paper> GetAllPapers() {
		return null;
	}

}

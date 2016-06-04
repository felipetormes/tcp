package main.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.data.Database;

public class PapersManagementServiceImpl  implements PapersManagementService {

	private Database database;


	public PapersManagementServiceImpl(Database database) {
		this.database = database;
	}

	private List<Researcher> getCommitteeMembersCandidates(Conference conference) {
		return conference.getCommitteeMembers();
	}

	private List<Researcher> getReviewersFromCandidates(List<Researcher> candidates) {
		return candidates;
	}


	/**
	 * @see PapersManagementService#allocPapersToReviewers(Conference, int)
	 */
	public void allocPapersToReviewers(Conference conference, int numReviewers) {

	}


	/**
	 * @see PapersManagementService#setGradeToPaper(Paper, Reviewer, Double)
	 */
	public void setGradeToPaper(Paper paper, Researcher reviewer, Double grade) {
		Review review = new Review(paper,reviewer,grade);
		paper.addReview(review);
	}


	/**
	 * @see PapersManagementService#selectPapersByAverage(Conference)
	 */
	public Map<Paper,Boolean> selectPapersByAverage(Conference conference) {
		Map<Paper, Boolean> accepted = new HashMap<Paper, Boolean>();

		if (!conference.hasEmptyGrade()) {
			List<Paper> papers = conference.getPapers();

			for (Paper paper : papers) {
				double grade = paper.getAverageGrade();
				accepted.put(paper, grade >= 0);
			}
		}

		return accepted;
	}


	/**
	 * @see PapersManagementService#getAllConferences()
	 */
	public List<Conference> getAllConferences() {
		return Database.getConferences();
	}


	/**
	 * @see PapersManagementService#GetAllPapers()
	 */
	public List<Paper> getAllPapers() {
		return Database.getPapers();
	}

}
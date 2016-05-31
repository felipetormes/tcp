package main.business.impl;

import java.util.ArrayList;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.data.Database;

public class PapersManagementServiceImpl  implements PapersManagementService {

	private Database database;


	public PapersManagementServiceImpl(Database database) {

	}

	private ArrayList<Researcher> getCommitteeMembersCandidates(Conference conference) {
		return null;
	}

	private ArrayList<Researcher> getReviewersFromCandidates(ArrayList<Researcher> candidates) {
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
	public void setGradeToPaper(Paper paper, Researcher reviewer, Double grade) {

	}


	/**
	 * @see PapersManagementService#selectPapersByAverage(Conference)
	 */
	public Map<Paper,Boolean> selectPapersByAverage(Conference conference) {
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
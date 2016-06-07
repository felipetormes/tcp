package main.business;

import java.util.List;
import java.util.Map;

import main.exceptions.BusinessDomainException;

public interface PapersManagementService {

	/**
	 * OPERATIONS
	 */
	
	/* allocate papers for people to review. return a map from paper id to
 	   reviewer id. */
	public abstract Map<Integer, Integer> allocPapersToReviewers(String conferenceInitials,	int numReviewers) throws BusinessDomainException;
	
	/* create a review from reviewer to paper with grade. */ 
	public abstract void setGradeToPaper(int paperId, int reviewerId, Double grade);
	
	/* return is a map from paper id to the answer of "was it accepted in this
	   conference?" */
	public abstract Map<Integer, Boolean> selectPapersByAverage(String conferenceInitials);

	/**
	 * GET DATA
	 */
	
	public abstract List<String> getConferencesInitials();
	public abstract List<String> getConferencesInitialsWithPendingAllocation();

	public abstract Map<String, Integer> getPapersTitlesAndIds();
	public abstract List<Integer> getPapersIds();
	public abstract List<String> getPapersTitles();
	
	public abstract List<String> getReviewers(int paperId);
	public abstract List<Integer> sortPapersByGrade(List<Integer> papersIds, boolean ascending);
	
	public abstract Map<String, Integer> getResearchersNamesAndIds();
	public abstract List<Integer> getResearchersIds();
	public abstract List<String> getResearchersNames();

}
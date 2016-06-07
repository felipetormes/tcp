package main.business;

import java.util.List;
import java.util.Map;

import main.exceptions.BusinessDomainException;
import main.exceptions.BusinessServiceException;

public interface PapersManagementService {

	/**
	 * OPERATIONS
	 */
	
	/* allocate papers for people to review. return a map from paper id to
 	   reviewer id. */
	public abstract Map<Integer, Integer> allocPapersToReviewers(String conferenceInitials,	int numReviewers) throws BusinessServiceException;
	
	/* create a review from reviewer to paper with grade. */ 
	public abstract void setGradeToPaper(int paperId, int reviewerId, double grade) throws BusinessDomainException;
	
	/* return is a map from paper id to the answer of "was it accepted in this
	   conference?" */
	public abstract Map<Integer, Boolean> selectPapersByAverage(String conferenceInitials);

	/**
	 * GET DATA
	 * @throws BusinessServiceException 
	 */
	
	public abstract List<String> getConferencesInitials() throws BusinessServiceException;
	public abstract List<String> getConferencesInitialsWithPendingAllocation();

	public abstract Map<String, Integer> getPapersTitlesAndIds() throws BusinessServiceException;
	public abstract List<Integer> getPapersIds();
	public abstract List<String> getPapersTitles();
	
	public abstract List<String> getReviewers(int paperId) throws BusinessServiceException;
	public abstract List<Integer> sortPapersByGrade(List<Integer> papersIds, boolean ascending);
	
	public abstract Map<String, Integer> getResearchersNamesAndIds() throws BusinessServiceException;
	public abstract List<Integer> getResearchersIds();
	public abstract List<String> getResearchersNames();

}
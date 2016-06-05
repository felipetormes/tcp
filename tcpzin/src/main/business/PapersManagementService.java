package main.business;

import java.util.List;
import java.util.Map;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.exceptions.BusinessDomainException;

public interface PapersManagementService {

	public abstract Map<Integer, Integer> allocPapersToReviewers(Conference conference, int numReviewers) throws BusinessDomainException;

	public abstract void setGradeToPaper(Paper paper, Researcher reviewer, Double grade);

	public List<Conference> getConferencesWithPendingAllocation();
	
	public abstract Map<Paper,Boolean> selectPapersByAverage(Conference conference);

	public abstract List<Conference> getAllConferences();

	public abstract List<Paper> getAllPapers();

}
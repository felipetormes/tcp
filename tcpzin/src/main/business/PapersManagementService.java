package main.business;

import java.util.List;
import java.util.Map;

import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;

public interface PapersManagementService {

	public abstract void allocPapersToReviewers(Conference conference, int numReviewers);

	public abstract void setGradeToPaper(Paper paper, Researcher reviewer, Double grade);

	public abstract Map<Paper,Boolean> selectPapersByAverage(Conference conference);

	public abstract List<Conference> getAllConferences();

	public abstract List<Paper> GetAllPapers();

}
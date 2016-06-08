package main.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.data.Database;
import main.exceptions.BusinessDomainException;
import main.exceptions.BusinessServiceException;
import main.ui.text.UIUtils;

public class PapersManagementServiceImpl implements PapersManagementService {
	private Database database;

	public PapersManagementServiceImpl(Database database) {
		this.database = database;
	}

	public Map<Integer, Integer> allocPapersToReviewers(
			String conferenceInitials, int numReviewers)
			throws BusinessServiceException {
		Conference conference = database
				.getConferenceByInitials(conferenceInitials);
		return allocPapersToReviewers(conference, numReviewers);
	}

	/**
	 * allocs papers to reviewers from a given conference until all papers have
	 * at least a given number of reviews
	 * 
	 * @param conference
	 * @param numReviewers
	 *            number of reviews per paper
	 * @return a map from article to reviewer
	 * @throws BusinessServiceException
	 */
	private Map<Integer, Integer> allocPapersToReviewers(Conference conference,
			int numReviewers) throws BusinessServiceException {
		return conference.allocPapersToReviewers(numReviewers);
	}

	/**
	 * get all conferences that have pending allocations
	 * 
	 * @return list of conferences
	 */
	private List<Conference> getConferencesWithPendingAllocation() {
		List<Conference> targets = new ArrayList<Conference>();

		for (Conference conf : database.getConferencesList()) {
			if (!conf.allocationDone())
				targets.add(conf);
		}

		return targets;
	}

	/**
	 * SETTING GRADES
	 * @throws BusinessDomainException 
	 */

	public void setGradeToPaper(int paperId, int reviewerId, double grade) throws BusinessDomainException {
		Paper paper = database.getPaperById(paperId);
		Researcher reviewer = database.getResearcherById(reviewerId);
		paper.setGrade(reviewer, grade);
	}

	/**
	 * GETTING SELECTED PAPERS
	 */

	public Map<Integer, Boolean> selectPapersByAverage(String conferenceInitials) {
		Conference conference = database
				.getConferenceByInitials(conferenceInitials);
		return selectPapersByAverage(conference);
	}

	private Map<Integer, Boolean> selectPapersByAverage(Conference conference) {
		Map<Integer, Boolean> accepted = new HashMap<Integer, Boolean>();

		if (!conference.hasEmptyGrade()) {
			List<Paper> papers = conference.getPapers();

			for (Paper paper : papers) {
				double grade = paper.getAverageGrade();
				accepted.put(paper.getId(), grade >= 0);
			}
		}

		return accepted;
	}

	/**
	 * GETTING LISTS OF PAPERS, CONFERENCES AND RESEARCHERS
	 */

	/**
	 * get conferences
	 */

	public List<String> getConferencesInitials()
			throws BusinessServiceException {
		List<String> conferencesNames = new ArrayList<String>();
		if (!database.getConferencesList().isEmpty()) {
			for (Conference conf : database.getConferencesList()) {
				conferencesNames.add(conf.getInitials());
			}
		} else {
			throw new BusinessServiceException(
					UIUtils.getText("exception.business.service.noConference"));
		}

		return conferencesNames;
	}

	public List<String> getConferencesInitialsWithPendingAllocation() {
		List<String> conferencesNames = new ArrayList<String>();

		for (Conference conf : getConferencesWithPendingAllocation()) {
			conferencesNames.add(conf.getInitials());
		}

		return conferencesNames;
	}

	/**
	 * get papers
	 * 
	 * @throws BusinessServiceException
	 */

	public Map<String, Integer> getPapersTitlesAndIds()
			throws BusinessServiceException {
		Map<String, Integer> ids2titles = new HashMap<String, Integer>();
		if (!database.getPapers().entrySet().isEmpty())
			for (Map.Entry<Integer, Paper> entry : database.getPapers()
					.entrySet()) {
				int id = entry.getKey();
				String title = entry.getValue().getTitle();
				ids2titles.put(title, id);
			}
		else {
			throw new BusinessServiceException(
					UIUtils.getText("exception.business.service.noPapers"));
		}
		return ids2titles;
	}

	public List<Integer> getPapersIds() {
		List<Integer> ids = new ArrayList<Integer>();

		for (Paper paper : database.getPapersList()) {
			ids.add(paper.getId());
		}

		return ids;
	}

	public List<String> getPapersTitles() {
		List<String> titles = new ArrayList<String>();

		for (Paper paper : database.getPapersList()) {
			titles.add(paper.getTitle());
		}

		return titles;
	}

	public List<String> getReviewers(int paperId)
			throws BusinessServiceException {
		List<String> reviewers = new ArrayList<String>();

		Paper paper = database.getPaperById(paperId);
		if (!paper.getReviews().isEmpty()) {
			for (Review review : paper.getReviews()) {
				reviewers.add(review.getReviewer().getName());
			}
		} else {
			throw new BusinessServiceException(
					(UIUtils.getText("exception.business.service.noReviewers")));
		}
		return reviewers;
	}

	public List<Integer> sortPapersByGrade(List<Integer> papersIds,
			boolean ascending) {
		List<Paper> papers = new ArrayList<Paper>();

		for (Integer id : papersIds) {
			Paper paper = database.getPaperById(id);
			papers.add(paper);
		}

		papers = Paper.sortPaperByGrade(papers, ascending);

		List<Integer> sorted = new ArrayList<Integer>();
		for (Paper paper : papers) {
			sorted.add(paper.getId());
		}

		return sorted;
	}

	/**
	 * get researchers
	 * @throws BusinessServiceException 
	 */

	public Map<String, Integer> getResearchersNamesAndIds() throws BusinessServiceException {
		Map<String, Integer> ids2names = new HashMap<String, Integer>();
		if (!database.getResearchers().isEmpty()){
		for (Map.Entry<Integer, Researcher> entry : database.getResearchers()
				.entrySet()) {
			int id = entry.getKey();
			String name = entry.getValue().getName();
			ids2names.put(name, id);
		}
		}
		else {
			throw new BusinessServiceException(
					(UIUtils.getText("exception.business.service.noResearchers")));
		}

		return ids2names;
	}

	public List<Integer> getResearchersIds() {
		List<Integer> ids = new ArrayList<Integer>();

		for (Researcher paper : database.getResearchersList()) {
			ids.add(paper.getId());
		}

		return ids;
	}

	public List<String> getResearchersNames() {
		List<String> ids = new ArrayList<String>();

		for (Researcher paper : database.getResearchersList()) {
			ids.add(paper.getName());
		}

		return ids;
	}

}
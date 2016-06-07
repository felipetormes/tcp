package main.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.data.Database;
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
		Map<Integer, Integer> paper2reviewer = new HashMap<Integer, Integer>();

		/*
		 * every researcher maps to 0 in the beginning. that is, no one was
		 * assigned a paper yet.
		 */
		Map<Researcher, Integer> allocSoFar = new HashMap<Researcher, Integer>();
		for (Researcher res : conference.getCommitteeMembers()) {
			allocSoFar.put(res, 0);
		}

		List<Researcher> committee = conference.getCommitteeMembers();

		Boolean done = false;
		while (!done) {
			/* create new list so it won't remove papers from conference */
			List<Paper> allocSet = new ArrayList<Paper>(conference.getPapers());

			while (!allocSet.isEmpty()) {
				/* select paper with lowest id */
				Collections.sort(allocSet, Paper.ascendingIdComparator);
				Paper paper = allocSet.get(0);

				Researcher bestCandidate = chooseBestCandidate(committee,
						paper, allocSoFar);

				/*
				 * create the review and add one to the reviews alloc'ed so far
				 * for the best candidate.
				 */
				int count = allocSoFar.get(bestCandidate);
				new Review(paper, bestCandidate);
				allocSoFar.put(bestCandidate, count + 1);
				allocSet.remove(paper);

				paper2reviewer.put(paper.getId(), bestCandidate.getId());
			}

			done = allAllocated(conference, numReviewers);
		}

		return paper2reviewer;
	}

	/**
	 * given a committee, a paper and a map that tells how many reviews each
	 * committee member has in the conference, select the best suited researcher
	 * to review that paper.
	 * 
	 * @param committee
	 *            list of researchers on the conference committee
	 * @param paper
	 *            to be reviewed
	 * @param allocSoFar
	 *            maps researchers to reviews assigned to them so far
	 * @return the best candidate to review the paper
	 * @throws BusinessServiceException
	 */
	private Researcher chooseBestCandidate(List<Researcher> committee,
			Paper paper, Map<Researcher, Integer> allocSoFar)
			throws BusinessServiceException {

		List<Researcher> candidates = new ArrayList<Researcher>(committee);
		for (Researcher cand : committee) {
			if (!cand.isSuitedToReview(paper))
				candidates.remove(cand);
		}

		if (candidates.isEmpty()) {
			throw new BusinessServiceException(
					UIUtils.getText("exception.business.service.noCandidates"));
		}

		Researcher leastStressed; /* the one with least allocations */
		leastStressed = candidates.get(0);
		for (Researcher cand : candidates) {
			if (allocSoFar.get(cand) < allocSoFar.get(leastStressed)) {
				leastStressed = cand;
			}
		}

		return leastStressed;
	}

	/**
	 * checks if all papers in conference have at least a minimum of reviewers.
	 * 
	 * @param conference
	 *            to which the papers belong
	 * @param minimum
	 *            number of reviewers.
	 * @return true if the answer is yes, false if it's no.
	 */
	private boolean allAllocated(Conference conference, Integer minimum) {
		for (Paper paper : conference.getPapers()) {
			if (paper.getReviews().size() < minimum) {
				return false;
			}
		}

		return true;
	}

	/**
	 * get all conferences that have pending allocations
	 * 
	 * @return list of conferences
	 */
	private List<Conference> getConferencesWithPendingAllocation() {
		List<Conference> targets = new ArrayList<Conference>();

		for (Conference conf : database.getConferencesList()) {
			if (!conf.isAllocationDone())
				targets.add(conf);
		}

		return targets;
	}

	/**
	 * SETTING GRADES
	 */

	public void setGradeToPaper(int paperId, int reviewerId, Double grade) {
		Paper paper = database.getPaperById(paperId);
		Researcher reviewer = database.getResearcherById(reviewerId);
		setGradeToPaper(paper, reviewer, grade);
	}

	private void setGradeToPaper(Paper paper, Researcher reviewer, Double grade) {
		new Review(paper, reviewer, grade);
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

		Comparator<Paper> comparator;
		if (ascending) {
			comparator = Paper.ascendingGradeComparator;
		} else {
			comparator = Paper.descendingGradeComparator;
		}

		Collections.sort(papers, comparator);

		List<Integer> sorted = new ArrayList<Integer>();
		for (Paper paper : papers) {
			sorted.add(paper.getId());
		}

		return sorted;
	}

	/**
	 * get researchers
	 */

	public Map<String, Integer> getResearchersNamesAndIds() {
		Map<String, Integer> ids2names = new HashMap<String, Integer>();

		for (Map.Entry<Integer, Researcher> entry : database.getResearchers()
				.entrySet()) {
			int id = entry.getKey();
			String name = entry.getValue().getName();
			ids2names.put(name, id);
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
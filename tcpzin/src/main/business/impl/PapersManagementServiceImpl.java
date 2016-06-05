package main.business.impl;

import java.util.ArrayList;
import java.util.Collections;
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

public class PapersManagementServiceImpl  implements PapersManagementService {
	private Database database;
	
	public PapersManagementServiceImpl(Database database) {
		this.database = database;
	}


	/**
	 * allocs papers to reviewers from a given conference until all papers
	 * have at least a given number of reviews
	 * @param conference
	 * @param numReviewers number of reviews per paper
	 * @return a map from article to reviewer
	 * @throws BusinessDomainException 
	 */
	public Map<Integer, Integer> allocPapersToReviewers(Conference conference, int numReviewers)
			throws BusinessDomainException {
		Map<Integer, Integer> paper2reviewer = new HashMap<Integer, Integer>();
		
		/* every researcher maps to 0 in the beginning. that is, no one was
		 * assigned a paper yet. */
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
				
				Researcher bestCandidate = chooseBestCandidate(committee, paper, allocSoFar);
				
				/* create the review and add one to the reviews alloc'ed so far
				 * for the best candidate. */
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
	 * committee member has in the conference, select the best suited
	 * researcher to review that paper.
	 * @param committee list of researchers on the conference committee
	 * @param paper to be reviewed
	 * @param allocSoFar maps researchers to reviews assigned to them so far
	 * @return the best candidate to review the paper
	 * @throws BusinessDomainException 
	 */
	private Researcher chooseBestCandidate(List<Researcher> committee,
	                                       Paper paper,
	                                       Map<Researcher, Integer> allocSoFar)
	        throws BusinessDomainException {

		List<Researcher> candidates = new ArrayList<Researcher>(committee);
		for (Researcher cand : committee) {
			if (!cand.isSuitedToReview(paper)) candidates.remove(cand);
		}
		
		if (candidates.isEmpty()) {
			throw new BusinessDomainException();
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
	 * @param conference to which the papers belong
	 * @param minimum number of reviewers.
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
	 * @see PapersManagementService#setGradeToPaper(Paper, Reviewer, Double)
	 */
	public void setGradeToPaper(Paper paper, Researcher reviewer, Double grade) {
		new Review(paper, reviewer, grade);
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
		return database.getConferences();
	}

	public List<Conference> getConferencesWithPendingAllocation() {
		List<Conference> targets = new ArrayList<Conference>();

		for (Conference conf : getAllConferences()) {
			if (!conf.isAllocationDone()) targets.add(conf);
		}

		return targets;
	}


	/**
	 * @see PapersManagementService#GetAllPapers()
	 */
	public List<Paper> getAllPapers() {
		return database.getPapers();
	}

}
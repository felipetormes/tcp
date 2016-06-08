package main.business.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.exceptions.BusinessDomainException;

public class Conference {
	private String initials;
	private List<Paper> papers;
	private List<Researcher> committeeMembers;
	private Boolean allocationDone;
	private int lowerLimitReviewers = 2;
	private int upperLimitReviewers = 5;

	public Conference(String initials) {
		this.initials = initials;
		this.papers = new ArrayList<Paper>();
		this.committeeMembers = new ArrayList<Researcher>();
		allocationDone = false;
	}

	public boolean hasEmptyGrade() {
		for (Paper paper : papers) {
			if (paper.hasPendingReviews())
				return true;
		}

		return false;
	}

	public void addCommitteeMember(Researcher member) {
		committeeMembers.add(member);
		Role role = new CommitteeMemberRole();
		member.addRole(this, role);
	}

	public void addPaper(Paper paper) {
		papers.add(paper);
		Role role = new ResearcherRole();
		paper.getAuthor().addRole(this, role);
		allocationDone = false;
	}

	public String getInitials() {
		return initials;
	}

	public List<Paper> getPapers() {
		return papers;
	}

	public Boolean allocationDone() {
		return allocationDone;
	}

	public List<Researcher> getCommitteeMembers() {
		return committeeMembers;
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
	public Map<Integer, Integer> allocPapersToReviewers(int numReviewers) throws BusinessDomainException {
		if (numReviewers <= lowerLimitReviewers || numReviewers >= lowerLimitReviewers) {
			throw new BusinessDomainException("exception.business.domain.invalidReviewersGrade");
		}
		
		if (allocationDone) {
			throw new BusinessDomainException("message.business.domain.alreadyAllocated");
		}
		
		Map<Integer, Integer> paper2reviewer = new HashMap<Integer, Integer>();
		
		/*
		 * every researcher maps to 0 in the beginning. that is, no one was
		 * assigned a paper yet.
		 */
		Map<Researcher, Integer> allocSoFar = new HashMap<Researcher, Integer>();
		for (Researcher res : committeeMembers) {
			allocSoFar.put(res, 0);
		}

		Boolean done = false;
		while (!done) {
			/* create new list so it won't remove papers from conference */
			List<Paper> allocSet = new ArrayList<Paper>(papers);

			while (!allocSet.isEmpty()) {
				/* select paper with lowest id */
				Collections.sort(allocSet, Paper.ascendingIdComparator);
				Paper paper = allocSet.get(0);

				Researcher bestCandidate = chooseBestCandidate(paper,
						allocSoFar);

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

			done = allAllocated(numReviewers);
		}

		allocationDone = true;

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
	private Researcher chooseBestCandidate(Paper paper,
			Map<Researcher, Integer> allocSoFar)
			throws BusinessDomainException {

		List<Researcher> candidates = new ArrayList<Researcher>(
				committeeMembers);
		for (Researcher cand : committeeMembers) {
			if (!cand.isSuitedToReview(paper))
				candidates.remove(cand);
		}

		if (candidates.isEmpty()) {
			throw new BusinessDomainException(
					"exception.business.service.noCandidates");
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
	private boolean allAllocated(Integer minimum) {
		for (Paper paper : papers) {
			if (paper.getReviews().size() < minimum) {
				return false;
			}
		}

		return true;
	}

	public Map<Integer, Boolean> selectPapersByAverage() throws BusinessDomainException {
		Map<Integer, Boolean> accepted = new HashMap<Integer, Boolean>();

		if (!this.hasEmptyGrade()) {
			for (Paper paper : papers) {
				double grade = paper.getAverageGrade();
				accepted.put(paper.getId(), grade >= 0);
			}
		} else {
			throw new BusinessDomainException("exception.business.domain.pendingRevisions");
		}

		return accepted;
	}

	@Override
	public String toString() {
		String output = "==> conference:\n";
		output += "initials: " + initials + "\n";
		output += "allocation: ";
		output += allocationDone ? "done\n" : "pending\n";
		output += "papers:\n";
		for (Paper paper : papers) {
			output += "- " + paper.getId() + ". " + paper.getTitle() + "\n";
		}

		output += "committee:\n";
		for (Researcher res : committeeMembers) {
			output += "- " + res.getId() + ". " + res.getName() + "\n";
		}

		return output;
	}

	public int hashCode() {
		return initials.hashCode();
	}
}
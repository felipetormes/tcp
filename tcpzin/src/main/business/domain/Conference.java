package main.business.domain;

import java.util.List;
import java.util.ArrayList;

public class Conference {
	private String initials;
	private List<Paper> papers;
	private List<Researcher> committeeMembers;
	
	public Conference(String initials) {
		this.initials = initials;
		this.papers = new ArrayList<Paper>();
		this.committeeMembers = new ArrayList<Researcher>();
	}
	
	public boolean hasEmptyGrade() {
		/* if one paper has less reviews than this conference has committee
		 * members, then some grade is empty.
		 */
		for (Paper paper : papers) {
			List<Review> reviews = paper.getReviews();
			for (Review review : reviews) {
				if (review.isPendingGrade()) return true;
			}
		}
		
		return false;
	}
	
	public void addCommitteeMember(Researcher member) {
		committeeMembers.add(member);
	}
	
	public void addPaper(Paper paper) {
		papers.add(paper);
	}
	
	public String getInitials() {
		return initials;
	}
	
	public List<Paper> getPapers() {
		return papers;
	}
	
	public List<Researcher> getCommitteeMembers() {
		return committeeMembers;
	}
	
	public String toString() {
		String output =
				"initials: " + getInitials() +
				", committee members ids: ";
		
		for (Researcher researcher : getCommitteeMembers()) {
			output += researcher.getId() + "/";
		}
		
		output += ", papers: ";
		for (Paper paper : papers) {
			output += paper.getTitle() + "/";
		}
		
		return output;
	}
}
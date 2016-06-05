package main.business.domain;

import java.util.List;
import java.util.ArrayList;

public class Conference {
	private String initials;
	private List<Paper> papers;
	private List<Researcher> committeeMembers;
	private Boolean allocationDone;
	
	public Conference(String initials) {
		this.initials = initials;
		this.papers = new ArrayList<Paper>();
		this.committeeMembers = new ArrayList<Researcher>();
		allocationDone = false;
	}
	
	public boolean hasEmptyGrade() {
		for (Paper paper : papers) {
			if (paper.hasPendingReviews()) return true;
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
	}
	
	public String getInitials() {
		return initials;
	}
	
	public List<Paper> getPapers() {
		return papers;
	}
	
	public Boolean isAllocationDone() {
		return allocationDone;
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
	
	public int hashCode() {
		return initials.hashCode();
	}
}
package main.business.domain;

public class CommitteeMemberRole extends Role {

	public CommitteeMemberRole(Conference conference) {
		super(conference);
	}
	
	// TODO
	public boolean isCandidate() {
		return false;
	}
}

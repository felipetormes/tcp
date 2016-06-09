package main.business.domain;

public class CommitteeMemberRole extends Role {
	// TODO
	public boolean isCandidate() {
		return false;
	}

	@Override
	public String toString() {
		return "committee member role";
	}
}

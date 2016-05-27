public class CommitteeMember extends Researcher {

	private Conference conference;

	public CommitteeMember() {

	}

	public boolean isConferenceMemberCandidate(Conference conference, Paper paper) {
		return false;
	}

}

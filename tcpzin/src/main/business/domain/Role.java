package main.business.domain;

public abstract class Role {
	private Conference conference;
	
	public Role(Conference conference) {
		this.setConference(conference);
	}
	
	public Conference getConference() {
		return conference;
	}
	
	public void setConference(Conference conference) {
		this.conference = conference;
	}
}

package main.business.domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Researcher implements Comparable<Researcher>{
	private int id;
	private String name;
	private University affiliation;
	private List<Topic> researchTopics;
	private Map<Conference, Role> roles;
	private static int lastId = 0;
	
	public Researcher(String name, University affiliation, List<Topic> researchTopics, int id) {
		this.name = name;
		this.affiliation = affiliation;
		this.researchTopics = researchTopics;
		this.id = id;
		this.roles = new HashMap<Conference, Role>();

		/* last id must always be the largest id */
		lastId = (id > lastId)? id : lastId;
	}
	
	public Researcher(String name, University affiliation, List<Topic> researchTopics) {
		this(name, affiliation, researchTopics, lastId++);
	}
	
	public Researcher(String name, University affiliation) {
		this(name, affiliation, new ArrayList<Topic>());
	}
	public void addTopic(Topic topic){
		researchTopics.add(topic);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public University getAffiliation() {
		return affiliation;
	}
	
	public List<Topic> getResearchTopics() {
		return researchTopics;
	}

	@Override
	public int compareTo(Researcher other) {
		if (this.getId() > other.getId()){
			return 1;
		}
		else if (this.getId() < other.getId()){
			return -1;
		}
		else {
			return 0;
		}
	}
	
	public boolean isSuitedToReview(Paper paper) {
		if (paper.getAuthor().equals(this)) {
			return false;
		}
		
		if (paper.getAuthor().getAffiliation().equals(this.affiliation)) {
			return false;
		}
		
		if (this.reviews(paper)) {
			return false;
		}
		
		if (!this.researchTopics.contains(paper.getResearchTopic())) {
			return false;
		}
		
		return true;
	}
	
	private boolean reviews(Paper paper) {
		Role role = roles.get(paper.getConference());
		
		if (role == null) {
			return false;
		}
		
		if (role instanceof ReviewerRole) {
			List<Review> reviews = ((ReviewerRole) role).getReviews();
			for (Review rev : reviews) {
				if (rev.getPaper() == paper) {
					return true;
				}
			}
		}
		
		return false;
	}

	public Map<Conference, Role> getRoles() {
		return roles;
	}
	
	public Role getRole(Conference conference) {
		return roles.get(conference);
	}

	public void addRole(Conference conference, Role role) {
		roles.put(conference, role);
	}
	
	@Override
	public String toString() {
		String output = "==> researcher:\n";
		output += "id: " + id + "\n";
		output += "name: " + name;
		output += "affiliation: " + affiliation + "\n";
		output += "roles:\n";
		
		for (Map.Entry<Conference, Role> entry : roles.entrySet()) {
			String conf = entry.getKey().getInitials();
			Role role = entry.getValue();
			output += "- " + role + " at " + conf + "\n";
		}

		output += "topics:\n";
		
		for (Topic topic : researchTopics) {
			output += "- " + topic + "\n";
		}

		return output;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Researcher) {
			Researcher other = (Researcher) obj;
			return this.getId() == other.getId();
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return this.getId();
	}
}

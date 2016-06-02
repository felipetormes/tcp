package main.business.domain;
import java.util.List;
import java.util.ArrayList;

public class Researcher implements Comparable<Researcher>{
	private int id;
	private String name;
	private University affiliation;
	private List<Topic> researchTopics;
	private Role role;
	private static int lastId = 0;
	
	public Researcher(String name, University affiliation, List<Topic> researchTopics, int id) {
		this.name = name;
		this.affiliation = affiliation;
		this.researchTopics = researchTopics;
		this.id = id;
		
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
	
	public void addRole (Role role) {
		this.setRole(role);
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String toString() {
		String output =
				"nome: " + getName() +
				", id: " + String.valueOf(getId()) +
				", affiliation: " + getAffiliation() +
				", topics: ";
		
		for (Topic topic : getResearchTopics()) {
			output += topic + "-";
		}
		
		return output;
	}
}

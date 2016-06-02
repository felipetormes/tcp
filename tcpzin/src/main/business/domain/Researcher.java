package main.business.domain;
import java.util.List;
import java.util.ArrayList;

public class Researcher implements Comparable<Researcher>{
	private int id;
	private String name;
	private University affiliation;
	private List<Topic> researchTopics;
	
	private static int last_id = 0;
	
	public Researcher(String name, University affiliation, List<Topic> researchTopics) {
		this.name = name;
		this.affiliation = affiliation;
		this.researchTopics = researchTopics;
		this.id = last_id++;
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
}

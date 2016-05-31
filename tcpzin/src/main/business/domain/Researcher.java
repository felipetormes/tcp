package main.business.domain;
import java.util.List;
import java.util.ArrayList;

public class Researcher {
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
	
	public int getId() {
		return id;
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
}

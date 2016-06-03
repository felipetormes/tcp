package main.business.domain;

public class Review {
	private Paper paper;
	private boolean isPending;
	private Researcher reviewer;
	private double grade;
	
	public Review(Paper paper, Researcher reviewer, double grade){
		this.paper = paper;
		this.reviewer = reviewer;
		this.setGrade(grade);
		this.isPending = false;
	}
	
	public Review(Paper paper, Researcher reviewer) {
		this(paper, reviewer, -1);
		this.isPending = true;
	}
	
	public void setPaper (Paper paper) {
		this.paper = paper;
	}
	
	public Paper getPaper() {
		return paper;
	}
	
	public void setReviewer (Researcher reviewer) {
		this.reviewer = reviewer;
	}
	
	public Researcher getReviewer() {
		return reviewer;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public boolean isPendingGrade() {
		return isPending;
	}
	
	@Override
	public String toString() {
		String output =
				"artigo: " + getPaper().getId() +
				", revisor: " + getReviewer().getId() +
				", nota: " + getGrade();
				
	return output;
	}
}

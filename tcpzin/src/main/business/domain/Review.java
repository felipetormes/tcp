package main.business.domain;

import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;

public class Review {
	private Paper paper;
	private boolean isPending;
	private Researcher reviewer;
	private double grade;
	private final static int LOWER_LIMIT_GRADE = -3;
	private final static int UPPER_LIMIT_GRADE = 3;

	public Review(Paper paper, Researcher reviewer, double grade)
			throws BusinessDomainException {
		this.paper = paper;
		this.reviewer = reviewer;
		this.setReviewer(reviewer);
		this.setPaper(paper);
		this.setGrade(grade);
		this.isPending = false;
	}

	public Review(Paper paper, Researcher reviewer)
			throws BusinessDomainException {
		this(paper, reviewer, -1);
		this.isPending = true;
	}

	public void setPaper(Paper paper) {
		paper.addReview(this);
	}

	public Paper getPaper() {
		return paper;
	}

	public void setReviewer(Researcher reviewer) {
		ReviewerRole reviewerRole = new ReviewerRole();
		reviewerRole.addReview(this);
		Role role = reviewerRole;
		reviewer.addRole(this.paper.getConference(), role);
	}

	public Researcher getReviewer() {
		return reviewer;
	}

	public void setGrade(double grade) throws BusinessDomainException {
		if (grade >= LOWER_LIMIT_GRADE && grade <= UPPER_LIMIT_GRADE) {
			this.grade = grade;
			this.isPending = false;
		} else {
			throw new BusinessDomainException(
					(UIUtils.getText("exception.business.domain.invalidGradeRange")));
		}

	}

	public double getGrade() {
		return grade;
	}

	public boolean isPendingGrade() {
		return isPending;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Review) {
			Review other = (Review) obj;
			return reviewer.equals(other.getReviewer())
					&& paper.equals(other.getPaper());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.paper.hashCode();
	}

	@Override
	public String toString() {
		String output = "==> review:\n";
		output += "paper id: " + paper.getId() + "\n";
		output += "reviewer id: " + reviewer.getId() + "\n";
		output += "grade: ";
		output += isPending ? "pending\n" : grade + "\n";
		return output;
	}

}

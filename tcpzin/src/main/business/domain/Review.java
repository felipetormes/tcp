package main.business.domain;

import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;

public class Review {
	private Paper paper;
	private boolean isPending;
	private Researcher reviewer;
	private double grade;
	private final static int LOWER_LIMIT = -3;
	private final static int UPPER_LIMIT = 3;

	public Review(Paper paper, Researcher reviewer, double grade)
			throws BusinessDomainException {
		this.setPaper(paper);
		this.setReviewer(reviewer);
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
		this.paper = paper;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setReviewer(Researcher reviewer) {
		ReviewerRole reviewerRole = new ReviewerRole();
		reviewerRole.addReview(this);
		Role role = reviewerRole;
		reviewer.addRole(this.paper.getConference(), role);
		this.reviewer = reviewer;
	}

	public Researcher getReviewer() {
		return reviewer;
	}

	public void setGrade(double grade) throws BusinessDomainException {
		if (grade >= LOWER_LIMIT && grade <= UPPER_LIMIT) {
			this.grade = grade;
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
	public String toString() {
		String output = "==> review:\n";
		output += "paper id: " + paper.getId() + "\n";
		output += "reviewer id: " + reviewer.getId() + "\n";
		output += "grade: ";
		output += isPending ? "pending\n" : grade + "\n";
		return output;
	}

}

package main.ui.text.command;

import java.util.ArrayList;
import java.util.List;

import main.business.PapersManagementService;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;

public class PapersGradesAttributionCommand implements ConferenceUICommand {

	private PapersManagementService papersManagementService;

	public PapersGradesAttributionCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	/**
	 * requests a paper, a reviewer and a grade from the user. then it creates
	 * a review with those data.
	 */
	public void execute() {
		try {
			Paper paper = readPaper();
			Researcher reviewer = readReviewer(paper);
			double grade = readGrade();
			papersManagementService.setGradeToPaper(paper, reviewer, grade);
		} catch (BusinessDomainException e) {
			System.out.println(e.getMessage());
		}
	}

	private Paper readPaper() throws BusinessDomainException {
		List<Paper> allPapers = papersManagementService.getAllPapers();

		Paper paper = UIUtils.chooseFromList(allPapers);

		return paper;
	}

	private Researcher readReviewer(Paper paper) throws BusinessDomainException {
		List<Review> reviews = paper.getReviews();

		List<Researcher> possibleReviewers = new ArrayList<Researcher>();
		for (Review review : reviews) {
			possibleReviewers.add(review.getReviewer());
		}

		Researcher reviewer = UIUtils.chooseFromList(possibleReviewers);

		return reviewer;
	}

	private double readGrade() {
		return UIUtils.readDouble("message.insiraNota");
	}

}

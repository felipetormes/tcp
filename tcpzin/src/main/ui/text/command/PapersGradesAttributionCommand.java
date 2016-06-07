package main.ui.text.command;

import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;

public class PapersGradesAttributionCommand implements ConferenceUICommand {

	private PapersManagementService papersManagementService;

	public PapersGradesAttributionCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	/**
	 * requests a paper, a reviewer and a grade from the user. then it creates a
	 * review with those data.
	 * @throws BusinessDomainException 
	 */
	public void execute() throws BusinessDomainException {
		
			Integer paperId = readPaper();
			Integer reviewerId = readReviewer(paperId);
			double grade = readGrade();
			papersManagementService.setGradeToPaper(paperId, reviewerId, grade);
		
	}

	private Integer readPaper() throws BusinessDomainException {
		List<String> titles = papersManagementService.getPapersTitles();

		if (!titles.isEmpty()) {
			String chosen = UIUtils.chooseFromList(titles);
			Map<String, Integer> title2id = papersManagementService.getPapersTitlesAndIds();
			return title2id.get(chosen);
		} else {
			return null;
		}
	}

	private Integer readReviewer(int paper) throws BusinessDomainException {
		List<String> possibleReviewers = papersManagementService.getReviewers(paper);

		if(!possibleReviewers.isEmpty()){
			String chosen = UIUtils.chooseFromList(possibleReviewers);
			Map<String, Integer> name2id = papersManagementService.getPapersTitlesAndIds();
			return name2id.get(chosen);
		} else {
			throw new BusinessDomainException((UIUtils.getText("exception.business.domain.noPossibleReviewers")));
		}
	}

	private double readGrade() {
		return UIUtils.readDouble("message.insertGrade");
	}

}

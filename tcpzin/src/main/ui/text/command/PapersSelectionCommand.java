package main.ui.text.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.business.domain.Paper;
import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;

public class PapersSelectionCommand implements ConferenceUICommand {
	private boolean isChosenConference = false;
	private PapersManagementService papersManagementService;

	public PapersSelectionCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	public void execute() {
		Conference conference = readConference();
		Map<Paper, Boolean> papersMap = papersManagementService
				.selectPapersByAverage(conference);
		showAccRejLists(papersMap);
	}

	private Conference readConference() {
		List<Conference> allConferences = papersManagementService.getAllConferences();
		
		for (int i = 0; i < allConferences.size(); i++) {
			String option = String.valueOf(i + 1) + ". " + allConferences.get(i);
			System.out.println(option);
		}
		
		int chosen = UIUtils.readInteger("message.choose.option");
		
		return allConferences.get(chosen);
	}

	private void showGradeMissingAlert() {

	}

	private void showAccRejLists(Map<Paper, Boolean> listsMap) {
		List<Paper> allPapers = papersManagementService.getAllPapers();
		List<Paper> rejectedList = new ArrayList<Paper>();
		List<Paper> acceptedList = new ArrayList<Paper>();

		for (Paper paper : allPapers) {
			boolean isRejected = (listsMap.get(paper) == false);
			if (isRejected) {
				rejectedList.add(paper);
			} else {
				acceptedList.add(paper);
			}
		}
		
		Collections.sort(rejectedList, Paper.descendingGradeComparator);
		Collections.sort(acceptedList, Paper.ascendingGradeComparator);
		
		System.out.println(UIUtils.getText("message.artigosRejeitados"));
		for (Paper rejPaper : rejectedList) {
			System.out.println(UIUtils.getText("message.message.paperId")
					+ ": " + rejPaper.getId()
					+ UIUtils.getText("message.message.paperTitle") + ": "
					+ rejPaper.getTitle());

		}
		System.out.println(UIUtils.getText("message.artigosAceitos"));
		for (Paper accPaper : acceptedList) {
			System.out.println(UIUtils.getText("message.message.paperId")
					+ ": " + accPaper.getId()
					+ UIUtils.getText("message.message.paperTitle") + ": "
					+ accPaper.getTitle());

		}

	}
}

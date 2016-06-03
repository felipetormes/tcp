package main.ui.text.command;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.business.domain.Paper;
import main.business.domain.Researcher;
import main.business.domain.Review;
import main.business.impl.PapersManagementServiceImpl;
import main.ui.text.UIUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.lang.Comparable;
import java.util.Comparator;

public class PapersSelectionCommand implements ConferenceUICommand {

	private PapersManagementService papersManagementService;

	public PapersSelectionCommand(
			PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	public void execute() {
		Conference conference = readConference();
		Map<Paper, Boolean> papersMap = papersManagementService
				.selectPapersByAverage(conference);
		showAccRejLists(papersMap);
	}

	private Conference readConference() {
		// List<Conference> allConferences = null;
		Conference chosenConference = null;

		List<Conference> allConferences = papersManagementService.getAllConferences();
		
		System.out.println(allConferences);
		
		//System.out.println(UIUtils.getText("message.todosReviewers"));

		
		 for (Conference conference : allConferences ){
		 System.out.println(UIUtils.getText("message.conferenceInitials") + ": "+
		 conference.getInitials() + UIUtils.getText("message.conferenceMembers") + ": "+
		 conference.getCommitteeMembers()); } 
		 String initialsConference = UIUtils.readString("message.insiraIdRevisor"); for (Researcher
		 reviewer : allReviewers ){ boolean isChosenReviewer =
		 reviewer.getId() == idReviewer; if(isChosenReviewer) { chosenReviewer= reviewer; break; } }
		 
		 } else {
		 System.out.println(UIUtils.getText("message.naoTemReviewers")); }
		 

		return null;
	}

	private void showGradeMissingAlert() {

	}

	private void showAccRejLists(Map<Paper, Boolean> listsMap) {
		List<Paper> allPapers = papersManagementService.GetAllPapers();
		List<Paper> rejectedList = null;
		List<Paper> acceptedList = null;

		for (Paper paper : allPapers) {
			boolean isRejected = (listsMap.get(paper) == false);
			if (isRejected) {
				rejectedList.add(paper);
				Collections.sort(allPapers);
			} else {
				acceptedList.add(paper);
				// TODO SORT OF LIST
			}
		}
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

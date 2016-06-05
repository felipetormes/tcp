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
	private PapersManagementService papersManagementService;

	public PapersSelectionCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	/**
	 * reads conference from user, get approved/rejected papers and prints them
	 * in ascending/descending order.
	 */
	public void execute() {
		Conference conference = null;
		try {
			conference = readConference();

			Map<Paper, Boolean> papersMap = papersManagementService.selectPapersByAverage(conference);
			if (papersMap.isEmpty()) {
				showGradeMissingAlert();
			} else {
				showAccRejLists(papersMap);
			}
		} catch (BusinessDomainException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * shows all conferences so user can pick one.
	 * 
	 * @return the conference picked.
	 * @throws BusinessDomainException
	 */
	private Conference readConference() throws BusinessDomainException {
		Conference conference = null;
		List<Conference> allConferences = papersManagementService.getAllConferences();
		if (allConferences != null) {
			conference = UIUtils.chooseFromList(allConferences);
		} else {
			throw new BusinessDomainException((UIUtils.getText("exception.business.domain.noConference")));
		}

		return conference;
	}

	/**
	 * shows a message informing that there are pending revisions.
	 */
	private void showGradeMissingAlert() {
		System.out.println(UIUtils.getText("alert.pendingRevisions"));
	}

	/**
	 * shows a list of rejected and accepted papers in descending and ascending
	 * order respectively.
	 * 
	 * @param listsMap
	 *            mapping that goes from a paper to true/false depending on if
	 *            it was accepted/rejected.
	 */
	private void showAccRejLists(Map<Paper, Boolean> listsMap) {
		List<Paper> rejectedList = new ArrayList<Paper>();
		List<Paper> acceptedList = new ArrayList<Paper>();

		for (Map.Entry<Paper, Boolean> entry : listsMap.entrySet()) {
			Paper paper = entry.getKey();
			Boolean accepted = entry.getValue();

			if (accepted) {
				acceptedList.add(paper);
			} else {
				rejectedList.add(paper);
			}
		}

		Collections.sort(rejectedList, Paper.descendingGradeComparator);
		Collections.sort(acceptedList, Paper.ascendingGradeComparator);

		System.out.println(UIUtils.getText("message.rejectedPapers"));
		for (Paper rejPaper : rejectedList) {
			System.out.println(rejPaper);

		}
		System.out.println(UIUtils.getText("message.acceptedPapers"));
		for (Paper accPaper : acceptedList) {
			System.out.println(accPaper);

		}

	}
}

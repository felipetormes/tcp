package main.ui.text.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.exceptions.BusinessDomainException;
import main.exceptions.BusinessServiceException;
import main.ui.text.UIUtils;

public class PapersSelectionCommand implements ConferenceUICommand {
	private PapersManagementService papersManagementService;

	public PapersSelectionCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	/**
	 * reads conference from user, get approved/rejected papers and prints them
	 * in ascending/descending order.
	 * @throws BusinessServiceException 
	 * @throws BusinessDomainException 
	 */
	public void execute() throws BusinessServiceException, BusinessDomainException {
		String conference = readConference();

			Map<Integer, Boolean> papersMap = papersManagementService.selectPapersByAverage(conference);

			if (papersMap.isEmpty()) {
				showGradeMissingAlert();
			} else {
				showAccRejLists(papersMap);
			}
	
	}

	/**
	 * shows all conferences so user can pick one.
	 * 
	 * @return the conference picked.
	 * @throws BusinessServiceException
	 */
	private String readConference() throws BusinessServiceException {
		List<String> allConferences = papersManagementService.getConferencesInitials();
			String chosen = UIUtils.chooseFromList(allConferences);
			return chosen;
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
	private void showAccRejLists(Map<Integer, Boolean> listsMap) {
		List<Integer> rejectedList = new ArrayList<Integer>();
		List<Integer> acceptedList = new ArrayList<Integer>();

		for (Map.Entry<Integer, Boolean> entry : listsMap.entrySet()) {
			Integer id = entry.getKey();
			Boolean accepted = entry.getValue();

			if (accepted) {
				acceptedList.add(id);
			} else {
				rejectedList.add(id);
			}
		}

		acceptedList = papersManagementService.sortPapersByGrade(acceptedList, false);
		rejectedList = papersManagementService.sortPapersByGrade(rejectedList, true);

		System.out.println(UIUtils.getText("message.rejectedPapers"));
		for (Integer rejPaper : rejectedList) {
			System.out.println(rejPaper);

		}
		System.out.println(UIUtils.getText("message.acceptedPapers"));
		for (Integer accPaper : acceptedList) {
			System.out.println(accPaper);

		}

	}
}

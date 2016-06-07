package main.ui.text.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;

public class PapersAllocationCommand implements ConferenceUICommand {

	private PapersManagementService papersManagementService;

	public PapersAllocationCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	public void execute() {
		try {
			String conference = readConference();
			Integer numReviewers = UIUtils.readInteger("message.insertNumReviewers");
			
			if (conference != null) {				
				allocate(conference, numReviewers);
			} else {
				System.out.println(UIUtils.getText("message.noConferencesInDatabase"));
			}
		} catch (BusinessDomainException e) {
			System.out.println(UIUtils.getText("alert.impossibleAllocation"));
		}
	}
	
	private void allocate(String conference, int numReviewers) throws BusinessDomainException {
		Map<Integer, Integer> paper2reviewer = new HashMap<Integer, Integer>();

		System.out.println(UIUtils.getText("message.startingAllocation"));
		paper2reviewer = papersManagementService.allocPapersToReviewers(conference, numReviewers);
		showAllocationLog(paper2reviewer);
		System.out.println(UIUtils.getText("message.endOfAllocation"));
	}

	private String readConference() throws BusinessDomainException {
		List<String> allConferences = papersManagementService.getConferencesInitials();

		if (!allConferences.isEmpty()) {
			String chosen = UIUtils.chooseFromList(allConferences);
			return chosen;
		} else {
			return null;
		}
	}

	private void showAllocationLog(Map<Integer, Integer> paper2reviewer) {
		for (Map.Entry<Integer, Integer> entry : paper2reviewer.entrySet()) {
			System.out.print(UIUtils.getText("message.thisPaper") + " " + entry.getKey());
			System.out.println(UIUtils.getText("message.wasAllocedTo") + " " + entry.getValue());
		}
	}

}

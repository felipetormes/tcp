package main.ui.text.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.business.PapersManagementService;
import main.business.domain.Conference;
import main.exceptions.BusinessDomainException;
import main.ui.text.UIUtils;

public class PapersAllocationCommand implements ConferenceUICommand {

	private PapersManagementService papersManagementService;

	public PapersAllocationCommand(PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
	}

	public void execute() {
		Map<Integer, Integer> paper2reviewer = new HashMap<Integer, Integer>();
		
		Conference conference = readConference();
		Integer numReviewers = UIUtils.readInteger("message.insertNumReviewers");

		System.out.println(UIUtils.getText("message.startingAllocation"));
		try {
			paper2reviewer = papersManagementService.allocPapersToReviewers(conference, numReviewers);
		} catch (BusinessDomainException e) {
			e.printStackTrace();
		}

		showAllocationLog(paper2reviewer);
		System.out.println(UIUtils.getText("message.endOfAllocation"));
		
	}

	private Conference readConference() {
		List<Conference> allConferences = papersManagementService.getConferencesWithPendingAllocation();
		Conference chosen = UIUtils.chooseFromList(allConferences);
		return chosen;
	}

	private void showAllocationLog(Map<Integer, Integer> paper2reviewer) {
		for (Map.Entry<Integer, Integer> entry : paper2reviewer.entrySet()) {
			System.out.print(UIUtils.getText("message.thisPaper") + " " + entry.getKey());
			System.out.println(UIUtils.getText("message.wasAllocedTo") + " " + entry.getValue());
		}
	}

}

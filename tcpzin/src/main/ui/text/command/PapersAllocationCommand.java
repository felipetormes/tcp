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

		
		try {
			Conference conference = readConference();
			Integer numReviewers = UIUtils.readInteger("message.insertNumReviewers");
			System.out.println(UIUtils.getText("message.startingAllocation"));
			paper2reviewer = papersManagementService.allocPapersToReviewers(conference, numReviewers);
		} catch (BusinessDomainException e) {
			System.out.println(e.getMessage());
		}

		showAllocationLog(paper2reviewer);
		System.out.println(UIUtils.getText("message.endOfAllocation"));

	}

	private Conference readConference() throws BusinessDomainException {
		Conference chosen = null;

		List<Conference> allConferences = papersManagementService.getConferencesWithPendingAllocation();
		if (allConferences != null) {
			chosen = UIUtils.chooseFromList(allConferences);
		} else {
			throw new BusinessDomainException((UIUtils.getText("exception.business.domain.pendigConferences")));
		}

		return chosen;
	}

	private void showAllocationLog(Map<Integer, Integer> paper2reviewer) {
		for (Map.Entry<Integer, Integer> entry : paper2reviewer.entrySet()) {
			System.out.print(UIUtils.getText("message.thisPaper") + " " + entry.getKey());
			System.out.println(UIUtils.getText("message.wasAllocedTo") + " " + entry.getValue());
		}
	}

}

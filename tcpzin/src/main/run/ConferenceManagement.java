package main.run;

import main.business.PapersManagementService;
import main.business.impl.PapersManagementServiceImpl;
import main.data.Database;
import main.exceptions.CommandTextException;
import main.ui.ConferenceUI;
import main.ui.text.ConferenceTextInterface;

public class ConferenceManagement {
	public static void main(String[] args) throws CommandTextException {
		Database database = new Database();
		PapersManagementService paperManagement = new PapersManagementServiceImpl(database);
		ConferenceUI conferenceUI = new ConferenceTextInterface(paperManagement);
		conferenceUI.showUI();
		
	}
}

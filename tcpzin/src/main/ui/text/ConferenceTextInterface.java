package main.ui.text;

import java.util.HashMap;

import main.business.PapersManagementService;
import main.exceptions.BusinessException;
import main.exceptions.CommandTextException;
import main.ui.ConferenceUI;
import main.ui.text.command.ConferenceUICommand;
import main.ui.text.command.PapersAllocationCommand;
import main.ui.text.command.PapersGradesAttributionCommand;
import main.ui.text.command.PapersSelectionCommand;

public class ConferenceTextInterface extends ConferenceUI {

	public static String EXIT_CODE = "E";
	private String option;
	private PapersManagementService papersManagementService;

	public ConferenceTextInterface(
			PapersManagementService papersManagementService) {
		this.papersManagementService = papersManagementService;
		commandMap = new HashMap<String, ConferenceUICommand>();
	}

	public String showMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append(UIUtils.getText("message.options")).append(":\n");
		sb.append(UIUtils.getText("message.options.allocation")).append("\n");
		sb.append(UIUtils.getText("message.options.attribution")).append("\n");
		sb.append(UIUtils.getText("message.options.selection")).append("\n");

		return sb.toString();

	}

	@Override
	public void addCommand(String key, ConferenceUICommand command) {
		super.addCommand(key, command);
	}

	@Override
	public void showUI() throws CommandTextException {
		addCommand("A", new PapersAllocationCommand(papersManagementService));
		addCommand("T", new PapersGradesAttributionCommand(papersManagementService));
		addCommand("S", new PapersSelectionCommand(papersManagementService));
		
		do {
			System.out.println(showMenu());
			option = UIUtils.readString(UIUtils.getText("message.choose.option")).toUpperCase();
			try {
				ConferenceUICommand c = commandMap.get(option);
				if (c != null) {
					c.execute();
				}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				System.out.println(UIUtils.getText(e.getMessage()));
			}
		} while (!EXIT_CODE.equals(option));
	}
}

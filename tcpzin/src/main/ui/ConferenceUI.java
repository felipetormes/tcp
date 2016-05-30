package main.ui;



import java.util.Map;

import main.ui.text.command.*;

public abstract class ConferenceUI {

	private Map<String,ConferenceUICommand> commandMap;

	private ConferenceUICommand conferenceUICommand;

	public void showUI() {

	}

	public void addCommand(String key, ConferenceUICommand command) {

	}

}

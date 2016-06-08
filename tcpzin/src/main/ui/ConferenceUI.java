package main.ui;



import java.util.Map;

import main.exceptions.CommandTextException;
import main.ui.text.command.ConferenceUICommand;

public abstract class ConferenceUI {

	public Map<String,ConferenceUICommand> commandMap;

	public void showUI() throws CommandTextException {
		
	}

	public void addCommand(String key, ConferenceUICommand command) {
		commandMap.put(key, command);
	}

}

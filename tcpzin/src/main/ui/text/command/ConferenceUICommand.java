package main.ui.text.command;

import main.exceptions.BusinessServiceException;

public interface ConferenceUICommand {

	public abstract void execute() throws BusinessServiceException;

}

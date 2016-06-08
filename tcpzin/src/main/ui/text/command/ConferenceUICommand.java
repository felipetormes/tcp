package main.ui.text.command;

import main.exceptions.BusinessDomainException;
import main.exceptions.BusinessServiceException;
import main.exceptions.CommandTextException;

public interface ConferenceUICommand {

	public abstract void execute() throws BusinessServiceException, BusinessDomainException, CommandTextException;

}

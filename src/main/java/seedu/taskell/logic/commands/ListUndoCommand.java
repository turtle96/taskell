/** @@author A0142130A **/
package seedu.taskell.logic.commands;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.events.model.DisplayListChangedEvent;

/** Lists a list of previous commands available for Undo operation
 * */
public class ListUndoCommand extends Command {
    
    public static final String COMMAND_WORD = "list-undo";

    public static final String MESSAGE_SUCCESS = "Listed all commands available for undo.";
    
    private static ListUndoCommand self;
    
    public static ListUndoCommand getInstance() {
    	if (self == null) {
    		self = new ListUndoCommand();
    	}
    	
    	return self;
    }

    @Override
    public CommandResult execute() {
        indicateDisplayListChanged();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public void indicateDisplayListChanged() {
        EventsCenter.getInstance().post(
                new DisplayListChangedEvent(UndoCommand.getListOfCommandHistoryText()));
    }

}

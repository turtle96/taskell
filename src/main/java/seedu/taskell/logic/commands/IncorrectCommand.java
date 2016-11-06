package seedu.taskell.logic.commands;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.events.undo.ExecutedIncorrectCommandEvent;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    private static boolean isUndoableCommand;
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser){
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        indicateAttemptToExecuteIncorrectCommand();
        indicateExecutedIncorrectCommand();
        return new CommandResult(feedbackToUser);
    }

    /** @@author A0142130A **/
    
    /** for setting latest command as an undoable command, so if command is
     *  input wrongly, its history needs to be deleted
     * */
    public static void setIsUndoableCommand(boolean value) {
        isUndoableCommand = value;
    }
    
    public static boolean isUndoableCommand() {
        return isUndoableCommand;
    }
    
    /** if latest command is an undoable command, need to delete its command history
     * */
    private void indicateExecutedIncorrectCommand() {
        EventsCenter.getInstance().post(new ExecutedIncorrectCommandEvent(isUndoableCommand));
    }

    /** @@author **/
}


package seedu.taskell.logic.commands;


/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    private static boolean isAddEditDeleteCommand;
    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser){
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        indicateAttemptToExecuteIncorrectCommand();
        return new CommandResult(feedbackToUser);
    }
    
    public void setIsUndoableCommand(boolean value) {
        isAddEditDeleteCommand = value;
    }
    
    public boolean isUndoableCommand() {
        return isAddEditDeleteCommand;
    }

}


package seedu.taskell.logic.commands;

import java.util.ArrayList;

/**
 * Undo most recent executed command
 */
public class UndoCommand extends Command {
    
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo most recent command.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n";

    private static ArrayList<Command> commandHistory;
    
    public UndoCommand() {
        
    }
    
    @Override
    public CommandResult execute() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void initializeCommandHistory() {
        if (commandHistory==null) {
            commandHistory = new ArrayList<>();
        }
    }

}

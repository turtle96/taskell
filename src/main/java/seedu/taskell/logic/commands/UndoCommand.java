package seedu.taskell.logic.commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.model.CommandHistory;
import seedu.taskell.model.task.ReadOnlyTask;
import seedu.taskell.model.task.Task;
import seedu.taskell.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.taskell.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * Undo most recent executed command (add or delete only for now)
 */
public class UndoCommand extends Command {
    private static final Logger logger = LogsCenter.getLogger(UndoCommand.class.getName());
    
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo most recent command.\n"
            + "Example: " + COMMAND_WORD;
    
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Task added back: %1$s";
    
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";
    public static final String MESSAGE_NO_TASK_TO_UNDO = "No add or delete commands available to undo.";

    private static ArrayList<CommandHistory> commandHistory;
    
    private static String mostRecentCommand;
    private static Task mostRecentAddedTask;
    private static Task mostRecentDeletedTask;
    
    public UndoCommand() {
        
    }
    
    @Override
    public CommandResult execute() {
        if (mostRecentCommand==null) {
            return new CommandResult(String.format(MESSAGE_NO_TASK_TO_UNDO));
        }
        switch (mostRecentCommand) {
        case AddCommand.COMMAND_WORD:
            return undoAdd();
        case DeleteCommand.COMMAND_WORD:
            return undoDelete();
        default:
            return new CommandResult(String.format(MESSAGE_NO_TASK_TO_UNDO));
        }
    }

    private CommandResult undoDelete() {
        try {
            model.addTask(mostRecentDeletedTask);
            return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, mostRecentDeletedTask));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }

    private CommandResult undoAdd() {
        try {
            model.deleteTask(mostRecentAddedTask);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, mostRecentAddedTask));
    }
    
    public static void updateMostRecentCommand(String commandWord) {
        mostRecentCommand = commandWord;
    }
    
    public static void updateMostRecentAddedTask(Task task) {
        mostRecentAddedTask = task;
    }
    
    public static void updateMostRecentDeletedTask(ReadOnlyTask task) {
        mostRecentDeletedTask = (Task) task;
    }
    
    public static void initializeCommandHistory() {
        if (commandHistory==null) {
            commandHistory = new ArrayList<>();
        }
    }
    
    public static void addCommandToHistory(String commandText, 
            String commandType, Task task) {
        commandHistory.add(new CommandHistory(commandText, commandType, task));
    }

}

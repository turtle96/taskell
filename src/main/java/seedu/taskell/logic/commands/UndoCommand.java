package seedu.taskell.logic.commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.model.CommandHistory;
import seedu.taskell.model.task.Task;
import seedu.taskell.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.taskell.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * Undo previously executed command (add or delete only for now)
 * Note: only for current session only (meaning after app is closed, history will be cleared)
 */
public class UndoCommand extends Command {
    private static final Logger logger = LogsCenter.getLogger(UndoCommand.class.getName());
    
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo a previously executed command.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3";
    
    private static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    private static final String MESSAGE_ADD_TASK_SUCCESS = "Task added back: %1$s";
    
    private static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";
    private static final String MESSAGE_NO_TASK_TO_UNDO = "No add or delete commands available to undo.";
    private static final String MESSAGE_COMMAND_HISTORY_EMPTY = "No command history available for undo.";
    private static final String MESSAGE_INVALID_INDEX = "Index is invalid";
    
    private static ArrayList<CommandHistory> commandHistoryList;
    
    private int index;
    private CommandHistory commandHistory;
    
    public UndoCommand(int index) {
        logger.info("Creating UndoCommand with index: " + index);
        this.index = index;
    }
    
    public static ArrayList<String> getListOfCommandHistoryText() {
        assert commandHistoryList != null;
        assert !commandHistoryList.isEmpty();
        
        ArrayList<String> list = new ArrayList<>();
        for (CommandHistory history: commandHistoryList) {
            list.add(history.getCommandText());
        }
        
        return list;
    }
    
    @Override
    public CommandResult execute() {
        
        if (commandHistoryList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_COMMAND_HISTORY_EMPTY));
        }
        else if (index > commandHistoryList.size()) {
            return new CommandResult(String.format(MESSAGE_INVALID_INDEX));
        }
        
        commandHistory = commandHistoryList.get(getOffset(index));
        
        switch (commandHistory.getCommandType()) {
        case AddCommand.COMMAND_WORD:
            return undoAdd();
        case DeleteCommand.COMMAND_WORD:
            return undoDelete();
        default:
            return new CommandResult(String.format(MESSAGE_NO_TASK_TO_UNDO));
        }
    }
    
    private static int getOffset(int index) {
        return index - 1;
    }

    private CommandResult undoDelete() {
        try {
            model.addTask(commandHistory.getTask());
            deleteCommandHistory();
            return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }

    private CommandResult undoAdd() {
        try {
            model.deleteTask(commandHistory.getTask());
            deleteCommandHistory();
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, commandHistory.getTask()));
    }

    private void deleteCommandHistory() {
        commandHistoryList.remove(commandHistory);
    }
    
    public static void initializeCommandHistory() {
        if (commandHistoryList==null) {
            commandHistoryList = new ArrayList<>();
        }
    }
    
    public static void addCommandToHistory(String commandText, 
            String commandType, Task task) {
        assert commandHistoryList != null;
        commandHistoryList.add(new CommandHistory(commandText, commandType, task));
    }
    
    public static void addTaskToCommandHistory(Task task) {
        logger.info("Adding task to history");
        if (commandHistoryList.isEmpty()) {
            logger.warning("No command history to add task to");
            return;
        }
        
        commandHistoryList.get(getOffset(commandHistoryList.size())).setTask(task);
    }

    public static void deletePreviousCommand() {
        logger.info("Command unsuccessfully executed. Deleting command history.");
        if (commandHistoryList.isEmpty()) {
            logger.warning("No command history to delete");
            return;
        }
        commandHistoryList.remove(getOffset(commandHistoryList.size()));
    }

}

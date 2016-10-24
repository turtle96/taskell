/** @@author A0142130A **/
package seedu.taskell.logic.commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.model.DisplayListChangedEvent;
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
    private static final String MESSAGE_EDIT_TASK_SUCCESS = "Task edited back to old version: %1$s";
    
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
        
        if (commandHistory.isRedoTrue()) {
            return redoUndo();
        }
        
        switch (commandHistory.getCommandType()) {
        case AddCommand.COMMAND_WORD:
            return undoAdd();
        case DeleteCommand.COMMAND_WORD:
            return undoDelete();
        case EditDateCommand.COMMAND_WORD: 
            return undoEdit();
        case EditDescriptionCommand.COMMAND_WORD:
            return undoEdit();
        case EditEndTimeCommand.COMMAND_WORD:
            return undoEdit();
        case EditPriorityCommand.COMMAND_WORD:
            return undoEdit();
        case EditStartTimeCommand.COMMAND_WORD:
            return undoEdit();
        default:
            logger.severe("CommandHistory is invalid");
            return new CommandResult(String.format(MESSAGE_NO_TASK_TO_UNDO));
        }
    }

    private CommandResult redoUndo() {
        switch (commandHistory.getCommandType()) {
        case AddCommand.COMMAND_WORD:
            return undoDelete();
        case DeleteCommand.COMMAND_WORD:
            return undoAdd();
        case EditDateCommand.COMMAND_WORD:
            return redoEdit();
        case EditDescriptionCommand.COMMAND_WORD:
            return redoEdit();
        case EditEndTimeCommand.COMMAND_WORD:
            return redoEdit();
        case EditPriorityCommand.COMMAND_WORD:
            return redoEdit();
        case EditStartTimeCommand.COMMAND_WORD:
            return redoEdit();
        default:
            logger.severe("CommandHistory is invalid");
            return new CommandResult(String.format(MESSAGE_NO_TASK_TO_UNDO));
        }
    }

    private CommandResult undoEdit() {
        try {
            model.editTask(commandHistory.getTask(), commandHistory.getOldTask());
            deleteCommandHistory();
            addUndoCommand(commandHistory);
            indicateDisplayListChanged();
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, commandHistory.getOldTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        
        assert false: "Undo edit should return a command result";
        return null;
    }
    
    private CommandResult redoEdit() {
        try {
            model.editTask(commandHistory.getOldTask(), commandHistory.getTask());
            deleteCommandHistory();
            indicateDisplayListChanged();
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        
        assert false: "Redo edit should return a command result";
        return null;
    }

    private CommandResult undoDelete() {
        try {
            model.addTask(commandHistory.getTask());
            deleteCommandHistory();
            addUndoCommand(commandHistory);
            indicateDisplayListChanged();
            return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }

    private CommandResult undoAdd() {
        try {
            model.deleteTask(commandHistory.getTask());
            deleteCommandHistory();
            addUndoCommand(commandHistory);
            indicateDisplayListChanged();
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, commandHistory.getTask()));
    }

    private void deleteCommandHistory() {
        commandHistoryList.remove(commandHistory);
    }
    
    private void addUndoCommand(CommandHistory commandHistory) {
        if (commandHistory.isRedoTrue()) {
            return;
        }
        commandHistory.setCommandText("undo " + commandHistory.getCommandText());
        commandHistory.setToRedoToTrue();
        commandHistoryList.add(commandHistory);
    }
    
    /******** static methods *********/
    
    public static void initializeCommandHistory() {
        if (commandHistoryList==null) {
            commandHistoryList = new ArrayList<>();
        }
    }
    
    public static void clearCommandHistory() {
        commandHistoryList.clear();
    }
    
    private static int getOffset(int index) {
        return index - 1;
    }
    
    public static void addCommandToHistory(String commandText, 
            String commandType) {
        assert commandHistoryList != null;
        commandHistoryList.add(new CommandHistory(commandText, commandType));
    }
    
    public static void addTaskToCommandHistory(Task task) {
        logger.info("Adding task to history");
        if (commandHistoryList.isEmpty()) {
            logger.warning("No command history to add task to");
            return;
        }
        
        commandHistoryList.get(getOffset(commandHistoryList.size())).setTask(task);
    }
    
    public static void addOldTaskToCommandHistory(Task task) {
        logger.info("Adding old task to history");
        if (commandHistoryList.isEmpty()) {
            logger.warning("No command history to add task to");
            return;
        }
        
        commandHistoryList.get(getOffset(commandHistoryList.size())).setOldTask(task);
    }

    public static void deletePreviousCommand() {
        logger.info("Command unsuccessfully executed. Deleting command history.");
        if (commandHistoryList.isEmpty()) {
            logger.warning("No command history to delete");
            return;
        }
        commandHistoryList.remove(getOffset(commandHistoryList.size()));
    }

    /****** Event ******/
    public void indicateDisplayListChanged() {
        EventsCenter.getInstance().post(
                new DisplayListChangedEvent(getListOfCommandHistoryText()));
    }
}

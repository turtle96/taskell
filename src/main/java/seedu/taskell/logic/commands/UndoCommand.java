/** @@author A0142130A **/
package seedu.taskell.logic.commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.ui.DisplayListChangedEvent;
import seedu.taskell.model.CommandHistory;
import seedu.taskell.model.History;
import seedu.taskell.model.HistoryManager;
import seedu.taskell.model.Model;
import seedu.taskell.model.task.Task;
import seedu.taskell.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.taskell.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * Undo previously executed commands (add, delete, edit, done, undone)
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

    private static final String MESSAGE_TASK_NOT_FOUND = "Task is not present in Taskell.";
    
    private ArrayList<CommandHistory> commandHistoryList;
    private History history;
    
    private int index;
    private CommandHistory commandHistory;
    
    public UndoCommand(int index) {
        logger.info("Creating UndoCommand with index: " + index);
        
        history = HistoryManager.getInstance();
        commandHistoryList = history.getList();
        this.index = index;
    }

    public UndoCommand() {
        logger.info("Creating UndoCommand without index");
        
        history = HistoryManager.getInstance();
        commandHistoryList = history.getList();
        this.index = commandHistoryList.size(); //offset will be done in execute()
    }

    @Override
    public CommandResult execute() {
        
        if (commandHistoryList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_COMMAND_HISTORY_EMPTY));
        } else if (index > commandHistoryList.size()) {
            return new CommandResult(String.format(MESSAGE_INVALID_INDEX));
        }
        
        commandHistory = commandHistoryList.get(getOffset(index));
        
        if (commandHistory.isRedoTrue()) {
            return redoUndo();
        }
        
        String commandType = commandHistory.getCommandType();
        if (commandType.equals(AddCommand.COMMAND_WORD)) {
            return executeDelete();
        } else if (commandType.equals(DeleteCommand.COMMAND_WORD)) {
            return executeAdd();
        } else if (commandType.equals(EditCommand.COMMAND_WORD)) {
            return undoEdit();
        } else if (commandType.equals(DoneCommand.COMMAND_WORD)) {
            return executeUndone();
        } else if (commandType.equals(UndoneCommand.COMMAND_WORD)) {
            return executeDone();
        } else {
            logger.severe("CommandHistory is invalid");
            return new CommandResult(String.format(MESSAGE_NO_TASK_TO_UNDO));
        }
        
    }

    private CommandResult redoUndo() {
        
        String commandType = commandHistory.getCommandType();
        if (commandType.equals(AddCommand.COMMAND_WORD)) {
            return executeAdd();
        } else if (commandType.equals(DeleteCommand.COMMAND_WORD)) {
            return executeDelete();
        } else if (commandType.equals(EditCommand.COMMAND_WORD)) {
            return redoEdit();
        } else if (commandType.equals(DoneCommand.COMMAND_WORD)) {
            return executeDone();
        } else if (commandType.equals(UndoneCommand.COMMAND_WORD)) {
            return executeUndone();
        } else {
            logger.severe("CommandHistory is invalid");
            return new CommandResult(String.format(MESSAGE_NO_TASK_TO_UNDO));
        }
        
    }
    
    private CommandResult executeUndone() {
        try {
            model.editTask(commandHistory.getTask(), commandHistory.getOldTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            indicateDisplayListChanged();
            return new CommandResult(String.format(UndoneCommand.MESSAGE_UNDONE_TASK_SUCCESS, 
                    commandHistory.getOldTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
            return new CommandResult(UndoneCommand.MESSAGE_UNDONE_UNSUCCESSFUL);
        }
        
    }
    
    private CommandResult executeDone() {
        try {
            model.editTask(commandHistory.getOldTask(), commandHistory.getTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            indicateDisplayListChanged();
            return new CommandResult(String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, 
                    commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }

    private CommandResult undoEdit() {
        try {
            model.editTask(commandHistory.getTask(), commandHistory.getOldTask());
            history.deleteCommandHistory(commandHistory);
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
            history.deleteCommandHistory(commandHistory);
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

    private CommandResult executeAdd() {
        try {
            model.addTask(commandHistory.getTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            indicateDisplayListChanged();
            return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }

    private CommandResult executeDelete() {
        try {
            model.deleteTask(commandHistory.getTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            indicateDisplayListChanged();
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, commandHistory.getTask()));
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
    
    private static int getOffset(int index) {
        return index - 1;
    }

    /****** Event ******/
    public void indicateDisplayListChanged() {
        EventsCenter.getInstance().post(
                new DisplayListChangedEvent(history.getListCommandText()));
    }
}

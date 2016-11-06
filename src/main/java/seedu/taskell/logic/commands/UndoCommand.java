/** @@author A0142130A **/
package seedu.taskell.logic.commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.ui.DisplayListChangedEvent;
import seedu.taskell.history.CommandHistory;
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
    
    private static final String MESSAGE_NO_COMMAND_TO_UNDO = "No commands available to undo.";
    public static final String MESSAGE_COMMAND_HISTORY_EMPTY = "No command history available for undo.";
    private static final String MESSAGE_INVALID_INDEX = "Index is invalid";

    public static final String MESSAGE_DONE_TASK_UNSUCCESSFUL = "The task status is already completed.";
    
    private ArrayList<CommandHistory> commandHistoryList;
    
    private int index;
    private CommandHistory commandHistory;
    
    public UndoCommand(int index) {
        logger.info("Creating UndoCommand with index: " + index);
        
        commandHistoryList = history.getList();
        this.index = index;
    }

    public UndoCommand() {
        logger.info("Creating UndoCommand without index");
        
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
        } else {
            return executeUndo();
        }
              
    }

    /** determine type of command of history and performs necessary undo changes
     * */
    private CommandResult executeUndo() {
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
            return new CommandResult(String.format(MESSAGE_NO_COMMAND_TO_UNDO));
        }
    }

    /** must be a previous undo command
     *  determine type of command of history and performs necessary redo changes
     * */
    private CommandResult redoUndo() {
        
        assert commandHistory.isRedoTrue();
        
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
            return new CommandResult(String.format(MESSAGE_NO_COMMAND_TO_UNDO));
        }
        
    }
    
    /** executes undone command on command history
     * */
    private CommandResult executeUndone() {
        try {
            model.editTask(commandHistory.getTask(), commandHistory.getOldTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            
            return new CommandResult(String.format(UndoneCommand.MESSAGE_UNDONE_TASK_SUCCESS, 
                    commandHistory.getOldTask()));
        } catch (DuplicateTaskException e) {
            history.deleteCommandHistory(commandHistory);
            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
            history.deleteCommandHistory(commandHistory);
            return new CommandResult(UndoneCommand.MESSAGE_UNDONE_UNSUCCESSFUL);
        }
        
    }
    
    /** executes done command on command history
     * */
    private CommandResult executeDone() {
        try {
            model.editTask(commandHistory.getOldTask(), commandHistory.getTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            
            return new CommandResult(String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, 
                    commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            history.deleteCommandHistory(commandHistory);
            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
            history.deleteCommandHistory(commandHistory);
            return new CommandResult(MESSAGE_DONE_TASK_UNSUCCESSFUL);
        }
    }

    /** executes edit command on command history, edit back to old task
     * */
    private CommandResult undoEdit() {
        try {
            model.editTask(commandHistory.getTask(), commandHistory.getOldTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, commandHistory.getOldTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        
        assert false: "Undo edit should return a command result";
        return null;
    }
    
    /** executes edit command on command history, edit back to new task
     *  i.e. old task -> new task -> old task
     * */
    private CommandResult redoEdit() {
        try {
            model.editTask(commandHistory.getOldTask(), commandHistory.getTask());
            history.deleteCommandHistory(commandHistory);
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_TASK);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        
        assert false: "Redo edit should return a command result";
        return null;
    }

    /** executes add command on command history
     * */
    private CommandResult executeAdd() {
        try {
            model.addTask(commandHistory.getTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
            return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, commandHistory.getTask()));
        } catch (DuplicateTaskException e) {
            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_TASK);
        }
    }

    /** executes delete command on command history
     * */
    private CommandResult executeDelete() {
        try {
            model.deleteTask(commandHistory.getTask());
            history.deleteCommandHistory(commandHistory);
            addUndoCommand(commandHistory);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, commandHistory.getTask()));
    }
    
    /** appends "undo " to front of command input string and saves as an undo command
     *  can execute redo
     * */
    private void addUndoCommand(CommandHistory commandHistory) {
        if (commandHistory.isRedoTrue()) {
            return;
        }
        commandHistory.setCommandText("undo " + commandHistory.getCommandText());
        commandHistory.setToRedoToTrue();
        commandHistoryList.add(commandHistory);
    }
    
    /******** static methods *********/
    
    /** convert 1-based index to support 0-based index within system
     * */
    private static int getOffset(int index) {
        return index - 1;
    }

    /****** Event ******/
    public void indicateDisplayListChanged() {
        EventsCenter.getInstance().post(
                new DisplayListChangedEvent(history.getListCommandText()));
    }
}

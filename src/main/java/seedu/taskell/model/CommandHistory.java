/** @@author A0142130A **/
package seedu.taskell.model;

import seedu.taskell.logic.commands.DeleteCommand;
import seedu.taskell.logic.commands.DoneCommand;
import seedu.taskell.logic.commands.UndoneCommand;
import seedu.taskell.model.task.Task;

/** Stores the relevant details of a command so it can be undone via UndoCommand
 *  each Add/Delete/Edit Command executed should correspond to a CommandHistory 
 *  stored in UndoCommand's list of CommandHistory
 * */
public class CommandHistory {
    private String commandText, commandType;
    private Task task;      //relevent task to be added, deleted or edited
    private Task oldTask;   //oldTask needed for EditCommands
    private boolean isRedoCommand;
    
    public CommandHistory() {
        commandText = "default command text";
        commandType = "default command type";
        task = null;
        isRedoCommand = false;
    }
    
    public CommandHistory(String commandText, String commandType) {
        assert commandText != null;
        assert commandType != null;
        assert !commandText.trim().isEmpty();
        assert !commandType.trim().isEmpty();
        
        this.commandText = commandText.trim();
        this.commandType = commandType.trim();
        this.task = null;
        isRedoCommand = false;
    }
    
    public String getCommandText() {
        return commandText;
    }
    
    public String getCommandType() {
        return commandType;
    }
    
    public Task getTask() {
        return task;
    }
    
    public Task getOldTask() {
        return oldTask;
    }
    
    public boolean isRedoTrue() {
        return isRedoCommand;
    }
    
    /**** Setter methods ****/
    
    public void setCommandText(String text) {
        this.commandText = text;
    }
    
    public void setTask(Task task) {
        assert task != null;
        
        this.task = task;
        
        //edits text form to show task description and other parameters
        if (commandType.equals(DeleteCommand.COMMAND_WORD)) {
            commandText = "delete " + this.task.getAsText();
        } else if (commandType.equals(DoneCommand.COMMAND_WORD)) {
            commandText = "done " + this.task.getAsText();
        } else if (commandType.equals(UndoneCommand.COMMAND_WORD)) {
            commandText = "undone " + this.task.getAsText();
        }
    }
    
    public void setOldTask(Task task) {
        this.oldTask = task;
    }
    
    public void setToRedoToTrue() {
        isRedoCommand = true;
    }
    
}

/** @@author A0142130A **/
package seedu.taskell.model;

import seedu.taskell.model.task.Task;

/** Stores the relevant details of a command so it can be undone via UndoCommand
 *  each Add/Delete/Edit Command executed should correspond to a CommandHistory 
 *  stored in UndoCommand's list of CommandHistory
 * */
public class CommandHistory {
    private String commandText, commandType;
    private Task task;  //relevent task to be added, deleted or edited
    private boolean toRedo;
    
    public CommandHistory() {
        commandText = "default command text";
        commandType = "default command type";
        task = null;
    }
    
    public CommandHistory(String commandText, String commandType, Task task) {
        assert commandText != null;
        assert commandType != null;
        assert !commandText.trim().isEmpty();
        assert !commandType.trim().isEmpty();
        
        this.commandText = commandText.trim();
        this.commandType = commandType.trim();
        this.task = task;
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
    
    public boolean isRedoTrue() {
        return toRedo;
    }
    
    public void setCommandText(String text) {
        this.commandText = text;
    }
    
    public void setTask(Task task) {
        assert task != null;
        this.task = task;
    }
    
    public void setToRedoToTrue() {
        toRedo = true;
    }
    
}

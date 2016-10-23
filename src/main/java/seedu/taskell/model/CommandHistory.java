package seedu.taskell.model;

import seedu.taskell.model.task.Task;

/** Stores the relevant details of a command so it can be undone via UndoCommand
 * */
public class CommandHistory {
    private final String commandText, commandType;
    private final Task task;  //relevent task to be added, deleted or edited
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
        
        this.commandText = commandText;
        this.commandType = commandType;
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
    
    public void setToRedoToTrue() {
        toRedo = true;
    }
}

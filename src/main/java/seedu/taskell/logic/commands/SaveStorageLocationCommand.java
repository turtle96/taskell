package seedu.taskell.logic.commands;

import seedu.taskell.commons.events.model.TaskManagerChangedEvent;
import seedu.taskell.model.ReadOnlyTaskManager;
import seedu.taskell.model.task.ReadOnlyTask;

public class SaveStorageLocationCommand extends Command {
    
    private String newStorageFilePath;
    private ReadOnlyTaskManager taskManager;
    
    public SaveStorageLocationCommand(String newStorageFilePath) {
        this.newStorageFilePath = newStorageFilePath;
    }

    @Override
    public CommandResult execute() {
        taskManager = model.getTaskManager();
        indicateTaskManagerChanged();
        return null;
    }
    
    /** Raises an event to indicate the model has changed */
    private void indicateTaskManagerChanged() {
        raise(new TaskManagerChangedEvent(taskManager));
    }

}

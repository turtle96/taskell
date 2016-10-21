package seedu.taskell.logic.commands;

import java.util.logging.Logger;

import seedu.taskell.commons.core.Config;
import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.model.TaskManagerChangedEvent;
import seedu.taskell.commons.events.storage.StorageLocationChangedEvent;
import seedu.taskell.model.ReadOnlyTaskManager;

/** Saves current data file to new filepath.
 * */

public class SaveStorageLocationCommand extends Command {
    
    private Logger logger = LogsCenter.getLogger(SaveStorageLocationCommand.class.getName());
    
    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves data file to new location specified.\n"
            + "Parameters: FILEPATH (must be valid and already present on computer)\n"
            + "Example: " + COMMAND_WORD + "C:/Users/chicken/Desktop/cat";
    
    public static final String MESSAGE_SUCCESS = "Data successfully saved to new location.";
    
    private static Config config;
    private String newStorageFilePath;
    private ReadOnlyTaskManager taskManager;
    
    public SaveStorageLocationCommand(String newStorageFilePath) {
        this.newStorageFilePath = newStorageFilePath.trim().replace("\\", "/") + "/taskmanager.xml";
        logger.severe("New file path" + newStorageFilePath);
    }
    
    public static void setConfig(Config c) {
        config = c;
    }

    @Override
    public CommandResult execute() {
        assert config != null;
        
        taskManager = model.getTaskManager();
        config.setTaskManagerFilePath(newStorageFilePath);
        EventsCenter.getInstance().post(new StorageLocationChangedEvent(config));
        
        indicateTaskManagerChanged();
        return new CommandResult(MESSAGE_SUCCESS);
    }
    
    /** Raises an event to indicate the model has changed */
    private void indicateTaskManagerChanged() {
        EventsCenter.getInstance().post(new TaskManagerChangedEvent(taskManager));
    }

}

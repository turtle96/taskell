package seedu.taskell.logic.commands;

import java.io.IOException;
import java.util.logging.Logger;

import seedu.taskell.commons.core.Config;
import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.model.TaskManagerChangedEvent;
import seedu.taskell.commons.events.storage.StorageLocationChangedEvent;
import seedu.taskell.model.ReadOnlyTaskManager;
import seedu.taskell.storage.JsonConfigStorage;

/** Saves current data file to new filepath.
 * */

public class SaveStorageLocationCommand extends Command {
    
    private Logger logger = LogsCenter.getLogger(SaveStorageLocationCommand.class.getName());
    
    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saves data file to new location specified.\n"
            + "Parameters: FILEPATH (must be valid and already present on computer)\n"
            + "Example: " + COMMAND_WORD + "C:/Users/chicken/Desktop/cat";
    
    private static final String MESSAGE_SUCCESS = "Data successfully saved to new location.";
    private static final String MESSAGE_INVALID_PATH = "Filepath given is invalid.";
    
    private static Config config;
    private String newStorageFilePath;
    private ReadOnlyTaskManager taskManager;
    private static JsonConfigStorage jsonConfigStorage;
    
    public SaveStorageLocationCommand(String newStorageFilePath) {
        this.newStorageFilePath = newStorageFilePath.trim().replace("\\", "/") + "/taskmanager.xml";
        logger.severe("New file path" + newStorageFilePath);
        jsonConfigStorage = new JsonConfigStorage(Config.DEFAULT_CONFIG_FILE);
    }
    
    public static void setConfig(Config c) {
        config = c;
    }

    @Override
    public CommandResult execute() {
        assert config != null;
        assert jsonConfigStorage != null;

        taskManager = model.getTaskManager();
        config.setTaskManagerFilePath(newStorageFilePath);
        indicateStorageLocationChanged();
        
        try {
            indicateTaskManagerChanged();
        } catch (Exception e) {
            return new CommandResult(MESSAGE_INVALID_PATH);
        }
        
        try {
            jsonConfigStorage.saveConfigFile(config);
        } catch (IOException e) {
            logger.severe("save to config json error");
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void indicateStorageLocationChanged() {
        assert config != null;
        EventsCenter.getInstance().post(new StorageLocationChangedEvent(config));
    }
    
    private void indicateTaskManagerChanged() {
        assert taskManager != null;
        EventsCenter.getInstance().post(new TaskManagerChangedEvent(taskManager));
    }

}

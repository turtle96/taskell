package guitests;

import java.util.Optional;
import java.util.logging.Logger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.taskell.TestApp;
import seedu.taskell.commons.core.Config;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.exceptions.DataConversionException;
import seedu.taskell.storage.JsonConfigStorage;

public class SaveStorageLocationCommandTest extends TaskManagerGuiTest {
    
    private static final Logger logger = LogsCenter.getLogger(SaveStorageLocationCommandTest.class);
    
    private static final String CONFIG_JSON = "config.json";
    private static final String CONFIG_LOCATION = "./src/test/data/SaveLocationCommandTest";
    
    private static final String DEFAULT_SAVE_LOCATION = TestApp.SAVE_LOCATION_FOR_TESTING;
    
    @Test
    public void saveToValidFilePath() throws DataConversionException {
        commandBox.runCommand("save cat/");
        assertWriteToJsonSuccess();
    }
    
    @Test
    public void saveToInvalidFilePath() throws DataConversionException {
        JsonConfigStorage jsonConfigStorage = new JsonConfigStorage(CONFIG_LOCATION);

        commandBox.runCommand("save E:");   
        
        Optional<Config> newConfig = jsonConfigStorage.readConfig(CONFIG_JSON);
        
        String newFilePath = newConfig.get().getTaskManagerFilePath();

        logger.severe("New path: " + newFilePath);
        
        assert(newFilePath.equals(DEFAULT_SAVE_LOCATION));
    }
    
    private void assertWriteToJsonSuccess() throws DataConversionException {
        JsonConfigStorage jsonConfigStorage = new JsonConfigStorage(CONFIG_LOCATION);
        Optional<Config> config = jsonConfigStorage.readConfig(CONFIG_JSON);
        assert(config.isPresent());
    } 
    

}

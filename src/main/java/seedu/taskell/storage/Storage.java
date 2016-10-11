package seedu.taskell.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import seedu.taskell.commons.events.model.AddressBookChangedEvent;
import seedu.taskell.commons.events.storage.DataSavingExceptionEvent;
import seedu.taskell.commons.exceptions.DataConversionException;
import seedu.taskell.model.ReadOnlyTaskManager;
import seedu.taskell.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskManagerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getAddressBookFilePath();

    @Override
    Optional<ReadOnlyTaskManager> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyTaskManager addressBook) throws IOException;

    /**
     * Saves the current version of the Address Book to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleAddressBookChangedEvent(AddressBookChangedEvent abce);
}

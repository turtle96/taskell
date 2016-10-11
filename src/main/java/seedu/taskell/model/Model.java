package seedu.taskell.model;

import java.util.Set;

import seedu.taskell.commons.core.UnmodifiableObservableList;
import seedu.taskell.model.task.Task;
import seedu.taskell.model.task.ReadOnlyPerson;
import seedu.taskell.model.task.UniquePersonList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyTaskManager newData);

    /** Returns the TaskManager */
    ReadOnlyTaskManager getAddressBook();

    /** Deletes the given person. */
    void deletePerson(ReadOnlyPerson target) throws UniquePersonList.PersonNotFoundException;

    /** Adds the given person */
    void addPerson(Task task) throws UniquePersonList.DuplicatePersonException;

    /** Returns the filtered person list as an {@code UnmodifiableObservableList<ReadOnlyPerson>} */
    UnmodifiableObservableList<ReadOnlyPerson> getFilteredPersonList();

    /** Updates the filter of the filtered person list to show all persons */
    void updateFilteredListToShowAll();

    /** Updates the filter of the filtered person list to filter by the given keywords*/
    void updateFilteredPersonList(Set<String> keywords);

}

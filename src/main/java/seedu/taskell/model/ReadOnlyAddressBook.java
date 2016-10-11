package seedu.taskell.model;


import java.util.List;

import seedu.taskell.model.person.ReadOnlyPerson;
import seedu.taskell.model.person.UniquePersonList;
import seedu.taskell.model.tag.Tag;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    UniqueTagList getUniqueTagList();

    UniquePersonList getUniquePersonList();

    /**
     * Returns an unmodifiable view of persons list
     */
    List<ReadOnlyPerson> getPersonList();

    /**
     * Returns an unmodifiable view of tags list
     */
    List<Tag> getTagList();

}

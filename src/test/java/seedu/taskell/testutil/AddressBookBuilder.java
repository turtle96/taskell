package seedu.taskell.testutil;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.TaskManager;
import seedu.taskell.model.tag.Tag;
import seedu.taskell.model.task.Person;
import seedu.taskell.model.task.UniquePersonList;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TaskManager ab = new AddressBookBuilder().withPerson("John", "Doe").withTag("Friend").build();}
 */
public class AddressBookBuilder {

    private TaskManager taskManager;

    public AddressBookBuilder(TaskManager taskManager){
        this.taskManager = taskManager;
    }

    public AddressBookBuilder withPerson(Person person) throws UniquePersonList.DuplicatePersonException {
        taskManager.addPerson(person);
        return this;
    }

    public AddressBookBuilder withTag(String tagName) throws IllegalValueException {
        taskManager.addTag(new Tag(tagName));
        return this;
    }

    public TaskManager build(){
        return taskManager;
    }
}

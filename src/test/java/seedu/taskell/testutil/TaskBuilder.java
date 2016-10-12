package seedu.taskell.testutil;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.tag.Tag;
import seedu.taskell.model.task.*;

/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    public TaskBuilder withDescription(String name) throws IllegalValueException {
        this.task.setDescription(new (name));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    public TaskBuilder withDate(String address) throws IllegalValueException {
        this.task.setDate(new TaskDate(address));
        return this;
    }

    public TaskBuilder withTime(String phone) throws IllegalValueException {
        this.task.setTime(new TaskTime(phone));
        return this;
    }

    public TaskBuilder withPriority(String email) throws IllegalValueException {
        this.task.setPriority(new TaskPriority(email));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}

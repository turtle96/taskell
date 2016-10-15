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

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setDescription(new Description(name));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            task.getTags().add(new Tag(tag));
        }
        return this;
    }

    public TaskBuilder withTaskPriority(String taskPriority) throws IllegalValueException {
        this.task.setTaskPriority(new TaskPriority(taskPriority));
        return this;
    }

    public TaskBuilder withTaskDate(String taskDate) throws IllegalValueException {
        this.task.setTaskDate(new TaskDate(taskDate));
        return this;
    }

    public TaskBuilder withTaskTime(String taskTime) throws IllegalValueException {
        this.task.setTaskTime(new TaskTime(taskTime));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}

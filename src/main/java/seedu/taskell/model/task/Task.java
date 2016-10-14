package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Description description;
    private TaskDate taskDate;
    private TaskTime taskTime;
    private TaskPriority taskPriority;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Description description, TaskDate taskDate, TaskTime taskTime, TaskPriority taskPriority, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(description, taskDate, taskTime, taskPriority, tags);
        this.description = description;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.taskPriority = taskPriority;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
<<<<<<< HEAD
        this(source.getDescription(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags());
=======
        this(source.getName(), source.getTaskDate(), source.getEmail(), source.getTaskPriority(), source.getTags());
>>>>>>> e6ab355e1524b899d613fc526a3bf4408cfee8b8
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public TaskDate getTaskDate() {
        return taskDate;
    }

    @Override
    public TaskTime getEmail() {
        return taskTime;
    }

    @Override
    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, taskDate, taskTime, taskPriority, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

package seedu.taskell.testutil;

import seedu.taskell.model.tag.UniqueTagList;
import seedu.taskell.model.task.*;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Description description;
    private TaskPriority taskPriority;
    private TaskTime taskTime;
    private TaskDate taskDate;
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public void setTime(TaskTime taskTime) {
        this.taskTime = taskTime;
    }

    public void setDate(TaskDate taskDate) {
        this.taskDate = taskDate;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public TaskDate getDate() {
        return taskDate;
    }

    @Override
    public TaskTime getTime() {
        return taskTime;
    }

    @Override
    public TaskPriority getPriority() {
        return taskPriority;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getDescription().fullDescription + " ");
        sb.append("p/" + this.getDate().value + " ");
        sb.append("e/" + this.getTime().value + " ");
        sb.append("a/" + this.getPriority().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagDescription + " "));
        return sb.toString();
    }
}

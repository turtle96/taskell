package seedu.taskell.testutil;

import seedu.taskell.model.tag.UniqueTagList;
import seedu.taskell.model.task.*;

/**
 * A mutable person object. For testing only.
 */
public class TestPerson implements ReadOnlyTask {

    private Description description;
    private TaskPriority taskPriority;
    private TaskTime taskTime;
    private TaskDate taskDate;
    private UniqueTagList tags;

    public TestPerson() {
        tags = new UniqueTagList();
    }

    public void setName(Description description) {
        this.description = description;
    }

    public void setAddress(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public void setEmail(TaskTime taskTime) {
        this.taskTime = taskTime;
    }

    public void setPhone(TaskDate taskDate) {
        this.taskDate = taskDate;
    }

    @Override
    public Description getName() {
        return description;
    }

    @Override
    public TaskDate getPhone() {
        return taskDate;
    }

    @Override
    public TaskTime getEmail() {
        return taskTime;
    }

    @Override
    public TaskPriority getAddress() {
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
        sb.append("add " + this.getName().fullName + " ");
        sb.append("p/" + this.getPhone().value + " ");
        sb.append("e/" + this.getEmail().value + " ");
        sb.append("a/" + this.getAddress().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}

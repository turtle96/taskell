package seedu.taskell.testutil;

import seedu.taskell.model.task.*;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

<<<<<<< HEAD
    private Description description;
    private Address address;
=======
    private Name name;
>>>>>>> remove-address-from-task
    private Email email;
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setDescription(Description description) {
        this.description= description;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public Email getEmail() {
        return email;
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
        sb.append("add " + this.getDescription().description+ " ");
        sb.append("e/" + this.getEmail().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}

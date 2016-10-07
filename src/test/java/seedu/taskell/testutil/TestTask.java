package seedu.taskell.testutil;

import seedu.taskell.model.task.*;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

<<<<<<< HEAD
    private Description description;
    private Email email;
=======
    private Name name;
    private Address address;
    
    private Phone phone;
>>>>>>> remove-email-from-task
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setDescription(Description description) {
        this.description= description;
    }

    

    @Override
    public Description getDescription() {
        return description;
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
<<<<<<< HEAD
        sb.append("add " + this.getDescription().description+ " ");
        sb.append("e/" + this.getEmail().value + " ");
=======
        sb.append("add " + this.getName().fullName + " ");
        sb.append("p/" + this.getPhone().value + " ");
        
        sb.append("a/" + this.getAddress().value + " ");
>>>>>>> remove-email-from-task
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}

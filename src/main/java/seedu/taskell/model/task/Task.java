package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

<<<<<<< HEAD
    private Description description;
    private Email email;
=======
    private Name name;
    private Phone phone;
    
    private Address address;
>>>>>>> remove-email-from-task

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
<<<<<<< HEAD
    public Task(Description description, Email email, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(description, email, tags);
        this.description= description;
        this.email = email;
=======
    public Task(Name name, Phone phone, Address address, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, phone, address, tags);
        this.name = name;
        this.phone = phone;
        
        this.address = address;
>>>>>>> remove-email-from-task
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
<<<<<<< HEAD
        this(source.getDescription(), source.getEmail(), source.getTags());
=======
        this(source.getName(), source.getPhone(), source.getAddress(), source.getTags());
>>>>>>> remove-email-from-task
    }

    @Override
    public Description getDescription() {
        return description;
    }

    

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
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
<<<<<<< HEAD
        return Objects.hash(description, email, tags);
=======
        return Objects.hash(name, phone, address, tags);
>>>>>>> remove-email-from-task
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

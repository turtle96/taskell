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
    private Email email;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
<<<<<<< HEAD
    public Task(Description description, Email email, Address address, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(description, email, address, tags);
        this.description= description;
=======
    public Task(Name name, Phone phone, Email email, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
>>>>>>> remove-address-from-task
        this.email = email;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
<<<<<<< HEAD
        this(source.getDescription(), source.getEmail(), source.getAddress(), source.getTags());
=======
        this(source.getName(), source.getPhone(), source.getEmail(), source.getTags());
>>>>>>> remove-address-from-task
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
        return Objects.hash(description, email, address, tags);
=======
        return Objects.hash(name, phone, email, tags);
>>>>>>> remove-address-from-task
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

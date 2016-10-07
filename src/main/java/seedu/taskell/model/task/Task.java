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
    private Phone phone;
    private Email email;
    private Address address;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Description description, Phone phone, Email email, Address address, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(description, phone, email, address, tags);
        this.description= description;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getDescription(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags());
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public Phone getPhone() {
        return phone;
    }

    @Override
    public Email getEmail() {
        return email;
    }

    @Override
    public Address getAddress() {
        return address;
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
        return Objects.hash(description, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

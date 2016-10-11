package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Represents a Person in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson {

    private Description description;
    private Phone phone;
    private Email email;
    private TaskPriority taskPriority;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Person(Description description, Phone phone, Email email, TaskPriority taskPriority, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(description, phone, email, taskPriority, tags);
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.taskPriority = taskPriority;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Person(ReadOnlyPerson source) {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags());
    }

    @Override
    public Description getName() {
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
    public TaskPriority getAddress() {
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
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, phone, email, taskPriority, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}

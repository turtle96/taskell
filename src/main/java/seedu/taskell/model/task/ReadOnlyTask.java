package seedu.taskell.model.task;

import seedu.taskell.model.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Task in the taskmanager.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {
    Description getDescription();
    TaskDate getTaskDate();
    TaskTime getEmail();
    TaskPriority getTaskPriority();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the task's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getDescription().equals(this.getDescription()) // state checks here onwards
                && other.getTaskDate().equals(this.getTaskDate())
                && other.getEmail().equals(this.getEmail())
                && other.getTaskPriority().equals(this.getTaskPriority()));
    }

    /**
     * Formats the task as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" TaskDate: ")
                .append(getTaskDate())
                .append(" TaskTime: ")
                .append(getEmail())
                .append(" TaskPriority: ")
                .append(getTaskPriority())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns a string representation of this Task's tags
     */
    default String tagsString() {
        final StringBuffer buffer = new StringBuffer();
        final String separator = ", ";
        getTags().forEach(tag -> buffer.append(tag).append(separator));
        if (buffer.length() == 0) {
            return "";
        } else {
            return buffer.substring(0, buffer.length() - separator.length());
        }
    }

}

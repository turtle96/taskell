package seedu.taskell.model.task;


import seedu.taskell.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's priority in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class TaskPriority {
    
    public static final String MESSAGE_PRIORITY_CONSTRAINTS = "Task priority must be an intger from 1 to 5";
    public static final String PRIORITY_VALIDATION_REGEX = ".+";

    public final String priority;

    /**
     * Validates given priority.
     *
     * @throws IllegalValueException if given priority string is invalid.
     */
    public TaskPriority(String priority) throws IllegalValueException {
        assert priority != null;
        if (!isValidAddress(priority)) {
            throw new IllegalValueException(MESSAGE_PRIORITY_CONSTRAINTS);
        }
        this.priority = priority;
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(PRIORITY_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return priority;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskPriority // instanceof handles nulls
                && this.priority.equals(((TaskPriority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }

}
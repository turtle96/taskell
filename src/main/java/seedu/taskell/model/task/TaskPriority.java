package seedu.taskell.model.task;


import seedu.taskell.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's priority in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class TaskPriority {
    
    public static final String MESSAGE_TASK_PRIORITY_CONSTRAINTS = "Task priority can be in any format";
    public static final String TASK_PRIORITY_VALIDATION_REGEX = ".+";

    public final String taskPriority;

    /**
     * Validates given priority.
     *
     * @throws IllegalValueException if given priority string is invalid.
     */
    public TaskPriority(String priority) throws IllegalValueException {
        assert priority != null;
        if (!isValidPriority(priority)) {
            throw new IllegalValueException(MESSAGE_TASK_PRIORITY_CONSTRAINTS);
        }
        this.taskPriority = priority;
    }

    /**
     * Returns true if a given string is a valid task taskTime.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(TASK_PRIORITY_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskPriority;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskPriority // instanceof handles nulls
                && this.taskPriority.equals(((TaskPriority) other).taskPriority)); // state check
    }

    @Override
    public int hashCode() {
        return taskPriority.hashCode();
    }

}
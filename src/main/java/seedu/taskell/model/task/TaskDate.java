package seedu.taskell.model.task;

import seedu.taskell.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's date in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDate(String)}
 */
public class TaskDate {

    public static final String MESSAGE_TASK_DATE_CONSTRAINTS = "Task dates should only contain numbers";
    public static final String TASK_DATE_VALIDATION_REGEX = "\\d+";

    public final String taskDate;

    /**
     * Validates given task date.
     *
     * @throws IllegalValueException if given task date string is invalid.
     */
    public TaskDate(String taskDate) throws IllegalValueException {
        assert taskDate != null;
        taskDate = taskDate.trim();
        if (!isValidTaskDate(taskDate)) {
            throw new IllegalValueException(MESSAGE_TASK_DATE_CONSTRAINTS);
        }
        this.taskDate = taskDate;
    }

    /**
     * Returns true if a given string is a valid task taskDate number.
     */
    public static boolean isValidTaskDate(String test) {
        return test.matches(TASK_DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && this.taskDate.equals(((TaskDate) other).taskDate)); // state check
    }

    @Override
    public int hashCode() {
        return taskDate.hashCode();
    }

}

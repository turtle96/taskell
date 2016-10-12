package seedu.taskell.model.task;


import seedu.taskell.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's time in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class TaskTime {

    public static final String MESSAGE_TIME_CONSTRAINTS =
            "Task time should be 2 alphanumeric/period strings separated by '@'";
    public static final String TIME_VALIDATION_REGEX = "[\\w\\.]+@[\\w\\.]+";

    public final String time;

    /**
     * Validates given time.
     *
     * @throws IllegalValueException if given time address string is invalid.
     */
    public TaskTime(String time) throws IllegalValueException {
        assert time != null;
        time = time.trim();
        if (!isValidTime(time)) {
            throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
        }
        this.time = time;
    }

    /**
     * Returns if a given string is a valid task time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTime // instanceof handles nulls
                && this.time.equals(((TaskTime) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}

package seedu.taskell.model.task;

import seedu.taskell.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's date in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class TaskDate {

    public static final String MESSAGE_DATE_CONSTRAINTS = "Task dates should only contain numbers";
    public static final String DATE_VALIDATION_REGEX = "\\d+";
    
    public final String date;

    /**
     * Validates given date number.
     *
     * @throws IllegalValueException if given date string is invalid.
     */
    public TaskDate(String date) throws IllegalValueException {
        assert date != null;
        date = date.trim();
        if (!isValidPhone(date)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.value = date;
    }

    /**
     * Returns true if a given string is a valid task date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && this.value.equals(((TaskDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

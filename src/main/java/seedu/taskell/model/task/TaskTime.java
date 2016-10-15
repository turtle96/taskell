package seedu.taskell.model.task;


import seedu.taskell.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's taskTime in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskTime(String)}
 */
public class TaskTime {

    public static final String MESSAGE_TASK_TIME_CONSTRAINTS =
            "Task time should be 2 alphanumeric/period strings separated by '@'";
    public static final String TASKTIME_VALIDATION_REGEX = "[\\w\\.]+@[\\w\\.]+";

    public final String taskTime;

    /**
     * Validates given tasktime.
     *
     * @throws IllegalValueException if given task time string is invalid.
     */
    public TaskTime(String taskTime) throws IllegalValueException {
        assert taskTime != null;
        taskTime = taskTime.trim();
        if (!isValidTaskTime(taskTime)) {
            throw new IllegalValueException(MESSAGE_TASK_TIME_CONSTRAINTS);
        }
        this.taskTime = taskTime;
    }

    /**
     * Returns if a given string is a valid task taskTime.
     */
    public static boolean isValidTaskTime(String test) {
        return test.matches(TASKTIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTime // instanceof handles nulls
                && this.taskTime.equals(((TaskTime) other).taskTime)); // state check
    }

    @Override
    public int hashCode() {
        return taskTime.hashCode();
    }

}

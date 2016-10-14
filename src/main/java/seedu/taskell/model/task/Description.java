package seedu.taskell.model.task;

import seedu.taskell.commons.exceptions.IllegalValueException;

/**
<<<<<<< HEAD
 * Represents a Task's name in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
=======
 * Represents a Task's description in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
>>>>>>> refactor_unfactored_code
 */
public class Description {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS = "Task description should be spaces or alphanumeric characters";
    public static final String DESCRIPTION_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String description;

    /**
     * Validates given description.
     *
     * @throws IllegalValueException if given description string is invalid.
     */
    public Description(String description) throws IllegalValueException {
        assert description != null;
        description = description.trim();
        if (!isValidDescription(description)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.description = description;
    }

    /**
<<<<<<< HEAD
     * Returns true if a given string is a valid task name.
=======
     * Returns true if a given string is a valid task description.
>>>>>>> refactor_unfactored_code
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}

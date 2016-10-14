package seedu.taskell.model.task;

import seedu.taskell.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's description in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Description {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS = "Task description should be spaces or alphanumeric characters";
    public static final String DESCRIPTION_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String description;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Description(String name) throws IllegalValueException {
        assert name != null;
        name = name.trim();
        if (!isValidDescription(name)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.description = name;
    }

    /**
     * Returns true if a given string is a valid person name.
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

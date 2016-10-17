package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class DeadlineTask extends Task {

    /**
     * Every field must be present and not null.
     */
    public DeadlineTask(Description description, TaskDate taskDate, TaskTime taskTime, TaskPriority taskPriority, UniqueTagList tags) {
        super(description,taskDate,taskTime,taskPriority,tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }
}

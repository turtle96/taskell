package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.model.task.Description;
import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Represents a Floating Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class FloatingTask extends Task {

    public FloatingTask(Description description, String taskType, TaskDate startDate, TaskTime startTime, TaskTime endTime, TaskPriority taskPriority, UniqueTagList tags) {
        super(description, Task.FLOATING_TASK, startDate, startTime, endTime, taskPriority, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

}

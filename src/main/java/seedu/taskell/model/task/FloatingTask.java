package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.model.task.Description;
import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Represents a Floating Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class FloatingTask extends Task {

    public FloatingTask(String description, String taskPriority, UniqueTagList tags) throws IllegalValueException {
        this(new Description(description),
                FLOATING_TASK,
                new TaskDate(TaskDate.DEFAULT_DATE),
                new TaskDate(TaskDate.DEFAULT_DATE),
                new TaskTime(TaskTime.DEFAULT_START_TIME),
                new TaskTime(TaskTime.DEFAULT_END_TIME),
                new TaskPriority(taskPriority),
                tags);
    }
    
    public FloatingTask(Description description, String taskType, TaskDate startDate, TaskDate endDate, TaskTime startTime, TaskTime endTime, TaskPriority taskPriority, UniqueTagList tags) {
        super(description, taskType, startDate, endDate, startTime, endTime, taskPriority, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

}

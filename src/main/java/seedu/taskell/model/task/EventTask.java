package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * Represents an Event task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class EventTask extends Task {
    public static final String MESSAGE_EVENT_CONSTRAINTS = "Start date and time must be before end date and time"
            + "\nAll date and time should not before current time";

    public EventTask(String description, String startDate, String endDate, String startTime, String endTime, String taskPriority, UniqueTagList tags) throws IllegalValueException {
        this(new Description(description),
                EVENT_TASK,
                new TaskDate(startDate),
                new TaskDate(endDate),
                new TaskTime(startTime),
                new TaskTime(endTime),
                new TaskPriority(taskPriority),
                tags);
    }
    /**
     * Every field must be present and not null.
     * @throws IllegalValueException 
     */
    public EventTask(Description description, String taskType, TaskDate startDate, TaskDate endDate, TaskTime startTime, TaskTime endTime, TaskPriority taskPriority, UniqueTagList tags) throws IllegalValueException {
        super(description, Task.EVENT_TASK, startDate, endDate, startTime, endTime, taskPriority, tags);
        
        if (!isValidEventDuration(startDate, endDate, startTime, endTime)) {
            throw new IllegalValueException(MESSAGE_EVENT_CONSTRAINTS);
        }
    }
    
    private boolean isValidEventDuration(TaskDate startDate, TaskDate endDate, TaskTime startTime, TaskTime endTime) {
        try {
            TaskDate today = new TaskDate(TaskDate.getTodayDate());
            
            if (startDate.isBefore(today) || endDate.isBefore(today)) {
                return false;
            } else if (startDate.isAfter(endDate)) {
                return false;
            } else if (startDate.equals(endDate)) {
                return !startTime.isAfter(endTime);
            } else {
                return true;
            }
        } catch (IllegalValueException ive) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }
}
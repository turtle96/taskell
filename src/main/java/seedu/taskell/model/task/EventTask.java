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
        endDate = autoAdjustEndDate(startDate, endDate, startTime, endTime);
        
        if (!isValidEventDuration(startDate, endDate, startTime, endTime)) {
            throw new IllegalValueException(MESSAGE_EVENT_CONSTRAINTS);
        }
        
        this.description = description;
        this.taskType = EVENT_TASK;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskPriority = taskPriority;
        this.tags = tags;
    }
    
    private boolean isValidEventDuration(TaskDate startDate, TaskDate endDate, TaskTime startTime, TaskTime endTime) {
        TaskDate today = TaskDate.getTodayDate();
        TaskTime currentTime = TaskTime.getTimeNow();
        
        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            return false;
        } else if (startDate.isAfter(endDate)) {
            return false;
        } if (startDate.equals(today) && startTime.isBefore(currentTime)) { 
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Adjust the endDate such that it fits into the real-world context
     * @throws IllegalValueException
     */
    private TaskDate autoAdjustEndDate(TaskDate startDate, TaskDate endDate, TaskTime startTime, TaskTime endTime) throws IllegalValueException {
        TaskDate today = TaskDate.getTodayDate();
        if (startDate.equals(endDate) && startTime.isAfter(endTime)) {
            endDate = endDate.getNextDay();
        } else if (startDate.getDayNameInWeek().equals(today.getDayNameInWeek())
                && !endDate.getDayNameInWeek().equals(today.getDayNameInWeek())) {
            endDate = endDate.getNextWeek();
        }
        return endDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }
}
package seedu.taskell.model.task;

import java.util.Objects;

import seedu.taskell.model.task.Description;
import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.commons.util.CollectionUtil;
import seedu.taskell.logic.commands.IncorrectCommand;
import seedu.taskell.model.tag.UniqueTagList;

//@@author A0139257X
/**
 * Represents a Floating Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */
public class FloatingTask extends Task {
    
    public static final String RECURRING_TYPE_NOT_ALLOWED = "Floating task cannot be recurring";
    public static final String EDIT_FLOATING_NOT_ALLOWED = "Floating task does not have date and time.";
    
    
    public FloatingTask(String[] taskComponentArray, boolean[] hasTaskComponentArray, UniqueTagList tags) throws IllegalValueException {

        this(new Description(taskComponentArray[DESCRIPTION]),
                new TaskDate(TaskDate.DEFAULT_DATE),
                new TaskDate(TaskDate.DEFAULT_DATE),
                new TaskTime(TaskTime.DEFAULT_START_TIME),
                new TaskTime(TaskTime.DEFAULT_END_TIME),
                new TaskPriority(taskComponentArray[TASK_PRIORITY]),
                new RecurringType(taskComponentArray[RECURRING_TYPE]),
                new TaskStatus(TaskStatus.INCOMPLETE),
                tags);
        
        if (hasTaskComponentArray[Task.RECURRING_COMPONENT]) {
            throw new IllegalValueException(FloatingTask.RECURRING_TYPE_NOT_ALLOWED);
        }
    }
    
    public FloatingTask(Description description, TaskDate startDate, 
            TaskDate endDate, TaskTime startTime, TaskTime endTime, TaskPriority taskPriority, 
            RecurringType recurringType, TaskStatus taskStatus, UniqueTagList tags) {
        
        super(description, FLOATING_TASK, 
                startDate, endDate, 
                startTime, endTime, 
                taskPriority, recurringType, 
                taskStatus, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

}

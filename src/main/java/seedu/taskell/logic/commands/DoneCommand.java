//@@author A0148004R
package seedu.taskell.logic.commands;

import seedu.taskell.commons.core.Messages;
import seedu.taskell.commons.core.UnmodifiableObservableList;
import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.task.ReadOnlyTask;
import seedu.taskell.model.task.Task;
import seedu.taskell.model.task.TaskDate;
import seedu.taskell.model.task.TaskStatus;
import seedu.taskell.model.task.UniqueTaskList;
import seedu.taskell.model.task.UniqueTaskList.TaskNotFoundException;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Done the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Done Task: %1$s";

    public final int targetIndex;
    
    
    public DoneCommand(int targetIndex) {
        this.targetIndex = targetIndex;
        
    }
    
    @Override
    public CommandResult execute() {

        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        
        TaskStatus finsihedStatus = new TaskStatus(TaskStatus.FINISHED);
        
        ReadOnlyTask taskToDone = lastShownList.get(targetIndex - 1);
        Task newTask = null;
        
        try {
            if(taskToDone.getRecurringType().equals("daily")){
                newTask = new Task(taskToDone.getDescription(), taskToDone.getTaskType(), taskToDone.getStartDate().getNextDay(), taskToDone.getEndDate().getNextDay(),                
                        taskToDone.getStartTime(), taskToDone.getEndTime(), taskToDone.getTaskPriority(), taskToDone.getRecurringType(), taskToDone.getTaskStatus(), taskToDone.getTags());
                
            }else if(taskToDone.getRecurringType().equals("weekly")){
                newTask = new Task(taskToDone.getDescription(), taskToDone.getTaskType(), taskToDone.getStartDate().getNextWeek(), taskToDone.getEndDate().getNextWeek(),                
                        taskToDone.getStartTime(), taskToDone.getEndTime(), taskToDone.getTaskPriority(), taskToDone.getRecurringType(), taskToDone.getTaskStatus(), taskToDone.getTags());
                
            }else if(taskToDone.getRecurringType().equals("monthly")){
                newTask = new Task(taskToDone.getDescription(), taskToDone.getTaskType(), taskToDone.getStartDate().getNextMonth(), taskToDone.getEndDate().getNextMonth(),                
                        taskToDone.getStartTime(), taskToDone.getEndTime(), taskToDone.getTaskPriority(), taskToDone.getRecurringType(), taskToDone.getTaskStatus(), taskToDone.getTags());
                
            }else if(taskToDone.getRecurringType().equals("neverRecur")){
                newTask = new Task(taskToDone.getDescription(), taskToDone.getTaskType(), taskToDone.getStartDate(), taskToDone.getEndDate(),                
                        taskToDone.getStartTime(), taskToDone.getEndTime(), taskToDone.getTaskPriority(), taskToDone.getRecurringType(), finsihedStatus, taskToDone.getTags());
            }
        } catch (IllegalValueException ive) {
            return new CommandResult(TaskDate.MESSAGE_TASK_DATE_CONSTRAINTS);
        }   
        
        try {
            model.editTask(taskToDone, newTask);
        } catch (TaskNotFoundException pnfe) {
            assert false : "The target task cannot be missing";
        } catch (UniqueTaskList.DuplicateTaskException e) {
            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_TASK);
        } 

        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToDone));
    }
}
//@@author
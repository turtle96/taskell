package seedu.taskell.logic.commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.taskell.model.task.TaskPriority;

public class ListPriorityCommand extends Command {

    public static final String COMMAND_WORD = "list-priority";

    public static final String MESSAGE_SUCCESS = "Listed all completed tasks in descending priority";

    public static final String MESSAGE_USAGE = "List a valid priority value from 0 to 3";
    
    private Set<String> mostImportant = new HashSet<>(Arrays.asList(TaskPriority.HIGH_PRIORITY));
    private Set<String> important = new HashSet<>(Arrays.asList(TaskPriority.MEDIUM_PRIORITY));
    private Set<String> lessImportant = new HashSet<>(Arrays.asList(TaskPriority.LOW_PRIORITY));
    private Set<String> leastImportant = new HashSet<>(Arrays.asList(TaskPriority.NO_PRIORITY));
    
    private Set<String> keyword;
    public ListPriorityCommand(String priority) {
        if(priority.equals(TaskPriority.HIGH_PRIORITY))
            keyword = mostImportant;
        else if(priority.equals(TaskPriority.MEDIUM_PRIORITY))
            keyword = important;
        else if(priority.equals(TaskPriority.LOW_PRIORITY))
            keyword = lessImportant;
        else
            keyword = leastImportant;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredTaskListPriority(keyword);
        return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }
}
package seedu.taskell.logic.commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.taskell.model.task.TaskStatus;

public class ListDoneCommand extends Command {
    
    public static final String COMMAND_WORD = "list-done";

    public static final String MESSAGE_SUCCESS = "Listed all completed tasks";

    private Set<String> keywordSet = new HashSet<>(Arrays.asList(TaskStatus.FINISHED));

    public ListDoneCommand() {
       
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredtaskListCompleted(keywordSet);
        return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }
}
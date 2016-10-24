package seedu.taskell.logic.commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ListDateCommand extends Command {
    
    public static final String COMMAND_WORD = "list-date";

    public static final String MESSAGE_SUCCESS = "Listed all tasks on a given date";

    private Set<String> keywordSet;

    public ListDateCommand(String date) {
       keywordSet = new HashSet<>(Arrays.asList(date));
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredtaskListDate(keywordSet);
        return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
    }
}
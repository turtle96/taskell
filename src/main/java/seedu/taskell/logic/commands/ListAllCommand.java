package seedu.taskell.logic.commands;

public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "list-all";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    public ListAllCommand() {}

    @Override
    public CommandResult execute() {
        model.updateFilteredListToShowAll();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
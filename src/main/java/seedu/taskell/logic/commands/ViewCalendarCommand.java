package seedu.taskell.logic.commands;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.events.ui.DisplayCalendarViewEvent;

public class ViewCalendarCommand extends Command {
    
    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows calendar view on right panel.\n"
            + "Example: " + COMMAND_WORD;
    
    private static final String MESSAGE_SUCCESS = "Displayed calendar.";

    @Override
    public CommandResult execute() {
        indicateDisplayCalendarView();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
    
    private void indicateDisplayCalendarView() {
        EventsCenter.getInstance().post(new DisplayCalendarViewEvent());
    }

}

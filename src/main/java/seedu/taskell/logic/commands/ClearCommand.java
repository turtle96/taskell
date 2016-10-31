package seedu.taskell.logic.commands;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.events.ui.ClearCommandInputEvent;
import seedu.taskell.model.Model;
import seedu.taskell.model.TaskManager;

/**
 * Clears the task manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Clear command executed.";
    private static ClearCommand self;

    public ClearCommand() {}

    public static ClearCommand getInstance() {
        if (self == null) {
            self = new ClearCommand();
        }
        
        return self;
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        raiseClearCommandInputEvent();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /** will be called from UiManager after confirmation of clear
     * */
    public void executeClear() {
        model.resetData(TaskManager.getEmptyTaskManager());
    }

    private void raiseClearCommandInputEvent() {
        EventsCenter.getInstance().post(new ClearCommandInputEvent());      
    }
}

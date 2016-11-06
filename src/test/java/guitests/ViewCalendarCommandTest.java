/** @@author A0142130A **/
package guitests;

import org.junit.Test;

import seedu.taskell.logic.commands.ViewCalendarCommand;
import seedu.taskell.logic.commands.ViewHistoryCommand;

public class ViewCalendarCommandTest extends TaskManagerGuiTest {
    
    @Test
    public void viewCal_success() {
        String expected = ViewCalendarCommand.MESSAGE_SUCCESS;
        
        commandBox.runCommand("calendar");
        assertResultMessage(expected);
        
        commandBox.runCommand("cal");
        assertResultMessage(expected);
    }
    
}

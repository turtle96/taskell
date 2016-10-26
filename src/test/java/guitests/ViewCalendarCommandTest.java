/** @@author A0142130A **/
package guitests;

import org.junit.Test;

public class ViewCalendarCommandTest extends TaskManagerGuiTest {
    
    @Test
    public void viewCal() {
        String expected = "Displayed calendar.";
        
        commandBox.runCommand("calendar");
        assertResultMessage(expected);
        
        commandBox.runCommand("cal");
        assertResultMessage(expected);
    }
}

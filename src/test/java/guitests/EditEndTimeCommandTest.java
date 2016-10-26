//@@author A0142073R
package guitests;

import org.junit.Test;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.testutil.TestTask;
import seedu.taskell.testutil.TestUtil;

import static org.junit.Assert.assertTrue;
import static seedu.taskell.logic.commands.EditEndTimeCommand.MESSAGE_EDIT_TASK_SUCCESS;

public class EditEndTimeCommandTest extends TaskManagerGuiTest {

    @Test
    public void edit() throws IllegalValueException {

        // edit the first in the list
        TestTask[] currentList = td.getTypicalTasks();
        int targetIndex = 1;
        String newDate = "5pm";
        asserteditSuccess(targetIndex, currentList, newDate);

        // edit the last in the list
        targetIndex = currentList.length;
        newDate = "12.35am";
        asserteditSuccess(targetIndex, currentList, newDate);

        // invalid index
        commandBox.runCommand("edit-endTime " + currentList.length + 1 + " 10am");
        assertResultMessage("The task index provided is invalid");

        //invalid command format
        commandBox.runCommand("edit-endTime 1 to 4pm ");
        assertResultMessage("Invalid command format! \n" 
                + "edit-endTime"
                + ": Edits the end time of a task identified by the index number used in the last task listing.\n"
                + "Parameters: INDEX (must be a positive integer) NEW_END_TIME\n"
                + "Example: " + "edit-endTime" + " 1 9pm ");

    }

    /**
     * Runs the edit command to edit the end time of a task at specified
     * index and confirms the result is correct.
     * 
     * @param targetIndexOneIndexed
     *            e.g. to edit the end time of first task in the list, 1
     *            should be given as the target index.
     * @param currentList
     *            A copy of the current list of tasks (before deletion).
     * @throws IllegalValueException
     */
    private void asserteditSuccess(int targetIndexOneIndexed, final TestTask[] currentList, String newDate)
            throws IllegalValueException {
        TestTask taskToedit = currentList[targetIndexOneIndexed - 1]; // -1
                                                                      // because
                                                                      // array
                                                                      // uses
                                                                      // zero
                                                                      // indexing
        TestTask newTask = TestUtil.editTaskEndTime(taskToedit, newDate);

        commandBox.runCommand("edit-endTime " + targetIndexOneIndexed + " " + newDate);
        TestTask[] current = TestUtil.replaceTaskFromList(currentList, newTask, targetIndexOneIndexed - 1);
        // confirm the list now contains all previous tasks except the edited
        // task
        assertTrue(taskListPanel.isListMatching(current));

        // confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToedit, newTask));
    }

}
//@@author
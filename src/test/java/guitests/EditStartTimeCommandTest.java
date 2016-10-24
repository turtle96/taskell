package guitests;

import org.junit.Test;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.testutil.TestTask;
import seedu.taskell.testutil.TestUtil;

import static org.junit.Assert.assertTrue;
import static seedu.taskell.logic.commands.EditStartTimeCommand.MESSAGE_EDIT_TASK_SUCCESS;

public class EditStartTimeCommandTest extends TaskManagerGuiTest {

    @Test
    public void edit() throws IllegalValueException {

        // edit the first in the list
        TestTask[] currentList = td.getTypicalTasks();
        int targetIndex = 1;
        String newDate = "5am";
        asserteditSuccess(targetIndex, currentList, newDate);

        // edit the last in the list
        targetIndex = currentList.length;
        newDate = "12.35pm";
        asserteditSuccess(targetIndex, currentList, newDate);

        // invalid index
        commandBox.runCommand("edit-startTime " + currentList.length + 1 + " 1am");
        assertResultMessage("The task index provided is invalid");

        //invalid command format
        commandBox.runCommand("edit-startTime 3 to 4pm ");
        assertResultMessage("Invalid command format! \n" 
                + "edit-startTime"
                + ": Edits the start time of a task identified by the index number used in the last task listing.\n"
                + "Parameters: INDEX (must be a positive integer) NEW_START_TIME\n"
                + "Example: edit-startTime 1 2pm ");

    }

    /**
     * Runs the edit command to edit the start time of a task at specified
     * index and confirms the result is correct.
     * 
     * @param targetIndexOneIndexed
     *            e.g. to edit the start time of first task in the list, 1
     *            should be given as the target index.
     * @param currentList
     *            A copy of the current list of tasks (before deletion).
     * @throws IllegalValueException
     */
    private void asserteditSuccess(int targetIndexOneIndexed, final TestTask[] currentList, String newTime)
            throws IllegalValueException {
        TestTask taskToedit = currentList[targetIndexOneIndexed - 1]; // -1
                                                                      // because
                                                                      // array
                                                                      // uses
                                                                      // zero
                                                                      // indexing
        TestTask newTask = TestUtil.editTaskStartTime(taskToedit, newTime);

        commandBox.runCommand("edit-startTime " + targetIndexOneIndexed + " " + newTime);
        TestTask[] current = TestUtil.replaceTaskFromList(currentList, newTask, targetIndexOneIndexed - 1);
        // confirm the list now contains all previous tasks except the edited
        // task
        assertTrue(taskListPanel.isListMatching(current));

        // confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToedit, newTask));
    }

}
//@@author A0142073R
package guitests;

import org.junit.Test;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.testutil.TestTask;
import seedu.taskell.testutil.TestUtil;

import static org.junit.Assert.assertTrue;
import static seedu.taskell.logic.commands.edit.EditPriorityCommand.MESSAGE_EDIT_TASK_SUCCESS;

public class EditPriorityCommandTest extends TaskManagerGuiTest {

    @Test
    public void edit() throws IllegalValueException {

        // edit the first in the list
        TestTask[] currentList = td.getTypicalTasks();
        int targetIndex = 1;
        String newPriority = "3";
        asserteditSuccess(targetIndex, currentList, newPriority);

        // edit the last in the list
        targetIndex = currentList.length;
        newPriority = "2";
        asserteditSuccess(targetIndex, currentList, newPriority);

        // invalid index
        commandBox.runCommand("edit-priority " + currentList.length + 1 + " 2");
        assertResultMessage("The task index provided is invalid");

        // invalid command
        commandBox.runCommand("edit-priority ");
        assertResultMessage("Invalid command format! \n" + "edit-priority"
                + ": Edits the priority of a task identified by the index number used in the last task listing.\n"
                + "Parameters: INDEX (must be a positive integer) NEW_PRIORITY(must be between 0 to 3)\n" + "Example: " + "edit-priority" + " 1 3 ");

    }

    /**
     * Runs the edit command to edit the priority of a task at specified
     * index and confirms the result is correct.
     * 
     * @param targetIndexOneIndexed
     *            e.g. to edit the priority of first task in the list, 1
     *            should be given as the target index.
     * @param currentList
     *            A copy of the current list of tasks (before deletion).
     * @throws IllegalValueException
     */
    private void asserteditSuccess(int targetIndexOneIndexed, final TestTask[] currentList, String newPriority)
            throws IllegalValueException {
        TestTask taskToedit = currentList[targetIndexOneIndexed - 1]; // -1
                                                                      // because
                                                                      // array
                                                                      // uses
                                                                      // zero
                                                                      // indexing
        TestTask newTask = TestUtil.editTaskPriority(taskToedit, newPriority);

        commandBox.runCommand("edit-priority " + targetIndexOneIndexed + " " + newPriority);
        TestTask[] current = TestUtil.replaceTaskFromList(currentList, newTask, targetIndexOneIndexed - 1);
        // confirm the list now contains all previous tasks except the edited
        // task
        assertTrue(taskListPanel.isListMatching(current));

        // confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToedit, newTask));
    }

}
//@@author

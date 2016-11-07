package guitests;

import static org.junit.Assert.assertEquals;
import static seedu.taskell.logic.commands.UndoneCommand.MESSAGE_UNDONE_TASK_SUCCESS;
import static seedu.taskell.logic.commands.DoneCommand.MESSAGE_DONE_TASK_SUCCESS;
import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.task.RecurringType;
import seedu.taskell.model.task.TaskStatus;
import seedu.taskell.testutil.TestTask;

public class UndoneCommandTest extends TaskManagerGuiTest {
    
    @Test
    public void Undone() {
        TestTask[] currentList= td.getTypicalTasks();
        int TargetIndex = 1;
        assertUndoneSuccess(TargetIndex, currentList);
        
        commandBox.runCommand("undone " + currentList.length + 1);
        assertResultMessage("The task index provided is invalid");
        
        commandBox.runCommand("undone " + 2);
        assertResultMessage("The task status is already incomplete");
    }
    /**
     * Runs the done command to Undon the task at specified index and confirms the result is correct.
     * @param targetIndexOneIndexed e.g. to mark the first task in the list as finished, 1 should be given as the target index.
     * @param currentList A copy of the current list of tasks (before marking as ).
     */
    private void assertUndoneSuccess(int targetIndexOneIndexed, final TestTask[] currentList){
        commandBox.runCommand("done " + targetIndexOneIndexed);
        
        TestTask taskToFinish = currentList[targetIndexOneIndexed-1];
        assertResultMessage(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToFinish));
       
        
        commandBox.runCommand("list-all");
        
        commandBox.runCommand("undone " + targetIndexOneIndexed);
               
        TaskCardHandle updatedCard = taskListPanel.navigateToTask(taskToFinish.getDescription().description);
        assertEquals(TaskStatus.INCOMPLETE, updatedCard.getTaskComplete());
    }
}

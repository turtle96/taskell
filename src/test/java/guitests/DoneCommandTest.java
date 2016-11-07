//@@author A0148004R
package guitests;

import static org.junit.Assert.assertEquals;
import static seedu.taskell.logic.commands.DoneCommand.MESSAGE_DONE_TASK_SUCCESS;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.task.RecurringType;
import seedu.taskell.model.task.TaskStatus;
import seedu.taskell.testutil.TestTask;

public class DoneCommandTest extends TaskManagerGuiTest {
    
    @Test
    public void done() throws IllegalValueException {
        
        //done the first item on the list
        TestTask[] currentList = td.getTypicalTasks();
        int targetIndex = 1;
        assertDoneSuccess(targetIndex, currentList);
        
        targetIndex = 2;
        assertDoneSuccess(targetIndex, currentList);
       
        targetIndex = 3;
        assertDoneSuccess(targetIndex, currentList);
        
        targetIndex = 4;
        assertDoneSuccess(targetIndex, currentList);
        
        commandBox.runCommand("done " + currentList.length + 1);
        assertResultMessage("The task index provided is invalid");
    }
    
    /**
     * Runs the done command to finish the task at specified index and confirms the result is correct.
     * @param targetIndexOneIndexed e.g. to mark the first task in the list as finished, 1 should be given as the target index.
     * @param currentList A copy of the current list of tasks (before marking as ).
     * @throws IllegalValueException 
     */
    private void assertDoneSuccess(int targetIndexOneIndexed, final TestTask[] currentList) throws IllegalValueException {
        commandBox.runCommand("done " + targetIndexOneIndexed);
        TestTask taskToFinish = currentList[targetIndexOneIndexed-1]; //-1 because array uses zero indexing
        
        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToFinish));
        
        commandBox.runCommand("list-all");
        
        //confirm the new card contains the right data
        TaskCardHandle updatedCard = taskListPanel.navigateToTask(taskToFinish.getDescription().description);
        
        
        
        if(taskToFinish.getRecurringType().recurringType.equals(RecurringType.DEFAULT_RECURRING)){
            assertEquals(TaskStatus.FINISHED, updatedCard.getTaskComplete());
        } else if (taskToFinish.getRecurringType().recurringType.equals(RecurringType.DAILY_RECURRING)){
            assertEquals(taskToFinish.getStartDate().getNextDay().getDisplayDate(), updatedCard.getStartDate());
        } else if (taskToFinish.getRecurringType().recurringType.equals(RecurringType.WEEKLY_RECURRING)){
            assertEquals(taskToFinish.getStartDate().getNextWeek().getDisplayDate(), updatedCard.getStartDate());
        } else if (taskToFinish.getRecurringType().recurringType.equals(RecurringType.MONTHLY_RECURRING)){
            assertEquals(taskToFinish.getStartDate().getNextMonth().getDisplayDate(), updatedCard.getStartDate());
        }
    }

}
//@@author

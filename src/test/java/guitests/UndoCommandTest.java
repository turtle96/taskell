package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.taskell.logic.commands.UndoCommand;
import seedu.taskell.testutil.TestTask;
import seedu.taskell.testutil.TestUtil;

public class UndoCommandTest extends TaskManagerGuiTest {
    
    @Test
    public void undoAdd() {
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToAdd = td.holdMeeting;
        
        //add one task
        commandBox.runCommand(taskToAdd.getAddCommand());
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);
        
        commandBox.runCommand("undo 1");
        assertDeleteSuccess(currentList.length, currentList);
        
        UndoCommand.clearCommandHistory();
    }
    
    @Test
    public void undoDelete() {
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToDelete = currentList[0];
        int target = 1;
        
        //delete a task
        commandBox.runCommand("delete " + target);
        assertDeleteSuccess(target, currentList);
        currentList = TestUtil.removeTaskFromList(currentList, 1);
        
        commandBox.runCommand("undo 1");
        assertAddSuccess(taskToDelete, currentList);
        
        UndoCommand.clearCommandHistory();
    }
    
    @Test
    public void undoAndRedoAdd() {
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToAdd = td.holdMeeting;
        
        //add one task
        commandBox.runCommand(taskToAdd.getAddCommand());    
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);
        
        commandBox.runCommand("undo 1");
        assertDeleteSuccess(currentList.length, currentList);
        currentList = TestUtil.removeTaskFromList(currentList, currentList.length);
        
        commandBox.runCommand("undo 1");
        assertAddSuccess(taskToAdd, currentList);
        UndoCommand.clearCommandHistory();
    }
    
    @Test
    public void undoAndRedoDelete() {
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToDelete = currentList[0];
        int target = 1;
        
        //delete a task
        commandBox.runCommand("delete " + target);
        assertDeleteSuccess(target, currentList);
        currentList = TestUtil.removeTaskFromList(currentList, 1);
        
        commandBox.runCommand("undo 1");
        assertAddSuccess(taskToDelete, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToDelete);
        
        target = currentList.length;
        commandBox.runCommand("undo 1");
        assertDeleteSuccess(target, currentList);
        
        UndoCommand.clearCommandHistory();
    }
    
    private void assertAddSuccess(TestTask taskToAdd, TestTask... currentList) {
        //confirm the new card contains the right data
        TaskCardHandle addedCard = taskListPanel.navigateToTask(taskToAdd.getDescription().description);
        assertMatching(taskToAdd, addedCard);

        //confirm the list now contains all previous tasks plus the new task
        TestTask[] expectedList = TestUtil.addTasksToList(currentList, taskToAdd);
        assertTrue(taskListPanel.isListMatching(expectedList));
    }
    
    /**
     * Confirms the undo add result is correct. Means task has been deleted.
     * @param targetIndexOneIndexed e.g. to delete the first task in the list, 1 should be given as the target index.
     * @param currentList A copy of the current list of tasks (before deletion).
     */
    private void assertDeleteSuccess(int targetIndexOneIndexed, final TestTask[] currentList) {
        TestTask taskToDelete = currentList[targetIndexOneIndexed-1]; //-1 because array uses zero indexing
        TestTask[] expectedRemainder = TestUtil.removeTaskFromList(currentList, targetIndexOneIndexed);

        //confirm the list now contains all previous tasks except the deleted task
        assertTrue(taskListPanel.isListMatching(expectedRemainder)); 
    }
}

/** @@author A0142130A **/
package seedu.taskell.history;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.taskell.commons.core.ComponentManager;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.undo.ExecutedIncorrectCommandEvent;
import seedu.taskell.logic.commands.AddCommand;
import seedu.taskell.logic.commands.DoneCommand;
import seedu.taskell.logic.commands.EditCommand;
import seedu.taskell.logic.commands.UndoneCommand;

import seedu.taskell.model.Model;
import seedu.taskell.model.task.Task;

/** Implementation of History API, manages command history available for undo
 *  uses singleton pattern to prevent multiple instantiation
 */
public class HistoryManager extends ComponentManager implements History {

    private static final Logger logger = LogsCenter.getLogger(HistoryManager.class.getName());
    
    private static ArrayList<CommandHistory> historyList;
    private static Model model;
    private static HistoryManager self;
    
    public HistoryManager() {
        historyList = new ArrayList<>();
    }
    
    public static History getInstance() {
        if (self == null) {
            self = new HistoryManager();
        }    
        return self;
    }
    
    public static void setModel(Model m) {
        model = m;
    }
    
    @Override
    public ArrayList<CommandHistory> getList() {
        return historyList;
    }

    @Override
    /** returns list of command history's user input strings
     * */
    public ArrayList<String> getListCommandText() {
        assert historyList != null;
        
        ArrayList<String> list = new ArrayList<>();
        for (CommandHistory history: historyList) {
            list.add(history.getCommandText());
        }
        
        return list;
    }
    
    @Override
    public void updateHistory() {
        try {
            updateList();
        } catch (ConcurrentModificationException e) {
            logger.severe("Concurrent modification exception while updating history");
            updateList();
        }
    }
    
    /** should be called whenever Delete or Edit or Clear is executed
     *  deletes history of the task deleted/edited
     * */
    public synchronized void updateList() throws ConcurrentModificationException {

        if (model == null) {
            logger.severe("Model is null");
            return;
        }
        
        ArrayList<CommandHistory> toRemove = new ArrayList<>();
        
        try {
            for (CommandHistory commandHistory: historyList) {
                if (isUndoCommandTypeAndNeedPresentTask(commandHistory) 
                        && !isTaskPresent(commandHistory.getTask())) {
                    toRemove.add(commandHistory);
                } else if (isRedoCommandTypeAndNeedPresentTask(commandHistory) 
                        && !isTaskPresent(commandHistory.getTask())) {
                    toRemove.add(commandHistory);
                }
            }
        } catch (ConcurrentModificationException e) {
            throw e;
        }
        
        historyList.removeAll(toRemove);
    }
    
    /** checks if type is Add/Edit/Done/Undone that requires a task present in system to work
     * */
    private boolean isUndoCommandTypeAndNeedPresentTask(CommandHistory commandHistory) {
        return (commandHistory.getCommandType().equals(AddCommand.COMMAND_WORD) 
                || commandHistory.getCommandType().equals(EditCommand.COMMAND_WORD)
                || commandHistory.getCommandType().equals(DoneCommand.COMMAND_WORD)
                || commandHistory.getCommandType().equals(UndoneCommand.COMMAND_WORD)) 
                && !commandHistory.isRedoTrue();
    }
    
    /** checks if type is Edit with isRedo set to true
     * */
    private boolean isRedoCommandTypeAndNeedPresentTask(CommandHistory commandHistory) {
        return (commandHistory.getCommandType().equals(EditCommand.COMMAND_WORD)
                || commandHistory.getCommandType().equals(DoneCommand.COMMAND_WORD)
                || commandHistory.getCommandType().equals(UndoneCommand.COMMAND_WORD))
                && commandHistory.isRedoTrue();
    }

    @Override
    public void clear() {
        logger.info("Clearing history...");
        historyList.clear();        
    }

    @Override
    public void addCommand(String commandText, String commandType) {
        assert historyList != null;
        historyList.add(new CommandHistory(commandText, commandType));
        
    }

    @Override
    public void addTask(Task task) {
        logger.info("Adding task to history");
        if (historyList.isEmpty()) {
            logger.warning("No command history to add task to");
            return;
        }
        
        historyList.get(getOffset(historyList.size())).setTask(task);
    }

    @Override
    public void addOldTask(Task task) {
        logger.info("Adding old task to history");
        if (historyList.isEmpty()) {
            logger.warning("No command history to add task to");
            return;
        }
        
        historyList.get(getOffset(historyList.size())).setOldTask(task);
    }

    private boolean isTaskPresent(Task task) {
        return model.isTaskPresent(task);
    }
    
    @Override
    public void deleteLatestCommand() {
        logger.info("Command unsuccessfully executed. Deleting command history.");
        if (historyList.isEmpty()) {
            logger.warning("No command history to delete");
            return;
        }
        historyList.remove(getOffset(historyList.size()));
    }

    @Override
    public void deleteCommandHistory(CommandHistory commandHistory) {
        historyList.remove(commandHistory);
    }
    
    private static int getOffset(int index) {
        return index - 1;
    }

    @Subscribe
    private void handleExecuteIncorrectCommandEvent(ExecutedIncorrectCommandEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        if (event.isUndoableCommand()) {
            deleteLatestCommand();
        }
    }

    
}

/** @@author A0142130A **/
package seedu.taskell.model;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.events.undo.ExecutedIncorrectCommandEvent;
import seedu.taskell.logic.commands.AddCommand;
import seedu.taskell.logic.commands.EditCommand;
import seedu.taskell.model.task.Task;

/** Implementation of History API, manages command history available for undo
 */
public class HistoryManager implements History {

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
    /** should be called whenever DeleteCommand is executed
     *  deletes history of the task deleted
     * */
    public synchronized void updateList() throws ConcurrentModificationException {
        if (model == null) {
            logger.severe("Model is null");
            return;
        }
        
        try {
            for (CommandHistory commandHistory: historyList) {
                if (isCommandTypeAddOrEdit(commandHistory) 
                        && !isTaskPresent(commandHistory.getTask())) {
                    historyList.remove(commandHistory);
                } else if (isUndoEditCommand(commandHistory) 
                        && !isTaskPresent(commandHistory.getTask())) {
                    historyList.remove(commandHistory);
                }
            }
        } catch (ConcurrentModificationException e) {
            throw e;
        }
    }
    
    private boolean isCommandTypeAddOrEdit(CommandHistory commandHistory) {
        return (commandHistory.getCommandType().equals(AddCommand.COMMAND_WORD) 
                || commandHistory.getCommandType().contains(EditCommand.COMMAND_WORD)) 
                && !commandHistory.isRedoTrue();
    }
    
    private boolean isUndoEditCommand(CommandHistory commandHistory) {
        return commandHistory.isRedoTrue() 
                && commandHistory.getCommandType().contains(EditCommand.COMMAND_WORD);
    }

    @Override
    public synchronized void clear() {
        logger.info("Clearing history...");
        historyList.clear();        
    }

    @Override
    public synchronized void addCommand(String commandText, String commandType) {
        assert historyList != null;
        historyList.add(new CommandHistory(commandText, commandType));
    }

    @Override
    public synchronized void addTask(Task task) {
        logger.info("Adding task to history");
        if (historyList.isEmpty()) {
            logger.warning("No command history to add task to");
            return;
        }
        
        historyList.get(getOffset(historyList.size())).setTask(task);
    }

    @Override
    public synchronized void addOldTask(Task task) {
        logger.info("Adding old task to history");
        if (historyList.isEmpty()) {
            logger.warning("No command history to add task to");
            return;
        }
        
        historyList.get(getOffset(historyList.size())).setOldTask(task);
    }

    @Override
    public boolean isTaskPresent(Task task) {
        return model.isTaskPresent(task);
    }
    
    @Override
    public synchronized void deleteLatestCommand() {
        logger.info("Command unsuccessfully executed. Deleting command history.");
        if (historyList.isEmpty()) {
            logger.warning("No command history to delete");
            return;
        }
        historyList.remove(getOffset(historyList.size()));
    }

    @Override
    public synchronized void deleteCommandHistory(CommandHistory commandHistory) {
        historyList.remove(commandHistory);
    }
    
    private static int getOffset(int index) {
        return index - 1;
    }

    @Subscribe
    private void handleExecuteIncorrectCommandEvent(ExecutedIncorrectCommandEvent event) {
        if (event.isUndoableCommand()) {
            deleteLatestCommand();
        }
    }

    
}

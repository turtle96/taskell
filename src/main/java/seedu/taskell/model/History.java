package seedu.taskell.model;

import java.util.ArrayList;

/** API of History (list of CommandHistory objects)
 */
public interface History {
    
    /** Returns list of command history */
    ArrayList<CommandHistory> getList();
    
    /** Clears internal history */
    void clear();
    
    /** Adds new command to history */
    void addCommand(String commandText, String commandType);
    
    void addTask();
    
    void addOldTask();
    
    /** Checks if task is present in internal model */
    boolean isTaskPresent();
}

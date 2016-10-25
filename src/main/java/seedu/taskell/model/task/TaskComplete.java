package seedu.taskell.model.task;

import java.util.Objects;

public class TaskComplete {
    public static final String COMPLETED_INFO = "completed";
    public static final String NOT_COMPLETED_INFO = "not completed";
    public final String taskStatus;
    
    public TaskComplete(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    
    public String taskStatus() {
        return taskStatus;
    }
    
    public static boolean isValidTaskComplete(String TaskToValidate) {
        return TaskToValidate == "finished"  || TaskToValidate == "not completed";           
    }
    
    @Override
    public String toString() {
        return taskStatus() == "finished"? COMPLETED_INFO : NOT_COMPLETED_INFO;
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskComplete // instanceof handles nulls
                        && this.taskStatus() == (((TaskComplete) other).taskStatus())); // state check
    }
    @Override
    public int hashCode() {
        return Objects.hash(taskStatus());
    }
}

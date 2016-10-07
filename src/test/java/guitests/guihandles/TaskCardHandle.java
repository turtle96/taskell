package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.stage.Stage;
import seedu.taskell.model.task.ReadOnlyTask;

/**
 * Provides a handle to a task card in the task list panel.
 */
public class TaskCardHandle extends GuiHandle {
<<<<<<< HEAD
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String EMAIL_FIELD_ID = "#email";
=======
    private static final String NAME_FIELD_ID = "#name";
    private static final String ADDRESS_FIELD_ID = "#address";
    private static final String PHONE_FIELD_ID = "#phone";
   
>>>>>>> remove-email-from-task

    private Node node;

    public TaskCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node){
        super(guiRobot, primaryStage, null);
        this.node = node;
    }

    protected String getTextFromLabel(String fieldId) {
        return getTextFromLabel(fieldId, node);
    }

    public String getDescription() {
        return getTextFromLabel(DESCRIPTION_FIELD_ID);
    }

    

    public boolean isSameTask(ReadOnlyTask task){
<<<<<<< HEAD
        return getDescription().equals(task.getDescription().description)
                && getEmail().equals(task.getEmail().value);
=======
        return getFullName().equals(task.getName().fullName) && getPhone().equals(task.getPhone().value)
                 && getAddress().equals(task.getAddress().value);
>>>>>>> remove-email-from-task
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TaskCardHandle) {
            TaskCardHandle handle = (TaskCardHandle) obj;
            return getDescription().equals(handle.getDescription());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getDescription() + " " + getDescription();
    }
}

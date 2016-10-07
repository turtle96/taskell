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
    private static final String ADDRESS_FIELD_ID = "#address";
=======
    private static final String NAME_FIELD_ID = "#name";
    private static final String PHONE_FIELD_ID = "#phone";
>>>>>>> remove-address-from-task
    private static final String EMAIL_FIELD_ID = "#email";

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

<<<<<<< HEAD
    public String getAddress() {
        return getTextFromLabel(ADDRESS_FIELD_ID);
=======
    public String getPhone() {
        return getTextFromLabel(PHONE_FIELD_ID);
>>>>>>> remove-address-from-task
    }

    public String getEmail() {
        return getTextFromLabel(EMAIL_FIELD_ID);
    }

    public boolean isSameTask(ReadOnlyTask task){
<<<<<<< HEAD
        return getDescription().equals(task.getDescription().description)
                && getEmail().equals(task.getEmail().value) && getAddress().equals(task.getAddress().value);
=======
        return getFullName().equals(task.getName().fullName) && getPhone().equals(task.getPhone().value)
                && getEmail().equals(task.getEmail().value);
>>>>>>> remove-address-from-task
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TaskCardHandle) {
            TaskCardHandle handle = (TaskCardHandle) obj;
<<<<<<< HEAD
            return getDescription().equals(handle.getDescription())
                    && getAddress().equals(handle.getAddress()); //TODO: compare the rest
=======
            return getFullName().equals(handle.getFullName()); //TODO: compare the rest
>>>>>>> remove-address-from-task
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return getDescription() + " " + getAddress();
=======
        return getFullName();
>>>>>>> remove-address-from-task
    }
}

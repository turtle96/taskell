package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.stage.Stage;
import seedu.taskell.model.task.ReadOnlyTask;

/**
 * Provides a handle to a person card in the person list panel.
 */
public class PersonCardHandle extends GuiHandle {
<<<<<<< HEAD
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String ADDRESS_FIELD_ID = "#address";
    private static final String PHONE_FIELD_ID = "#phone";
=======
    private static final String NAME_FIELD_ID = "#name";
    private static final String TASK_PRIORITY_FIELD_ID = "#taskPriority";
    private static final String TASK_DATE_FIELD_ID = "#taskDate";
>>>>>>> e6ab355e1524b899d613fc526a3bf4408cfee8b8
    private static final String EMAIL_FIELD_ID = "#email";

    private Node node;

    public PersonCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node){
        super(guiRobot, primaryStage, null);
        this.node = node;
    }

    protected String getTextFromLabel(String fieldId) {
        return getTextFromLabel(fieldId, node);
    }

    public String getDescription() {
        return getTextFromLabel(DESCRIPTION_FIELD_ID);
    }

    public String getTaskPriority() {
        return getTextFromLabel(TASK_PRIORITY_FIELD_ID);
    }

    public String getTaskDate() {
        return getTextFromLabel(TASK_DATE_FIELD_ID);
    }

    public String getEmail() {
        return getTextFromLabel(EMAIL_FIELD_ID);
    }

    public boolean isSamePerson(ReadOnlyTask person){
<<<<<<< HEAD
        return getDescription().equals(person.getDescription().description) && getPhone().equals(person.getPhone().value)
                && getEmail().equals(person.getEmail().value) && getAddress().equals(person.getAddress().value);
=======
        return getFullName().equals(person.getName().fullName) && getTaskDate().equals(person.getTaskDate().value)
                && getEmail().equals(person.getEmail().value) && getTaskPriority().equals(person.getTaskPriority().value);
>>>>>>> e6ab355e1524b899d613fc526a3bf4408cfee8b8
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PersonCardHandle) {
            PersonCardHandle handle = (PersonCardHandle) obj;
<<<<<<< HEAD
            return getDescription().equals(handle.getDescription())
                    && getAddress().equals(handle.getAddress()); //TODO: compare the rest
=======
            return getFullName().equals(handle.getFullName())
                    && getTaskPriority().equals(handle.getTaskPriority()); //TODO: compare the rest
>>>>>>> e6ab355e1524b899d613fc526a3bf4408cfee8b8
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return getDescription() + " " + getAddress();
=======
        return getFullName() + " " + getTaskPriority();
>>>>>>> e6ab355e1524b899d613fc526a3bf4408cfee8b8
    }
}

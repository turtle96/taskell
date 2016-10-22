package seedu.taskell.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.taskell.model.task.ReadOnlyTask;
import seedu.taskell.model.task.Task;

public class TaskCard extends UiPart{

    private static final String FXML = "TaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label tags;

    private ReadOnlyTask task;
    private int displayedIndex;

    public TaskCard(){

    }

    public static TaskCard load(ReadOnlyTask task, int displayedIndex){
        TaskCard card = new TaskCard();
        card.task = task;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    @FXML
    public void initialize() {
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().description);
        tags.setText(task.tagsString());
        
        if (task.getTaskType().equals(Task.FLOATING_TASK)) {
            startDate.setVisible(false);
            endDate.setVisible(false);
            startTime.setVisible(false);
            endTime.setVisible(false);
        } else {
            startDate.setText(task.getStartDate().taskDate);
            endDate.setText(task.getEndDate().taskDate);
            startTime.setText(task.getStartTime().taskTime);
            endTime.setText(task.getEndTime().taskTime);
        }
    }

    public HBox getLayout() {
        return cardPane;
    }

    @Override
    public void setNode(Node node) {
        cardPane = (HBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}

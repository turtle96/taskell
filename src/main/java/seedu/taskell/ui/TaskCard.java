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
    private Label taskStatus;
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
        startDate.setText(task.getStartDate().getDisplayDate());
        endDate.setText(task.getEndDate().getDisplayDate());
        startTime.setText(task.getStartTime().taskTime);
        endTime.setText(task.getEndTime().taskTime);
        taskStatus.setText(task.getTaskStatus().taskStatus);
        
        if (task.getTaskType().equals(Task.FLOATING_TASK)) {
            startDate.setVisible(false);
            endDate.setVisible(false);
            startTime.setVisible(false);
            endTime.setVisible(false);
        }
        
        System.out.println("TT: " + taskStatus);
        System.out.println("TASKCARD: " + task.getTaskStatus().taskStatus);
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

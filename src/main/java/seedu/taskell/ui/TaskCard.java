package seedu.taskell.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.taskell.model.task.ReadOnlyTask;
import seedu.taskell.model.task.Task;

public class TaskCard extends UiPart{

    private static final String FXML = "TaskListCard.fxml";
    private static final String WHITESPACE = " ";

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label taskType;
    @FXML
    private Label id;
    @FXML
    private Label taskDate;
    @FXML
    private Label taskPriority;
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
        taskType.setText(WHITESPACE);
        id.setText(displayedIndex + ". ");
        description.setText(task.getDescription().description);
        taskPriority.setText(WHITESPACE);
        tags.setText(task.tagsString());
        
        if (task.getTaskType().equals(Task.FLOATING_TASK)) {
            taskDate.setText(WHITESPACE);
            startTime.setText(WHITESPACE);
            endTime.setText(WHITESPACE);
        } else if (task.getTaskType().equals(Task.DEADLINE_TASK)) {
            taskDate.setText(task.getTaskDate().taskDate);
            startTime.setText(task.getEndTime().taskTime);  //To accustomed to display format on UI
            endTime.setText(WHITESPACE);
        } else {
            taskDate.setText(task.getTaskDate().taskDate);
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

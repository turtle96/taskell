package seedu.taskell.ui;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import seedu.taskell.commons.core.LogsCenter;
import seedu.taskell.commons.util.FxViewUtil;
import seedu.taskell.model.task.ReadOnlyTask;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The Display Panel of the App.
 */
public class DisplayPanel extends UiPart{

    private static Logger logger = LogsCenter.getLogger(DisplayPanel.class);
    
    public static final String RESULT_DISPLAY_ID = "resultDisplay";
    private static final String STATUS_BAR_STYLE_SHEET = "result-display";
    
    private TextArea display;

    /**
     * Constructor is kept private as {@link #load(AnchorPane)} is the only way to create a DisplayPanel.
     */
    private DisplayPanel() {

    }

    @Override
    public void setNode(Node node) {
        //not applicable
    }

    @Override
    public String getFxmlPath() {
        return null; //not applicable
    }

    /**
     * Factory method for creating a Browser Panel.
     * This method should be called after the FX runtime is initialized and in FX application thread.
     * @param placeholder The AnchorPane where the DisplayPanel must be inserted
     */
    public static DisplayPanel load(AnchorPane placeholder){
        logger.info("Initializing display panel");
        DisplayPanel displayPanel = new DisplayPanel();
        displayPanel.display = new TextArea();
        displayPanel.display.setEditable(false);
        displayPanel.display.setId(RESULT_DISPLAY_ID);
        displayPanel.display.getStyleClass().removeAll();
        displayPanel.display.getStyleClass().add(STATUS_BAR_STYLE_SHEET);
        
        FxViewUtil.applyAnchorBoundaryParameters(displayPanel.display, 0.0, 0.0, 0.0, 0.0);
        placeholder.getChildren().add(displayPanel.display);
        
        displayPanel.display.setText("Welcome to Taskell!");
       
        return displayPanel;
    }
    
    public void loadList(ArrayList<String> list) {
        display.setText("");
        if (list.isEmpty()) {
            display.setText("No commands available for undo.");
        }
        else {
            for (int i=0; i<list.size(); i++) {
                display.appendText(i+1 + ". " + list.get(i) + "\n");
            }
        }
    }

}

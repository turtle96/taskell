package seedu.taskell.ui;

import javafx.scene.Node;
import jfxtras.scene.control.agenda.Agenda;

public class CalendarView extends UiPart {
    
    public void load() {
        // create Agenda
        Agenda agenda = new Agenda();

        // add an appointment
        agenda.appointments().addAll();

    }

    @Override
    public void setNode(Node node) {
        //not applicable
    }

    @Override
    public String getFxmlPath() {
        return null;    //not applicable
    }

}

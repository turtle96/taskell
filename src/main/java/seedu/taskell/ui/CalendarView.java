package seedu.taskell.ui;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.Scene;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.AppointmentImpl;

public class CalendarView extends UiPart {
    
    private void load() {
        // create Agenda
        Agenda agenda = new Agenda();

     // add an appointment
        agenda.appointments().addAll();

       // show it
       primaryStage.setScene(new Scene(agenda, 800, 600));
       primaryStage.show();
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

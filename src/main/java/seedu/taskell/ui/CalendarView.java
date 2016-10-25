package seedu.taskell.ui;

import jfxtras.scene.control.agenda.Agenda;

/** This class holds the necessary elements to display calendar UI via Agenda API from jfxtras
 * */

public class CalendarView {
    
    private Agenda agenda;
    
    public CalendarView() {
        agenda = new Agenda();
    }
    
    public Agenda getAgenda() {
        return agenda;
    }

}

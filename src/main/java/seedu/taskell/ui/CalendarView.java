/** @@author A0142130A **/
package seedu.taskell.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import seedu.taskell.model.Model;
import seedu.taskell.model.ReadOnlyTaskManager;
import seedu.taskell.model.TaskManager;
import seedu.taskell.model.task.ReadOnlyTask;
import seedu.taskell.model.task.UniqueTaskList;

/** This class holds the necessary elements to display calendar UI via Agenda API from jfxtras
 * */

public class CalendarView {
    
    private static Model model;
    
    private Agenda agenda;
    
    public CalendarView() {
        agenda = new Agenda();
        setAgendaProperties();
        loadTasks();
    }
    
    public static void setData(Model m) {
        model = m;
    }
    
    public Agenda getAgenda() {
        return agenda;
    }
    
    private void setAgendaProperties() {
        agenda.setAllowDragging(false);
        agenda.setAllowResize(false);
        agenda.setFocusTraversable(false);
        
        agenda.setStyle("-fx-font-size: 12pt");
    }
    
    public void loadTasks() {
        ReadOnlyTaskManager taskManager = model.getTaskManager();
        UniqueTaskList taskList = taskManager.getUniqueTaskList();
        
        ArrayList<Appointment> appointments = new ArrayList<>();
        int i=0;
        
        for (ReadOnlyTask task: taskList) {
            appointments.add(new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(LocalDateTime.now().plusDays(i))
                    .withEndLocalDateTime(LocalDateTime.now().plusDays(i).plusHours(4).plusMinutes(30))
                    .withSummary(task.getDescription().description)
                    .withAppointmentGroup(
                            new Agenda.AppointmentGroupImpl().withStyleClass("group"+i)));
            i++;
        }
        
        
        
        

        agenda.appointments().addAll(appointments);
        
        //"-fx-background-color: #EC407A; -fx-fill: #EC407A;"
    }

}

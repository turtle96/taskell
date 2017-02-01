/** @@author A0142130A **/
package seedu.taskell.ui;

import java.util.ArrayList;

import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import seedu.taskell.commons.core.UnmodifiableObservableList;
import seedu.taskell.model.Model;
import seedu.taskell.model.task.ReadOnlyTask;
import seedu.taskell.model.task.Task;
import seedu.taskell.model.task.TaskStatus;

/** This class holds the necessary elements to display calendar UI via Agenda API from jfxtras
 * */

public class CalendarView {
    
    private static final int APPOINTMENT_MAX_COLOUR = 24;

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
        agenda.appointments().clear();
        loadTasks();
        return agenda;
    }
    
    private void setAgendaProperties() {
        agenda.setAllowDragging(false);
        agenda.setAllowResize(false);
        agenda.setFocusTraversable(false);
        
        agenda.setStyle("-fx-font-size: 12pt");
    }
    
    /** Gets current filtered task list from model and updates agenda's data
     * */
    public void loadTasks() {
        agenda.appointments().clear();

        UnmodifiableObservableList<ReadOnlyTask> taskList = model.getFilteredTaskList();
        
        ArrayList<Appointment> appointments = new ArrayList<>();
        int i=1;
        
        for (ReadOnlyTask task: taskList) {
            
            if (isValidEventTask(task)) {
                appointments.add(new Agenda.AppointmentImplLocal()
                        .withStartLocalDateTime(task.getStartDate().toLocalDateTime(task.getStartTime()))
                        .withEndLocalDateTime(task.getEndDate().toLocalDateTime(task.getEndTime()))
                        .withSummary(String.valueOf(i))
                        .withAppointmentGroup(
                                new Agenda.AppointmentGroupImpl().withStyleClass("group"+i)));
            }
            
            i++;
            i%=APPOINTMENT_MAX_COLOUR;
        }
        
        agenda.appointments().addAll(appointments);
        
    }

    /** Checks that task is incomplete status and is type EventTask
     * */
    private boolean isValidEventTask(ReadOnlyTask task) {
        return task.getTaskStatus().toString().equals(TaskStatus.INCOMPLETE) 
                && task.getTaskType().equals(Task.EVENT_TASK);
    }

}

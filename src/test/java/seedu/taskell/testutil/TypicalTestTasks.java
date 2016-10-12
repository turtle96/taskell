package seedu.taskell.testutil;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.TaskManager;
import seedu.taskell.model.task.*;

/**
 *
 */
public class TypicalTestTasks {

    public static TestTask archivePastEmails, borrowBooks, collectParcel, discardBooks, editPowerpoint, holdMeeting, inspectWarehouse;
    
    public TypicalTestTasks() {
        try {
            archivePastEmails =  new TaskBuilder().withDescription("Archive past emails").withDate("8-8-16").withTime("12pm").withPriority("3").withTags("personal").build();
            borrowBooks =  new TaskBuilder().withDescription("borrorBooks").withDate("9-8-16").withTime("12am").withPriority("1").withTags("friend").build();
            collectParcel =  new TaskBuilder().withDescription("collectParcel").withDate("9-9-16").withTime("2am").withPriority("1").withTags("friend").build();
            discardBooks =  new TaskBuilder().withDescription("discard old books").withDate("19-9-16").withTime("4am").withPriority("1").withTags("home").build();
            editPowerpoint =  new TaskBuilder().withDescription("edit 2101 powerpoint").withDate("8-9-16").withTime("6am").withPriority("5").withTags("homework").build();
            
            //Manually added
            holdMeeting =  new TaskBuilder().withDescription("hold project meeting").withDate("8-10-16").withTime("11am").withPriority("5").withTags("homework").build();
            inspectWarehouse =  new TaskBuilder().withDescription("inspect ware house").withDate("28-10-16").withTime("11am").withPriority("4").withTags("work").build();
            
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) {

        try {
            ab.addTask(new Task(archivePastEmails));
            ab.addTask(new Task(borrowBooks));
            ab.addTask(new Task(collectParcel));
            ab.addTask(new Task(discardBooks));
            ab.addTask(new Task(editPowerpoint));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "not possible";
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{archivePastEmails, borrowBooks, collectParcel, discardBooks, editPowerpoint};
    }

    public TaskManager getTypicalTaskManager(){
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}

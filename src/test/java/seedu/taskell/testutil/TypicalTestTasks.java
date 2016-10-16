package seedu.taskell.testutil;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.TaskManager;
import seedu.taskell.model.task.*;

/**
 *
 */
public class TypicalTestTasks {

    public static TestTask alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestTasks() {
        try {
            alice =  new TaskBuilder().withDescription("Alice Pauline").withTaskPriority("123, Jurong West Ave 6, #08-111")
                    .withTaskTime("alice@gmail.com").withTaskDate("1-1-2015")
                    .withTags("friends").build();
            benson = new TaskBuilder().withDescription("Benson Meier").withTaskPriority("311, Clementi Ave 2, #02-25")
                    .withTaskTime("johnd@gmail.com").withTaskDate("1-1-2015")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withDescription("Carl Kurz").withTaskDate("1-1-2015").withTaskTime("heinz@yahoo.com").withTaskPriority("wall street").build();
            daniel = new TaskBuilder().withDescription("Daniel Meier").withTaskDate("1-1-2015").withTaskTime("cornelia@google.com").withTaskPriority("10th street").build();
            elle = new TaskBuilder().withDescription("Elle Meyer").withTaskDate("1-1-2015").withTaskTime("werner@gmail.com").withTaskPriority("michegan ave").build();
            fiona = new TaskBuilder().withDescription("Fiona Kunz").withTaskDate("1-1-2015").withTaskTime("lydia@gmail.com").withTaskPriority("little tokyo").build();
            george = new TaskBuilder().withDescription("George Best").withTaskDate("1-1-2015").withTaskTime("anna@google.com").withTaskPriority("4th street").build();

            //Manually added
            hoon = new TaskBuilder().withDescription("Hoon Meier").withTaskDate("1-1-2015").withTaskTime("stefan@mail.com").withTaskPriority("little india").build();
            ida = new TaskBuilder().withDescription("Ida Mueller").withTaskDate("1-1-2015").withTaskTime("hans@google.com").withTaskPriority("chicago ave").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager ab) {

        try {
            ab.addTask(new Task(alice));
            ab.addTask(new Task(benson));
            ab.addTask(new Task(carl));
            ab.addTask(new Task(daniel));
            ab.addTask(new Task(elle));
            ab.addTask(new Task(fiona));
            ab.addTask(new Task(george));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "not possible";
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public TaskManager getTypicalTaskManager(){
        TaskManager ab = new TaskManager();
        loadTaskManagerWithSampleData(ab);
        return ab;
    }
}

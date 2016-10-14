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
            alice =  new TaskBuilder().withName("Alice Pauline").withTaskPriority("123, Jurong West Ave 6, #08-111")
                    .withEmail("alice@gmail.com").withTaskDate("85355255")
                    .withTags("friends").build();
            benson = new TaskBuilder().withName("Benson Meier").withTaskPriority("311, Clementi Ave 2, #02-25")
                    .withEmail("johnd@gmail.com").withTaskDate("98765432")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withName("Carl Kurz").withTaskDate("95352563").withEmail("heinz@yahoo.com").withTaskPriority("wall street").build();
            daniel = new TaskBuilder().withName("Daniel Meier").withTaskDate("87652533").withEmail("cornelia@google.com").withTaskPriority("10th street").build();
            elle = new TaskBuilder().withName("Elle Meyer").withTaskDate("9482224").withEmail("werner@gmail.com").withTaskPriority("michegan ave").build();
            fiona = new TaskBuilder().withName("Fiona Kunz").withTaskDate("9482427").withEmail("lydia@gmail.com").withTaskPriority("little tokyo").build();
            george = new TaskBuilder().withName("George Best").withTaskDate("9482442").withEmail("anna@google.com").withTaskPriority("4th street").build();

            //Manually added
            hoon = new TaskBuilder().withName("Hoon Meier").withTaskDate("8482424").withEmail("stefan@mail.com").withTaskPriority("little india").build();
            ida = new TaskBuilder().withName("Ida Mueller").withTaskDate("8482131").withEmail("hans@google.com").withTaskPriority("chicago ave").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadAddressBookWithSampleData(TaskManager ab) {

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

    public TaskManager getTypicalAddressBook(){
        TaskManager ab = new TaskManager();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}

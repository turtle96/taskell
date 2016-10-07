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
<<<<<<< HEAD
            alice =  new TaskBuilder().withDescription("Archive past emails").withAddress("123, Jurong West Ave 6, #08-111")
                    .withEmail("alice@gmail.com")
                    .withTags("friends").build();
            benson = new TaskBuilder().withDescription("Borrow books").withAddress("311, Clementi Ave 2, #02-25")
                    .withEmail("johnd@gmail.com")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withDescription("Collect parcel").withEmail("heinz@yahoo.com").withAddress("wall street").build();
            daniel = new TaskBuilder().withDescription("Discard books").withEmail("cornelia@google.com").withAddress("10th street").build();
            elle = new TaskBuilder().withDescription("Edit powerpoint").withEmail("werner@gmail.com").withAddress("michegan ave").build();
            fiona = new TaskBuilder().withDescription("Fix bugs").withEmail("lydia@gmail.com").withAddress("little tokyo").build();
            george = new TaskBuilder().withDescription("Get new uniform").withEmail("anna@google.com").withAddress("4th street").build();

            //Manually added
            hoon = new TaskBuilder().withDescription("Hold meeting").withEmail("stefan@mail.com").withAddress("little india").build();
            ida = new TaskBuilder().withDescription("Inspect warehouse").withEmail("hans@google.com").withAddress("chicago ave").build();
=======
            alice =  new TaskBuilder().withName("Alice Pauline")
                    .withEmail("alice@gmail.com").withPhone("85355255")
                    .withTags("friends").build();
            benson = new TaskBuilder().withName("Benson Meier")
                    .withEmail("johnd@gmail.com").withPhone("98765432")
                    .withTags("owesMoney", "friends").build();
            carl = new TaskBuilder().withName("Carl Kurz").withPhone("95352563").withEmail("heinz@yahoo.com").build();
            daniel = new TaskBuilder().withName("Daniel Meier").withPhone("87652533").withEmail("cornelia@google.com").build();
            elle = new TaskBuilder().withName("Elle Meyer").withPhone("9482224").withEmail("werner@gmail.com").build();
            fiona = new TaskBuilder().withName("Fiona Kunz").withPhone("9482427").withEmail("lydia@gmail.com").build();
            george = new TaskBuilder().withName("George Best").withPhone("9482442").withEmail("anna@google.com").build();

            //Manually added
            hoon = new TaskBuilder().withName("Hoon Meier").withPhone("8482424").withEmail("stefan@mail.com").build();
            ida = new TaskBuilder().withName("Ida Mueller").withPhone("8482131").withEmail("hans@google.com").build();
>>>>>>> remove-address-from-task
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

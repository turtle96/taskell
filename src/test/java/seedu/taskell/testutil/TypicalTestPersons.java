package seedu.taskell.testutil;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.TaskManager;
import seedu.taskell.model.task.*;

/**
 *
 */
public class TypicalTestPersons {

    public static TestPerson alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestPersons() {
        try {
            alice =  new PersonBuilder().withName("Alice Pauline").withTaskPriority("123, Jurong West Ave 6, #08-111")
                    .withEmail("alice@gmail.com").withTaskDate("85355255")
                    .withTags("friends").build();
            benson = new PersonBuilder().withName("Benson Meier").withTaskPriority("311, Clementi Ave 2, #02-25")
                    .withEmail("johnd@gmail.com").withTaskDate("98765432")
                    .withTags("owesMoney", "friends").build();
            carl = new PersonBuilder().withName("Carl Kurz").withTaskDate("95352563").withEmail("heinz@yahoo.com").withTaskPriority("wall street").build();
            daniel = new PersonBuilder().withName("Daniel Meier").withTaskDate("87652533").withEmail("cornelia@google.com").withTaskPriority("10th street").build();
            elle = new PersonBuilder().withName("Elle Meyer").withTaskDate("9482224").withEmail("werner@gmail.com").withTaskPriority("michegan ave").build();
            fiona = new PersonBuilder().withName("Fiona Kunz").withTaskDate("9482427").withEmail("lydia@gmail.com").withTaskPriority("little tokyo").build();
            george = new PersonBuilder().withName("George Best").withTaskDate("9482442").withEmail("anna@google.com").withTaskPriority("4th street").build();

            //Manually added
            hoon = new PersonBuilder().withName("Hoon Meier").withTaskDate("8482424").withEmail("stefan@mail.com").withTaskPriority("little india").build();
            ida = new PersonBuilder().withName("Ida Mueller").withTaskDate("8482131").withEmail("hans@google.com").withTaskPriority("chicago ave").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadAddressBookWithSampleData(TaskManager ab) {

        try {
            ab.addPerson(new Task(alice));
            ab.addPerson(new Task(benson));
            ab.addPerson(new Task(carl));
            ab.addPerson(new Task(daniel));
            ab.addPerson(new Task(elle));
            ab.addPerson(new Task(fiona));
            ab.addPerson(new Task(george));
        } catch (UniqueTaskList.DuplicatePersonException e) {
            assert false : "not possible";
        }
    }

    public TestPerson[] getTypicalPersons() {
        return new TestPerson[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public TaskManager getTypicalAddressBook(){
        TaskManager ab = new TaskManager();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}

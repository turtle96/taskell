package seedu.taskell.testutil;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.tag.Tag;
import seedu.taskell.model.task.*;

/**
 *
 */
public class PersonBuilder {

    private TestPerson person;

    public PersonBuilder() {
        this.person = new TestPerson();
    }

    public PersonBuilder withName(String name) throws IllegalValueException {
        this.person.setName(new Description(name));
        return this;
    }

    public PersonBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            person.getTags().add(new Tag(tag));
        }
        return this;
    }

    public PersonBuilder withAddress(String address) throws IllegalValueException {
        this.person.setAddress(new TaskPriority(address));
        return this;
    }

    public PersonBuilder withPhone(String phone) throws IllegalValueException {
        this.person.setPhone(new TaskDate(phone));
        return this;
    }

    public PersonBuilder withEmail(String email) throws IllegalValueException {
        this.person.setEmail(new TaskTime(email));
        return this;
    }

    public TestPerson build() {
        return this.person;
    }

}

package seedu.taskell.testutil;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.AddressBook;
import seedu.taskell.model.person.Person;
import seedu.taskell.model.person.UniquePersonList;
import seedu.taskell.model.tag.Tag;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").withTag("Friend").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder(AddressBook addressBook){
        this.addressBook = addressBook;
    }

    public AddressBookBuilder withPerson(Person person) throws UniquePersonList.DuplicatePersonException {
        addressBook.addPerson(person);
        return this;
    }

    public AddressBookBuilder withTag(String tagName) throws IllegalValueException {
        addressBook.addTag(new Tag(tagName));
        return this;
    }

    public AddressBook build(){
        return addressBook;
    }
}

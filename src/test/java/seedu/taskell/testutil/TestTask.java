package seedu.taskell.testutil;

import seedu.taskell.model.task.*;
import seedu.taskell.model.tag.UniqueTagList;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Address address;
    
    private Phone phone;
    private UniqueTagList tags;

    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Phone getPhone() {
        return phone;
    }

    

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        sb.append("p/" + this.getPhone().value + " ");
        
        sb.append("a/" + this.getAddress().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}

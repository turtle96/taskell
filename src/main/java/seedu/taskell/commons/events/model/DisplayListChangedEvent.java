package seedu.taskell.commons.events.model;

import java.util.ArrayList;

import seedu.taskell.commons.events.BaseEvent;

public class DisplayListChangedEvent extends BaseEvent {
    
    private ArrayList<String> list;
    
    public DisplayListChangedEvent(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
    
    public ArrayList<String> getList() {
        return list;
    }

}

package seedu.taskell.commons.events.storage;

import seedu.taskell.commons.events.BaseEvent;

//@@author A0139257X-reused
public class DuplicateDataExceptionEvent extends BaseEvent{

    public Exception exception;

    public DuplicateDataExceptionEvent(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString(){
        return exception.toString();
    }
}
//@@author
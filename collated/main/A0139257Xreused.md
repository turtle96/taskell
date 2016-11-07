# A0139257Xreused
###### \java\seedu\taskell\commons\events\storage\DuplicateDataExceptionEvent.java
``` java
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
```
###### \java\seedu\taskell\commons\util\CollectionUtil.java
``` java
    /**
     * Returns a set with unique items only
     */
    public static Set<?> generateUniqueList(Collection<?> items) {
        Set<Object> uniqueSet = new HashSet<>();
        for (Object item : items) {
            uniqueSet.add(item); // see Set documentation
        }
        return uniqueSet;
    }
```
###### \java\seedu\taskell\logic\commands\AddCommand.java
``` java
    @Override
    public CommandResult execute() {
        assert model != null;
        try {
            model.addTask(toAdd);
            history.addTask(toAdd);
            jumpToNewTaskIndex();
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            history.deleteLatestCommand();
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }
    
    private void jumpToNewTaskIndex() {
        int indexOfNewTask = model.getFilteredTaskList().size()-1;
        jumpToIndex(indexOfNewTask);
    }

}
```
###### \java\seedu\taskell\logic\commands\Command.java
``` java
    /**
     * Raises an event to jump to the given index
     */
    protected void jumpToIndex(int index) {
        EventsCenter.getInstance().post(new JumpToListRequestEvent(index));
    }
```
###### \java\seedu\taskell\storage\XmlSerializableTaskManager.java
``` java
    @SuppressWarnings("unchecked")
    @Override
    public UniqueTagList getUniqueTagList() {
        try {
            return new UniqueTagList(tags);
        } catch (UniqueTagList.DuplicateTagException e) {
            EventsCenter.getInstance().post(new DuplicateDataExceptionEvent(e));
            logger.info("Duplicated tags will be removed from UniqueTagList upon adding a new task");
            return new UniqueTagList((Set<Tag>)CollectionUtil.generateUniqueList(tags));
        }
    }

    @Override
    public UniqueTaskList getUniqueTaskList() {
        UniqueTaskList lists = new UniqueTaskList();
        for (XmlAdaptedTask p : tasks) {
            try {
                lists.add(p.toModelType());
            } catch (IllegalValueException e) {
                EventsCenter.getInstance().post(new DuplicateDataExceptionEvent(e));
                logger.info("Duplicated task will be removed upon adding a new task");
            }
        }
        return lists;
    }
```

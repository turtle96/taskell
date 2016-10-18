//package seedu.taskell.logic.commands;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import seedu.taskell.commons.core.Messages;
//import seedu.taskell.commons.core.UnmodifiableObservableList;
//import seedu.taskell.commons.exceptions.IllegalValueException;
//import seedu.taskell.model.tag.Tag;
//import seedu.taskell.model.tag.UniqueTagList;
//import seedu.taskell.model.task.Description;
//import seedu.taskell.model.task.ReadOnlyTask;
//import seedu.taskell.model.task.Task;
//import seedu.taskell.model.task.TaskDate;
//import seedu.taskell.model.task.TaskPriority;
//import seedu.taskell.model.task.TaskTime;
//import seedu.taskell.model.task.UniqueTaskList;
//import seedu.taskell.model.task.UniqueTaskList.TaskNotFoundException;
//
///**
// * Edits a task identified using it's last displayed index from the task manager.
// */
//public class EditDateCommand extends Command {
//    public static final String COMMAND_WORD = "edit-description";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD
//            + ": Edits the description task identified by the index number used in the last task listing.\n"
//            + "Parameters: INDEX (must be a positive integer) NEW_DESCRIPTION [t/TAG]\n"
//            + "Example: " + COMMAND_WORD + " 1 buy cake t/chicken";
//
//    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Old Task: %1$s \nNewTask: %2$s";
//
//    public final int targetIndex;
//    private final Task newTask;
//    private final String description;
//    
//    public EditDescriptionCommand(int targetIndex, String description,String taskType, String taskDate,  String startTime, String endTime, String priority, Set<String> tags) throws IllegalValueException{
//        this.targetIndex = targetIndex;
//        this.description = description;
//        final Set<Tag> tagSet = new HashSet<>();
//        for (String tagName : tags) {
//            tagSet.add(new Tag(tagName));
//        }
//        this.newTask = new Task(
//                new Description(description),taskType,new TaskDate(taskDate), new TaskTime(startTime),new TaskTime(endTime),
//                new TaskPriority(priority), new UniqueTagList(tagSet)
//        );
//    }
//    
//    @Override
//    public CommandResult execute() {
//
//        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();
//
//        if (lastShownList.size() < targetIndex) {
//            indicateAttemptToExecuteIncorrectCommand();
//            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
//        }
//
//        ReadOnlyTask taskToEdit = lastShownList.get(targetIndex - 1);
//
//        try {
//            model.editTask(taskToEdit,newTask);
//        } catch (TaskNotFoundException pnfe) {
//            assert false : "The target task cannot be missing";
//        } catch (UniqueTaskList.DuplicateTaskException e) {
//            return new CommandResult(AddCommand.MESSAGE_DUPLICATE_TASK);
//        }
//
//        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToEdit, newTask));
//    }
//}
package seedu.taskell.logic.parser;

import static seedu.taskell.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskell.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.taskell.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.commons.util.StringUtil;
import seedu.taskell.logic.commands.*;
import seedu.taskell.logic.commands.list.ListAllCommand;
import seedu.taskell.logic.commands.list.ListCommand;
import seedu.taskell.logic.commands.list.ListDateCommand;
import seedu.taskell.logic.commands.list.ListDoneCommand;
import seedu.taskell.logic.commands.list.ListPriorityCommand;
import seedu.taskell.model.History;
import seedu.taskell.model.HistoryManager;
import seedu.taskell.model.tag.Tag;
import seedu.taskell.model.task.Description;
import seedu.taskell.model.task.FloatingTask;
import seedu.taskell.model.task.RecurringType;
import seedu.taskell.model.task.Task;
import seedu.taskell.model.task.TaskDate;
import seedu.taskell.model.task.TaskPriority;
import seedu.taskell.model.task.TaskTime;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    private static final Pattern KEYWORDS_ARGS_FORMAT = Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one

    private static final Pattern TASK_DATA_ARGS_FORMAT = // '/' forward slashes
                                                         // are reserved for
                                                         // delimiter prefixes
            Pattern.compile("(?<description>[^/]+)" + " (?<isTaskTypePrivate>p?)p/(?<taskType>[^/]+)"
                    + " (?<isTaskDatePrivate>p?)p/(?<startDate>[^/]+)" + " (?<isStartPrivate>p?)e/(?<startTime>[^/]+)"
                    + " (?<isEndPrivate>p?)e/(?<endTime>[^/]+)"
                    + " (?<isTaskPriorityPrivate>p?)a/(?<taskPriority>[^/]+)"
                    + " (?<isTaskCompletePrivate>p?)a/(?<taskComplete>[^/]+)" + "(?<tagArguments>(?: t/[^/]+)*)"); // variable
                                                                                                                   // number
    private static final String BY = "by";
    private static final String ON = "on";
    private static final String AT = "at";
    private static final String FROM = "from";
    private static final String TO = "to";
    
    private static final int NUM_QUEUE = 6;
    private static final int DESCRIPTION_QUEUE = 0;
    private static final int BY_QUEUE = 1;
    private static final int ON_QUEUE = 2;
    private static final int AT_QUEUE = 3;
    private static final int FROM_QUEUE = 4;
    private static final int TO_QUEUE = 5;
    
    private static final int NUM_BOOLEAN_TASK_COMPONENT = 5;
    private static final int START_DATE_COMPONENT = 0;
    private static final int END_DATE_COMPONENT = 1;
    private static final int START_TIME_COMPONENT = 2;
    private static final int END_TIME_COMPONENT = 3;
    private static final int RECURRING_COMPONENT = 4;
    
    private static final int NUM_TASK_COMPONENT = 8;
    private static final int DESCRIPTION = 0;
    private static final int START_DATE = 1;
    private static final int END_DATE = 2;
    private static final int START_TIME = 3;
    private static final int END_TIME = 4;
    private static final int TASK_PRIORITY = 5;
    private static final int RECURRING_TYPE = 6;
    private static final int TAG = 7;
    
    private static final String ST = "st:";
    private static final String ET = "et:";
    private static final String SD = "sd:";
    private static final String ED = "ed:";
    private static final String DESC = "desc:";
    private static final String P = "p:";
    
    private static History history;

    private boolean hasChangedDescription = false;
    private boolean hasChangedStartDate = false;
    private boolean hasChangedEndDate = false;
    private boolean hasChangedStartTime = false;
    private boolean hasChangedEndTime = false;
    private boolean hasChangedPriority = false;
    
    
    private ArrayList<Queue<String>> partitionQueue;
    private boolean[] hasTaskComponentArray;
    private String[] taskComponentArray;
    
    
    
    
    
    

    public Parser() {
        history = HistoryManager.getInstance();
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput
     *            full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        saveToHistory(userInput, commandWord);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case SelectCommand.COMMAND_WORD:
            return prepareSelect(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case EditCommand.COMMAND_WORD:
            return prepareEdit(arguments);

        case ClearCommand.COMMAND_WORD:
            return prepareClear(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case FindTagCommand.COMMAND_WORD:
            return prepareFindByTag(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListAllCommand.COMMAND_WORD:
            return new ListAllCommand();

        case ListDoneCommand.COMMAND_WORD:
            return new ListDoneCommand();

        case ListDateCommand.COMMAND_WORD:
            return prepareListDate(arguments);

        case ListPriorityCommand.COMMAND_WORD:
            return prepareListPriority(arguments);

        case UndoCommand.COMMAND_WORD:
            return prepareUndo(arguments);

        case SaveStorageLocationCommand.COMMAND_WORD:
            return prepareSaveStorageLocation(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case DoneCommand.COMMAND_WORD:
            return prepareDone(arguments);

        case UndoneCommand.COMMAND_WORD:
            return prepareUndone(arguments);

        case ViewHistoryCommand.COMMAND_WORD_1:
            return new ViewHistoryCommand();

        case ViewHistoryCommand.COMMAND_WORD_2:
            return new ViewHistoryCommand();

        case ViewCalendarCommand.COMMAND_WORD_1:
            return new ViewCalendarCommand();

        case ViewCalendarCommand.COMMAND_WORD_2:
            return new ViewCalendarCommand();

        default:
            return new IncorrectCommand(MESSAGE_UNKNOWN_COMMAND);
        }
    }


    /** @@author A0142130A **/
    
    private Command prepareClear(String arguments) {
        if (arguments.isEmpty()) {
            return new ClearCommand();
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
        
    }
    
    /** @@author **/

    /** @@author A0142130A **/

    /**
     * if type of command is undoable, saves to history for undoing
     */
    private void saveToHistory(String userInput, final String commandWord) {
        if (commandWord.equals(AddCommand.COMMAND_WORD) || commandWord.equals(DeleteCommand.COMMAND_WORD)
                || commandWord.contains(UndoCommand.EDIT)) {
            IncorrectCommand.setIsUndoableCommand(true);
            history.addCommand(userInput, commandWord);
        } else {
            IncorrectCommand.setIsUndoableCommand(false);
        }
    }

    /** @@author **/

    // @@author A0142073R

    private Command prepareListDate(String arguments) {

        if (arguments.isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDateCommand.MESSAGE_USAGE));
        }

        StringTokenizer st = new StringTokenizer(arguments.trim(), " ");
        String date = st.nextToken();

        if (st.hasMoreTokens()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDateCommand.MESSAGE_USAGE));
        }
        if (!TaskDate.isValidDate(date)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDateCommand.MESSAGE_USAGE));
        }
        return new ListDateCommand(date);
    }

    private Command prepareListPriority(String args) {

        if (args.isEmpty()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListPriorityCommand.MESSAGE_USAGE));
        }

        StringTokenizer st = new StringTokenizer(args.trim(), " ");
        String intValue = st.nextToken();
        
        if (st.hasMoreTokens()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListPriorityCommand.MESSAGE_USAGE));
        }
        if (!isInt(intValue)) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListPriorityCommand.MESSAGE_USAGE));
        }
        
        int targetIdx = Integer.valueOf(intValue);
        if (targetIdx < Integer.valueOf(TaskPriority.DEFAULT_PRIORITY) || targetIdx > Integer.valueOf(TaskPriority.HIGH_PRIORITY)) {
            return new IncorrectCommand(
                    String.format(TaskPriority.MESSAGE_TASK_PRIORITY_CONSTRAINTS, ListPriorityCommand.MESSAGE_USAGE));
        } else
            return new ListPriorityCommand(intValue);
        
    }

    /**
     * Parses arguments in the context of the edit command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareEdit(String args) {
        String description = "default";
        String startDate = TaskDate.DEFAULT_DATE;
        String endDate = TaskDate.DEFAULT_DATE;
        String startTime = TaskTime.DEFAULT_START_TIME;
        String endTime = TaskTime.DEFAULT_END_TIME;
        String taskPriority = TaskPriority.DEFAULT_PRIORITY;

        if (args.isEmpty()) {
            // UndoCommand.deletePreviousCommand();
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        
        StringTokenizer st = new StringTokenizer(args.trim(), " ");
        String intValue = st.nextToken();
        if (!isInt(intValue)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, EditCommand.MESSAGE_USAGE));
        }
        
        int targetIdx = Integer.valueOf(intValue);
        hasChangedDescription = false;
        hasChangedStartDate = false;
        hasChangedEndDate = false;
        hasChangedStartTime = false;
        hasChangedEndTime = false;
        hasChangedPriority = false;
        boolean lastChar = false;
        
        if (!st.hasMoreTokens()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        while (st.hasMoreTokens()) {
            String parts = st.nextToken();
            // System.out.println("Parts is " + parts);
            if (parts.equals(DESC)) {
                if (hasChangedDescription == true) {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                // System.out.println("I am inside desc");
                String desc = " ";
                while (!(parts.equals(ST) || parts.equals(ET) || parts.equals(SD)
                        || parts.equals("ed") | parts.equals(P)) && st.hasMoreTokens()) {
                    desc += (parts + " ");
                    parts = st.nextToken();
                    hasChangedDescription = true;
                }
                if (!(parts.equals(ST) || parts.equals(ET) || parts.equals(SD)
                        || parts.equals("ed") | parts.equals(P))) {
                    // System.out.println("I am here to add the last is
                    // "+parts);
                    desc += parts;
                    lastChar = true;
                }
                desc = desc.trim();
                if (Description.isValidDescription(desc)) {
                    System.out.println("The new desc is valid");
                    description = desc.substring(5);
                    hasChangedDescription = true;
                }
                // System.out.println("Description End: " + desc);
            }
            if (parts.equals(ST)) {
                if (hasChangedStartTime == true) {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                // System.out.println("I am inside start time");
                if (st.hasMoreTokens()) {
                    String startT = st.nextToken();
                    if (TaskTime.isValidTime(startT)) {
                        startTime = startT.trim();
                        hasChangedStartTime = true;
                    } else {
                        return new IncorrectCommand(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskTime.MESSAGE_TASK_TIME_CONSTRAINTS));
                    }
                } else {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                // System.out.println("Start time is " + startTime);
            }
            if (parts.equals(ET)) {
                if (hasChangedEndTime == true) {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                if (st.hasMoreTokens()) {
                    String endT = st.nextToken();
                    if (TaskTime.isValidTime(endT)) {
                        endTime = endT.trim();
                        hasChangedEndTime = true;
                    } else {
                        return new IncorrectCommand(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskTime.MESSAGE_TASK_TIME_CONSTRAINTS));
                    }
                } else {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                // System.out.println("End time is " + endTime);
            }
            if (parts.equals(SD)) {
                if (hasChangedStartDate == true) {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                if (st.hasMoreTokens()) {
                    String startD = st.nextToken();
                    if (TaskDate.isValidDate(startD)) {
                        startDate = startD.trim();
                        hasChangedStartDate = true;
                    } else {
                        return new IncorrectCommand(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDate.MESSAGE_TASK_DATE_CONSTRAINTS));
                    }
                } else {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                // System.out.println("Start Date is " + startDate);
            }
            if (parts.equals(ED)) {
                if (hasChangedEndDate == true) {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                if (st.hasMoreTokens()) {
                    String endD = st.nextToken();
                    if (TaskDate.isValidDate(endD)) {
                        endDate = endD.trim();
                        hasChangedEndDate = true;
                    } else {
                        return new IncorrectCommand(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDate.MESSAGE_TASK_DATE_CONSTRAINTS));
                    }
                } else {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                // System.out.println("End Date is " + endDate);
            }
            if (parts.equals(P)) {
                if (hasChangedPriority == true) {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                if (st.hasMoreTokens()) {
                    String p = st.nextToken();
                    if (TaskPriority.isValidPriority(p)) {
                        taskPriority = p.trim();
                        hasChangedPriority = true;
                    } else {
                        return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                TaskPriority.MESSAGE_TASK_PRIORITY_CONSTRAINTS));
                    }
                } else {
                    return new IncorrectCommand(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                // System.out.println("Priority is " + taskPriority);
            }
            if (!(parts.equals(DESC) || parts.equals(ST) || parts.equals(ET) || parts.equals(SD) || parts.equals(ED)
                    || parts.equals(P)) && lastChar == false) {
                // System.out.println("I am here as incrct because of parts
                // "+parts);
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }
            
        }
        // System.out
        // .println("Desc: " + hasChangedDescription + " st: " +
        // hasChangedStartTime + " et: " + hasChangedEndTime
        // + " sd: " + hasChangedStartDate + " ed: " + hasChangedEndDate + " p:
        // " + hasChangedPriority);
        try {
            // System.out.println("I am here to exectue edit command");
            return new EditCommand(targetIdx, new Description(description), hasChangedDescription,
                    new TaskDate(startDate), hasChangedStartDate, new TaskDate(endDate), hasChangedEndDate,
                    new TaskTime(startTime), hasChangedStartTime, new TaskTime(endTime), hasChangedEndTime,
                    new TaskPriority(taskPriority), hasChangedPriority);
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
        
    }

    // @@author

    // @@author A0139257X
    /**
     * Parses arguments in the context of the add task command.
     *
     * @param full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args) {
        if (args.isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ArrayList<String> argsList = tokenizeArguments(args);
        Queue<String> initialQueue = initialiseArgQueue(argsList);
        
        initialisePartitionQueue();
        initialiseHasTaskComponentArray();
        initialiseTaskComponentArray();
        
        try {
            splitInputIntoComponents(initialQueue);
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        
        addReservedWordToDescription(); //Add trailing reserved word to description
        
        extractDescriptionComponent();
        adjustEndDate();
        adjustStartTime();

        return addTaskAccordingToType();
    }
    
    private Command addTaskAccordingToType() {
        if (isEventTask()) {
            try {
                return new AddCommand(taskComponentArray[DESCRIPTION], Task.EVENT_TASK, 
                        taskComponentArray[START_DATE], taskComponentArray[END_DATE], 
                        taskComponentArray[START_TIME], taskComponentArray[END_TIME],
                        taskComponentArray[TASK_PRIORITY], taskComponentArray[RECURRING_TYPE], 
                        getTagsFromArgs(taskComponentArray[TAG]));
            } catch (IllegalValueException ive) {
                return new IncorrectCommand(ive.getMessage());
            }
        } else {
            if (hasTaskComponentArray[RECURRING_COMPONENT]) {
                return new IncorrectCommand(FloatingTask.RECURRING_TYPE_NOT_ALLOWED);
            } else {
                try {
                    return new AddCommand(taskComponentArray[DESCRIPTION], Task.FLOATING_TASK, 
                            taskComponentArray[START_DATE], taskComponentArray[END_DATE], 
                            taskComponentArray[START_TIME], taskComponentArray[END_TIME],
                            taskComponentArray[TASK_PRIORITY], taskComponentArray[RECURRING_TYPE], 
                            getTagsFromArgs(taskComponentArray[TAG]));
                } catch (IllegalValueException ive) {
                    return new IncorrectCommand(ive.getMessage());
                }
            }
        }
    }
    
    private void splitInputIntoComponents(Queue<String> initialQueue) throws IllegalValueException {
        String token = "";
        int priorityCount = 0;
        int recurrenceCount = 0;
        
        while (!initialQueue.isEmpty()) {
            token = initialQueue.poll().trim();
            if (!isReservedWord(token)) {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
                continue;
            } else if (token.equals(BY)) {
                offerTokenToQueue(BY_QUEUE, token);
                continue;
            } else if (token.equals(ON)) {
                offerTokenToQueue(ON_QUEUE, token);
                continue;
            } else if (token.equals(AT)) {
                offerTokenToQueue(AT_QUEUE, token);
                continue;
            } else if (token.equals(FROM)) {
                offerTokenToQueue(FROM_QUEUE, token);
                continue;
            } else if (token.equals(TO)) {
                offerTokenToQueue(TO_QUEUE, token);
                continue;
            } else if (token.startsWith(Tag.PREFIX)) {
                addReservedWordToDescription();
                taskComponentArray[TAG] += " " + token;
                continue;
            } else if (token.startsWith(TaskPriority.PREFIX)) {
                addReservedWordToDescription();
                if (priorityCount > 0) {
                    throw new IllegalValueException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
                } else {
                    taskComponentArray[TASK_PRIORITY] = token.substring(token.indexOf(TaskPriority.PREFIX) + 2);
                    priorityCount++;
                }
                continue;
            } else if (token.startsWith(RecurringType.PREFIX)) {
                addReservedWordToDescription();
                if (recurrenceCount > 0) {
                    throw new IllegalValueException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
                } else {
                    taskComponentArray[RECURRING_TYPE] = token.substring(token.indexOf(RecurringType.PREFIX) + 2);
                    hasTaskComponentArray[RECURRING_COMPONENT] = true;
                    recurrenceCount++;
                }
            } else if (TaskDate.isValidDate(token)) {
                determineDateisActualDateOrDescription(token);
            } else if (TaskTime.isValidTime(token)) {
                determineTimeIsActualTimeOrDescription(token);
            }
        }
    }
    
    
    private void determineDateisActualDateOrDescription(String token) {
        if (!isPreceededByDateTimePrefix(partitionQueue)) {
            partitionQueue.get(DESCRIPTION_QUEUE).offer(token);
        } else if (isPreceededByPrefixInQueue(ON_QUEUE)) {
            if (!hasTaskComponentArray[START_DATE_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, ON_QUEUE, START_DATE, START_DATE_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        } else if (isPreceededByPrefixInQueue(BY_QUEUE)) {
            if (!hasTaskComponentArray[END_DATE_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, BY_QUEUE, END_DATE, END_DATE_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        } else if (isPreceededByPrefixInQueue(AT_QUEUE)) {
            addReservedWordToDescription();
            partitionQueue.get(DESCRIPTION_QUEUE).offer(token);
        } else if (isPreceededByPrefixInQueue(FROM_QUEUE)) {
            if (!hasTaskComponentArray[START_DATE_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, FROM_QUEUE, START_DATE, START_DATE_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        } else if (isPreceededByPrefixInQueue(TO_QUEUE)) {
            if (!hasTaskComponentArray[END_DATE_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, TO_QUEUE, END_DATE, END_DATE_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        }
    }
    
    private void determineTimeIsActualTimeOrDescription(String token) {
        if (!isPreceededByDateTimePrefix(partitionQueue)) {
            partitionQueue.get(DESCRIPTION_QUEUE).offer(token);
        } else if (isPreceededByPrefixInQueue(BY_QUEUE)) {
            if (!hasTaskComponentArray[END_TIME_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, BY_QUEUE, END_TIME, END_TIME_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        } else if (isPreceededByPrefixInQueue(AT_QUEUE)) {
            if (!hasTaskComponentArray[START_TIME_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, AT_QUEUE, START_TIME, START_TIME_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        } else if (isPreceededByPrefixInQueue(FROM_QUEUE)) {
            if (!hasTaskComponentArray[START_TIME_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, FROM_QUEUE, START_TIME, START_TIME_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        } else if (isPreceededByPrefixInQueue(TO_QUEUE)) {
            if (!hasTaskComponentArray[END_TIME_COMPONENT]) {
                extractDateTimeWhenPreceededByPrefix(token, TO_QUEUE, END_TIME, END_TIME_COMPONENT);
            } else {
                offerTokenToQueue(DESCRIPTION_QUEUE, token);
            }
        } else if (isPreceededByPrefixInQueue(ON_QUEUE)) {
            offerTokenToQueue(DESCRIPTION_QUEUE, token);
        }
    }
    
    /**
     * Flush outstanding Date-Time prefix into description queue
     * Add Token to respective queue
     */
    private void offerTokenToQueue(int queueType, String token) {
        addReservedWordToDescription();
        partitionQueue.get(queueType).offer(token);
    }
    
    private boolean isEventTask() {
        return hasTaskComponentArray[START_DATE_COMPONENT] || hasTaskComponentArray[END_DATE_COMPONENT]
                || hasTaskComponentArray[START_TIME_COMPONENT] || hasTaskComponentArray[END_TIME_COMPONENT];
    }
    
    private void adjustStartTime() {
        if ((TaskDate.isValidToday(taskComponentArray[START_DATE]) && !hasTaskComponentArray[START_TIME_COMPONENT])
                || taskComponentArray[START_DATE].equals(TaskDate.DEFAULT_DATE) && !hasTaskComponentArray[START_TIME_COMPONENT]) {
            taskComponentArray[START_TIME] = TaskTime.getTimeNow().toString();
        }
    }
    
    private void adjustEndDate() {
        if (!hasTaskComponentArray[END_DATE_COMPONENT]) {
            taskComponentArray[END_DATE] = taskComponentArray[START_DATE];
        }
    }
    
    private void extractDescriptionComponent() {
        while (!partitionQueue.get(DESCRIPTION_QUEUE).isEmpty()) {
            taskComponentArray[DESCRIPTION] += partitionQueue.get(DESCRIPTION_QUEUE).poll() + " ";
        }
        taskComponentArray[DESCRIPTION].trim();
    }
    
    
    private void extractDateTimeWhenPreceededByPrefix(String token, int queueType, 
            int taskComponent, int taskComponentBoolean) {
        partitionQueue.get(queueType).poll();
        taskComponentArray[taskComponent] = token;
        hasTaskComponentArray[taskComponentBoolean] = true;
    }
    
    private boolean isPreceededByDateTimePrefix(ArrayList<Queue<String>> partitionQueue) {
        return !partitionQueue.get(BY_QUEUE).isEmpty() || !partitionQueue.get(ON_QUEUE).isEmpty() 
                || !partitionQueue.get(AT_QUEUE).isEmpty() || !partitionQueue.get(FROM_QUEUE).isEmpty() 
                || !partitionQueue.get(TO_QUEUE).isEmpty();
    }
    
    private boolean isPreceededByPrefixInQueue(int queueType) {
        return !partitionQueue.get(queueType).isEmpty();
    }
    
    private boolean isReservedWord(String token) {
        return token.equals(BY) || token.equals(ON) || token.equals(AT) || token.equals(FROM) 
                || token.equals(TO) || TaskDate.isValidDate(token) || TaskTime.isValidTime(token) 
                || token.startsWith(Tag.PREFIX) || token.startsWith(TaskPriority.PREFIX) 
                || token.startsWith(RecurringType.PREFIX);
    }
    
    private void addReservedWordToDescription() {
        String tempToken = flushQueue(partitionQueue);
        if (!tempToken.isEmpty()) {
            partitionQueue.get(DESCRIPTION_QUEUE).offer(tempToken);
        }
    }

    private String flushQueue(ArrayList<Queue<String>> partitionQueue) {
        String token = "";

        if (!partitionQueue.get(BY_QUEUE).isEmpty()) {
            token = partitionQueue.get(BY_QUEUE).poll();
        } else if (!partitionQueue.get(ON_QUEUE).isEmpty()) {
            token = partitionQueue.get(ON_QUEUE).poll();
        } else if (!partitionQueue.get(AT_QUEUE).isEmpty()) {
            token = partitionQueue.get(AT_QUEUE).poll();
        } else if (!partitionQueue.get(FROM_QUEUE).isEmpty()) {
            token = partitionQueue.get(FROM_QUEUE).poll();
        } else if (!partitionQueue.get(TO_QUEUE).isEmpty()) {
            token = partitionQueue.get(TO_QUEUE).poll();
        }

        return token;
    }

    private Queue<String> initialiseArgQueue(ArrayList<String> argsList) {
        Queue<String> argsQueue = new LinkedList<String>();
        for (String arg : argsList) {
            argsQueue.offer(arg);
        }
        return argsQueue;
    }
    
    private void initialisePartitionQueue() {
        partitionQueue = new ArrayList<Queue<String>>();
        for (int i=0; i<NUM_QUEUE; i++) {
            partitionQueue.add(new LinkedList<String>());
        }
    }
    
    private void initialiseHasTaskComponentArray() {
        hasTaskComponentArray = new boolean[NUM_BOOLEAN_TASK_COMPONENT];
    }
    
    private void initialiseTaskComponentArray() {
        taskComponentArray = new String[NUM_TASK_COMPONENT];
        taskComponentArray[DESCRIPTION] = "";
        taskComponentArray[START_DATE] = TaskDate.DEFAULT_DATE;
        taskComponentArray[END_DATE] = taskComponentArray[START_DATE];
        taskComponentArray[START_TIME] = TaskTime.DEFAULT_START_TIME;
        taskComponentArray[END_TIME] = TaskTime.DEFAULT_END_TIME;
        taskComponentArray[TASK_PRIORITY] = TaskPriority.DEFAULT_PRIORITY;
        taskComponentArray[RECURRING_TYPE] = RecurringType.DEFAULT_RECURRING;
        taskComponentArray[TAG] = "";
    }

    private ArrayList<String> tokenizeArguments(String args) {
        ArrayList<String> argsList = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(args, " ");
        while (st.hasMoreTokens()) {
            argsList.add(st.nextToken());
        }
        return argsList;
    }
    
    // @@author

    /**
     * Extracts the new task's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays
                .asList(tagArguments.replaceFirst(" " + Tag.PREFIX, "").split(" " + Tag.PREFIX));
        return new HashSet<>(tagStrings);
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {

        Optional<Integer> index = parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        return new DeleteCommand(index.get());
    }

    /**
     * Parses arguments in the context of the select task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareSelect(String args) {
        Optional<Integer> index = parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
        }

        return new SelectCommand(index.get());
    }

    /**
     * Returns the specified index in the {@code command} IF a positive unsigned
     * integer is given as the index. Returns an {@code Optional.empty()}
     * otherwise.
     */
    private Optional<Integer> parseIndex(String command) {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(command.trim());
        if (!matcher.matches()) {
            return Optional.empty();
        }

        String index = matcher.group("targetIndex");
        if (!StringUtil.isUnsignedInteger(index)) {
            return Optional.empty();
        }
        return Optional.of(Integer.parseInt(index));

    }

    /**
     * Parses arguments in the context of the find task command.
     *
     * @param args
     *            string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }

    /** @@author A0142130A **/

    /**
     * Parses arguments in the context of undo command.
     * 
     */
    private Command prepareUndo(String args) {
        if (args.isEmpty()) {
            return new UndoCommand();
        }

        Optional<Integer> index = parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        }
        return new UndoCommand(index.get());
    }

    /**
     * Parses arguments in the context of the find task by tags command.
     * 
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareFindByTag(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindTagCommand(keywordSet);

    }

    /**
     * Parses arguments in the context of the save storage location command.
     * 
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareSaveStorageLocation(String args) {
        if (args.isEmpty()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveStorageLocationCommand.MESSAGE_USAGE));
        }
        return new SaveStorageLocationCommand(args);
    }
    /** @@author **/

    // @@author A0148004R
    /**
     * Parses arguments in the context of the done task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareDone(String args) {
        Optional<Integer> index = parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
        }

        return new DoneCommand(index.get());
    }

    /**
     * Parses arguments in the context of the Undone task command.
     *
     * @param args
     *            full command args string
     * @return the prepared command
     */
    private Command prepareUndone(String args) {
        Optional<Integer> index = parseIndex(args);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoneCommand.MESSAGE_USAGE));
        }

        return new UndoneCommand(index.get());
    }
    // @@author

    // @@author A0142073R
    private static boolean isInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        }

        catch (NumberFormatException er) {
            return false;
        }
    }
    // @@author
}
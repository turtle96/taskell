package seedu.taskell.model.task;

import org.junit.Before;
import org.junit.Test;

import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.model.tag.UniqueTagList;

import static org.junit.Assert.assertEquals;

//@@author A0139257X

/**
 * This test class is time and date sensitive
 */
public class EventTaskTest {
    
    TaskDate today;
    TaskDate tomorrow;
    String defaultDescription;
    
    @Before
    public void setup() {
        today = TaskDate.getTodayDate();
        tomorrow = TaskDate.getTomorrowDate();
        defaultDescription = "Go meeting";
    }
    
    /**
     * Compare string of both parameter confirms both are the same.
     */
    private void assertEqualEventTask(EventTaskTestExpectedResult expected, EventTask actual) {
        assertEquals(expected.toString(),(actual.toString()));
    }
    
    @Test
    public void construct_fullDateAsEndDate_endDateAfterToday() throws IllegalValueException {
        //Go meeting by 1-1-2050
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String endDate = "1-1-2050";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.END_DATE] = endDate;
        helper.hasTaskComponentArray[Task.END_DATE_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                today.toString(), endDate,
                TaskTime.getTimeNow().toString(), TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_fullDateAsEndDate_exceptionThrown() {
        //Go meeting by 1-1-2000
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String endDate = "1-1-2000";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.END_DATE] = endDate;
        helper.hasTaskComponentArray[Task.END_DATE_COMPONENT] = true;
        
        try {
            EventTask actual = helper.getEventTask();
            
            EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                    today.toString(), endDate,
                    TaskTime.getTimeNow().toString(), TaskTime.DEFAULT_END_TIME.toString(),
                    TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
            
            assertEqualEventTask(expected, actual);
        } catch (IllegalValueException ive) {
            assertEquals(ive.getMessage(), EventTask.MESSAGE_EVENT_CONSTRAINTS);
        }
    }
    
    @Test
    public void construct_dayNameInWeekAsEndDate_endDateAfterToday() throws IllegalValueException {
        //Go meeting by fri
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String endDate = "fri";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.END_DATE] = endDate;
        helper.hasTaskComponentArray[Task.END_DATE_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                today.toString(), TaskDate.determineDayInWeekGivenName(endDate).toString(),
                TaskTime.getTimeNow().toString(), TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_monthAndYearAsEndDate_exceptionThrown() {
        //Go meeting by may-2000
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String endDate = "may-2000";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.END_DATE] = endDate;
        helper.hasTaskComponentArray[Task.END_DATE_COMPONENT] = true;
        
        try {
            EventTask actual = helper.getEventTask();
            
            EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                    today.toString(), endDate,
                    TaskTime.getTimeNow().toString(), TaskTime.DEFAULT_END_TIME.toString(),
                    TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
            
            assertEqualEventTask(expected, actual);
        } catch (IllegalValueException ive) {
            assertEquals(ive.getMessage(), EventTask.MESSAGE_EVENT_CONSTRAINTS);
        }
    }
    
    @Test
    public void construct_monthAndYearAsStartDate_succesfulTaskCreated() throws IllegalValueException {
        //Go meeting on may-2020
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String startDate = "may-2020";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.START_DATE] = startDate;
        helper.hasTaskComponentArray[Task.START_DATE_COMPONENT] = true;
        
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                startDate, startDate,
                TaskTime.DEFAULT_START_TIME.toString(), TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_fullDateAsStartDate_startDateAfterToday() throws IllegalValueException {
        //Go meeting on may-2020
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String startDate = "may-2020";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.START_DATE] = startDate;
        helper.hasTaskComponentArray[Task.START_DATE_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                startDate, startDate,
                TaskTime.DEFAULT_START_TIME.toString(), TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_noStartDateAndStartTimeBeforeCurrentTime_startDateIsNextDay() throws IllegalValueException {
        //Go meeting at 12.01am 
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String startTime = "12.01am";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.START_TIME] = startTime;
        helper.hasTaskComponentArray[Task.START_TIME_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                tomorrow.toString(), tomorrow.toString(),
                startTime, TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_noEndDate_EndTimeBeforeStartTime_startDateIsNextDay() throws IllegalValueException {
        //Go meeting on 1-1-2050 from 2am to 1am
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String startDate = "1-1-2020";
        String startTime = "2am";
        String endTime = "1am";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.START_DATE] = startDate;
        helper.taskComponentArray[Task.START_TIME] = startTime;
        helper.taskComponentArray[Task.END_TIME] = endTime;
        helper.hasTaskComponentArray[Task.START_DATE_COMPONENT] = true;
        helper.hasTaskComponentArray[Task.START_TIME_COMPONENT] = true;
        helper.hasTaskComponentArray[Task.END_TIME_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        TaskDate expectedEndDate = new TaskDate(startDate).getNextDay();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                startDate, expectedEndDate.toString(),
                startTime, endTime,
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_validMonthBeforeToday_startDateSameMonthNextYear() throws IllegalValueException {
        //Go meeting on may
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String startDate = "may";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.START_DATE] = startDate;
        helper.hasTaskComponentArray[Task.START_DATE_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                "may-" + today.getNextYear().getYear(), "may-" + today.getNextYear().getYear(),
                TaskTime.DEFAULT_START_TIME.toString(), TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_endMonthBeforeStartMonth_SameDateTwoYearsLater() throws IllegalValueException {
        //Go meeting from may to april
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String startDate = "may";
        String endDate = "april";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.START_DATE] = startDate;
        helper.hasTaskComponentArray[Task.START_DATE_COMPONENT] = true;
        helper.taskComponentArray[Task.END_DATE] = endDate;
        helper.hasTaskComponentArray[Task.END_DATE_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                "may-" + today.getNextYear().getYear(), "april-" + today.getNextYear().getNextYear().getYear(),
                TaskTime.DEFAULT_START_TIME.toString(), TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }
    
    @Test
    public void construct_endDayMonthBeforeStartDayMonth_SameDateTwoYearsLater() throws IllegalValueException {
        //Go meeting from 12-may to 15-april
        EventTaskTestHelper helper = new EventTaskTestHelper();
        String startDate = "12-may";
        String endDate = "15-april";
        helper.taskComponentArray[Task.DESCRIPTION] = defaultDescription;
        helper.taskComponentArray[Task.START_DATE] = startDate;
        helper.hasTaskComponentArray[Task.START_DATE_COMPONENT] = true;
        helper.taskComponentArray[Task.END_DATE] = endDate;
        helper.hasTaskComponentArray[Task.END_DATE_COMPONENT] = true;
        
        EventTask actual = helper.getEventTask();
        
        EventTaskTestExpectedResult expected = new EventTaskTestExpectedResult(defaultDescription,
                "12-may-" + today.getNextYear().getYear(), "15-april-" + today.getNextYear().getNextYear().getYear(),
                TaskTime.DEFAULT_START_TIME.toString(), TaskTime.DEFAULT_END_TIME.toString(),
                TaskPriority.DEFAULT_PRIORITY, RecurringType.DEFAULT_RECURRING);
        
        assertEqualEventTask(expected, actual);
    }

}

/**
 * A utility class to generate test data.
 */
class EventTaskTestHelper {
    
    String[] taskComponentArray;
    boolean[] hasTaskComponentArray;
    UniqueTagList tags;
    
    EventTaskTestHelper() {
        initialiseTaskComponentArray();
        initialiseHasTaskComponentArray();
        initialiseUniqueTagList();
    }
    
    void initialiseHasTaskComponentArray() {
        hasTaskComponentArray = new boolean[Task.NUM_BOOLEAN_TASK_COMPONENT];
    }

    void initialiseTaskComponentArray() {
        taskComponentArray = new String[Task.NUM_TASK_COMPONENT];
        taskComponentArray[Task.DESCRIPTION] = " ";
        taskComponentArray[Task.START_DATE] = TaskDate.DEFAULT_DATE;
        taskComponentArray[Task.END_DATE] = taskComponentArray[Task.START_DATE];
        taskComponentArray[Task.START_TIME] = TaskTime.DEFAULT_START_TIME;
        taskComponentArray[Task.END_TIME] = TaskTime.DEFAULT_END_TIME;
        taskComponentArray[Task.TASK_PRIORITY] = TaskPriority.DEFAULT_PRIORITY;
        taskComponentArray[Task.RECURRING_TYPE] = RecurringType.DEFAULT_RECURRING;
        taskComponentArray[Task.TAG] = "";
    }
    
    void initialiseUniqueTagList() {
        tags = new UniqueTagList();
    }
    
    EventTask getEventTask() throws IllegalValueException {
        return new EventTask(taskComponentArray, hasTaskComponentArray, tags);
    }
}

/**
 * A utility class to generate expected result of an event task.
 */
class EventTaskTestExpectedResult {
    Description description;
    String taskType;
    TaskDate startDate;
    TaskDate endDate;
    TaskTime startTime;
    TaskTime endTime;
    TaskPriority taskPriority;
    RecurringType recurringType;
    TaskStatus taskStatus;
    UniqueTagList tags;
    
    EventTaskTestExpectedResult (String description, String startDate, String endDate, 
            String startTime, String endTime, String taskPriority, String recurringType) throws IllegalValueException {
        
        this.description = new Description(description);
        this.taskType = Task.EVENT_TASK;
        this.startDate = new TaskDate(startDate);
        this.endDate = new TaskDate(endDate);
        this.startTime = new TaskTime(startTime);
        this.endTime = new TaskTime(endTime);
        this.taskPriority = new TaskPriority(taskPriority);
        this.recurringType = new RecurringType(recurringType);
        this.taskStatus = new TaskStatus(TaskStatus.INCOMPLETE);
        this.tags = new UniqueTagList();
    }
    
    String getAsTextEventTask() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.description)
                .append(" StartDate: ")
                .append(this.startDate)
                .append(" EndDate: ")
                .append(this.endDate)
                .append(" StartTime: ")
                .append(this.startTime)
                .append(" EndTime: ")
                .append(this.endTime)
                .append(" TaskPriority: ")
                .append(this.taskPriority)
                .append(" RecurringType: ")
                .append(this.recurringType)
                .append(" TaskStatus: ")
                .append(this.taskStatus)
                .append(" Tags: ");
        tags.forEach(builder::append);
        return builder.toString();
    }
    
    @Override
    public String toString() {
        return getAsTextEventTask();
    }
}
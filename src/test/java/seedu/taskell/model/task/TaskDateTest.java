package seedu.taskell.model.task;

import seedu.taskell.commons.exceptions.IllegalValueException;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

//@@author A0139257X
public class TaskDateTest {
    
    private TaskDate defaultDate;
    
    @Before
    public void setup() throws IllegalValueException {
        defaultDate = new TaskDate("1-1-2016");
    }
    
    @Test
    public void isValidDate_dayOfTheWeek_returnTrue() {
        assertTrue(TaskDate.isValidDate("mon"));
        assertTrue(TaskDate.isValidDate("tue"));
        assertTrue(TaskDate.isValidDate("WED"));
        assertTrue(TaskDate.isValidDate("thurs"));
        assertTrue(TaskDate.isValidDate("fRi"));
        assertTrue(TaskDate.isValidDate("saturday"));
        assertTrue(TaskDate.isValidDate("sun"));
    }
    
    @Test
    public void isValidDate_month_returnTrue() {
        assertTrue(TaskDate.isValidDate("jan"));
        assertTrue(TaskDate.isValidDate("fEb"));
        assertTrue(TaskDate.isValidDate("march"));
        assertTrue(TaskDate.isValidDate("APRIL"));
        assertTrue(TaskDate.isValidDate("mAy"));
        assertTrue(TaskDate.isValidDate("junE"));
        assertTrue(TaskDate.isValidDate("jul"));
        assertTrue(TaskDate.isValidDate("aug"));
        assertTrue(TaskDate.isValidDate("sept"));
        assertTrue(TaskDate.isValidDate("oct"));
        assertTrue(TaskDate.isValidDate("November"));
        assertTrue(TaskDate.isValidDate("December"));
    }
        
    @Test
    public void isValidDate_monthAndYear_returnTrue() {
        assertTrue(TaskDate.isValidDate("may 2016"));
        assertTrue(TaskDate.isValidDate("may-2016"));
        assertTrue(TaskDate.isValidDate("may.2016"));
        assertTrue(TaskDate.isValidDate("may/2016"));
    }
    
    @Test
    public void isValidDate_dayAndMonth_returnTrue() {
        assertTrue(TaskDate.isValidDate("1 jan"));
        assertTrue(TaskDate.isValidDate("1-jan"));
        assertTrue(TaskDate.isValidDate("1.jan"));
        assertTrue(TaskDate.isValidDate("1/jan"));
    }
     
    @Test
    public void isValidDate_fullDate_returnTrue() {
        assertTrue(TaskDate.isValidDate(TaskDate.DEFAULT_DATE));
        assertTrue(TaskDate.isValidDate("1 1 2016"));
        assertTrue(TaskDate.isValidDate("1 jan 2016"));
        assertTrue(TaskDate.isValidDate("1-1-2016"));
        assertTrue(TaskDate.isValidDate("1-jan-2016"));
        assertTrue(TaskDate.isValidDate("1.1.2016"));
        assertTrue(TaskDate.isValidDate("8.DeCeMbEr.2016"));
        assertTrue(TaskDate.isValidDate("8/8/2016"));
        assertTrue(TaskDate.isValidDate("8/jan/2016"));
        assertTrue(TaskDate.isValidDate("1-1/2016"));
        assertTrue(TaskDate.isValidDate("1-jan/2016"));
        assertTrue(TaskDate.isValidDate("1-1.2016"));
        assertTrue(TaskDate.isValidDate("1-jan.2016"));
        assertTrue(TaskDate.isValidDate("1/1-2016"));
        assertTrue(TaskDate.isValidDate("1/jan-2016"));
        assertTrue(TaskDate.isValidDate("1/1.2016"));
        assertTrue(TaskDate.isValidDate("1/jan.2016"));
        assertTrue(TaskDate.isValidDate("1.1/2016"));
        assertTrue(TaskDate.isValidDate("1.jan/2016"));
        assertTrue(TaskDate.isValidDate("1.1-2016"));
        assertTrue(TaskDate.isValidDate("1.jan-2016"));
    }
    
    @Test
    public void isValidDate_today_returnTrue() {
        assertTrue(TaskDate.isValidDate("Today"));
        assertTrue(TaskDate.isValidDate("tdy"));
    }
    
    @Test 
    public void isValidDate_returnTrue() {
        assertTrue(TaskDate.isValidDate("Tomorrow"));
        assertTrue(TaskDate.isValidDate("tmr"));
    }
    
    @Test
    public void isValidDate_returnFalse() {
        assertFalse(TaskDate.isValidDate(""));
        assertFalse(TaskDate.isValidDate(null));
        assertFalse(TaskDate.isValidDate("1st January"));
        assertFalse(TaskDate.isValidDate("1/2"));
        assertFalse(TaskDate.isValidDate("01022016"));
        assertFalse(TaskDate.isValidDate("2016"));
        assertFalse(TaskDate.isValidDate("NotAValidDate"));
    }
    
    @Test
    public void constructor_validDate_newObjectCreated() throws IllegalValueException {
        TaskDate today = TaskDate.getTodayDate();
        TaskDate validDayOfWeek = new TaskDate(today.getDayNameInWeek());
        assertEquals(today.getNextWeek(), validDayOfWeek);
        
        TaskDate validMonth = new TaskDate("september");
        assertEquals("1-9-2017", validMonth.toString());
        
        TaskDate validMonthAndYear = new TaskDate("dec-2017");
        assertEquals("1-12-2017", validMonthAndYear.toString());
        
        TaskDate validDayAndMonth = new TaskDate("1-jan");
        assertEquals("1-1-2017", validDayAndMonth.toString());
        
        TaskDate validFullDate = new TaskDate("1-1-2011");
        assertEquals("1-1-2011", validFullDate.toString());
        
        TaskDate validToday = new TaskDate("today");
        DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
        assertEquals(LocalDate.now().format(standardFormat), validToday.toString());
        
        TaskDate validTomorrow = new TaskDate("tmr");
        standardFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
        assertEquals(LocalDate.now().plusDays(1).format(standardFormat), validTomorrow.toString());
    }
    
    @Test
    public void constructor_invalidDate_ExceptionThrown() {
        try {
            TaskDate invalidDate = new TaskDate("NOT-A-VALID-DATE");
        } catch (IllegalValueException ive) {
            assertEquals(TaskDate.MESSAGE_TASK_DATE_CONSTRAINTS, ive.getMessage());
        }
    }
    
    @Test
    public void getTodayDate_returnTodayDateAsString_success() {
        DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
        assertEquals(LocalDate.now().format(standardFormat), TaskDate.getTodayDate().toString());
    }
    
    @Test
    public void getTomorrowDate_returnTomorrowDateAsString_success() {
        DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
        assertEquals(LocalDate.now().plusDays(1).format(standardFormat), TaskDate.getTomorrowDate().toString());
    }
    
    @Test
    public void getYear_returnYearAsString_success() {
        assertEquals(LocalDate.now().getYear() + "", TaskDate.getThisYear());
    }
    
    @Test
    public void getNextDay_returnNextDay_success() throws IllegalValueException {
        TaskDate today = defaultDate;
        TaskDate nextDay = new TaskDate("2-1-2016");
        assertEquals(nextDay, today.getNextDay());
    }
    
    @Test
    public void getNextWeek_returnNextWeek_success() throws IllegalValueException {
        TaskDate today = defaultDate;
        TaskDate nextWeek = new TaskDate("8-1-2016");
        assertEquals(nextWeek, today.getNextWeek());
    }
    
    @Test
    public void getNextMonth_returnNextMonth_success() throws IllegalValueException {
        TaskDate today = defaultDate;
        TaskDate nextMonth = new TaskDate("1-2-2016");
        assertEquals(nextMonth, today.getNextMonth());
    }
    
    @Test
    public void getNextYear_returnNextYear_success() throws IllegalValueException {
        TaskDate today = defaultDate;
        TaskDate nextYear = new TaskDate("1-1-2017");
        assertEquals(nextYear, today.getNextYear());
    }
    
    @Test
    public void getDayInt_returnsIntegerValueDayInMonth_success() throws IllegalValueException {
        assertEquals(1, defaultDate.getDayInt());
    }
    
    @Test
    public void getMonthInt_returnsIntegerValueOfMonth_success() throws IllegalValueException {
        assertEquals(1, defaultDate.getMonthInt());
    }
    
    @Test
    public void getYearInt_returnsIntegerValueOfYear_success() throws IllegalValueException {
        assertEquals(2016, defaultDate.getYearInt());
    }
    
    @Test
    public void getLocalDate_returnLocalDate_success() {
        TaskDate date = defaultDate;
        LocalDate actual = date.getLocalDate();
        LocalDate expected = LocalDate.of(2016, 1, 1);
        assertEquals(expected, actual);
    }
    
    @Test
    public void isBefore_thisDateBeforeGivenDate_success() throws IllegalValueException {
        TaskDate startDate = defaultDate;
        TaskDate endDateDiffDaySameMonthSameYear = new TaskDate("10-1-2016");
        TaskDate endDateSameDayDiffMonthSameYear = new TaskDate("1-2-2016");
        TaskDate endDateSameDaySameMonthDiffYear = new TaskDate("1-1-2200");
        
        assertTrue(startDate.isBefore(endDateDiffDaySameMonthSameYear));
        assertTrue(startDate.isBefore(endDateSameDayDiffMonthSameYear));
        assertTrue(startDate.isBefore(endDateSameDaySameMonthDiffYear));
    }
    
    @Test
    public void isBefore_thisDateBeforeGivenDate_failure() throws IllegalValueException {
        TaskDate startDate = defaultDate;
        TaskDate endDateDiffDaySameMonthSameYear = new TaskDate("10-1-2016");
        TaskDate endDateSameDayDiffMonthSameYear = new TaskDate("1-2-2016");
        TaskDate endDateSameDaySameMonthDiffYear = new TaskDate("1-1-2200");
        
        assertFalse(endDateDiffDaySameMonthSameYear.isBefore(startDate));
        assertFalse(endDateSameDayDiffMonthSameYear.isBefore(startDate));
        assertFalse(endDateSameDaySameMonthDiffYear.isBefore(startDate));
    }
    
    @Test
    public void isAfter_thisDateAfterGivenDate_success() throws IllegalValueException {
        TaskDate startDate = defaultDate;
        TaskDate endDateDiffDaySameMonthSameYear = new TaskDate("10-1-2016");
        TaskDate endDateSameDayDiffMonthSameYear = new TaskDate("1-2-2016");
        TaskDate endDateSameDaySameMonthDiffYear = new TaskDate("1-1-2200");
        
        assertTrue(endDateDiffDaySameMonthSameYear.isAfter(startDate));
        assertTrue(endDateSameDayDiffMonthSameYear.isAfter(startDate));
        assertTrue(endDateSameDaySameMonthDiffYear.isAfter(startDate));
    }
    
    @Test
    public void isAfter_thisDateAfterGivenDate_failure() throws IllegalValueException {
        TaskDate startDate = defaultDate;
        TaskDate endDateDiffDaySameMonthSameYear = new TaskDate("10-1-2016");
        TaskDate endDateSameDayDiffMonthSameYear = new TaskDate("1-2-2016");
        TaskDate endDateSameDaySameMonthDiffYear = new TaskDate("1-1-2200");
        
        assertFalse(startDate.isAfter(endDateDiffDaySameMonthSameYear));
        assertFalse(startDate.isAfter(endDateSameDayDiffMonthSameYear));
        assertFalse(startDate.isAfter(endDateSameDaySameMonthDiffYear));
    }
    
    @Test
    public void between_firstDateBeforeSecondDate_returnPositiveDifference() throws IllegalValueException {
        TaskDate first = defaultDate;
        TaskDate second = new TaskDate("20-1-2016");
        
        long positiveDayDifference = TaskDate.between(first, second);
        assertEquals(19, positiveDayDifference);
    }
    
    @Test
    public void between_firstDateAfterSecondDate_returnNegativeDifference() throws IllegalValueException {
        TaskDate first = defaultDate;
        TaskDate second = new TaskDate("20-1-2016");
        
        long negativeDayDifference = TaskDate.between(second, first);
        assertEquals(-19, negativeDayDifference);
    }
    
    @Test
    public void getDisplayDate_success() {
        TaskDate date = defaultDate;
        assertEquals("Friday, 1 January 2016", date.getDisplayDate());
    }
    
    @Test
    public void getLocalDateTime_success() throws IllegalValueException {
        TaskDate date = defaultDate;
        TaskTime givenTime = new TaskTime("3am");

        LocalDateTime actual = date.toLocalDateTime(givenTime);
        LocalDateTime expected = LocalDateTime.of(2016, 1, 1, 3, 0);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void toString_success() throws IllegalValueException {
        assertEquals("1-1-2016", defaultDate.toString());
    }
    
    @Test
    public void equals_success() throws IllegalValueException {
        TaskDate date = defaultDate;
        TaskDate sameDate = new TaskDate("1-1-2016");
        
        assertEquals(date, date);
        assertEquals(date, sameDate);
    }
    
    @Test
    public void equals_failure() throws IllegalValueException {
        TaskDate date = defaultDate;
        TaskDate differentDate = new TaskDate("2-2-2016");
        
        assertNotSame(date, differentDate);
        assertNotSame(date, "1-1-2015");
        assertNotSame(date, "NOT A DATE");
        assertNotSame(date, null);
    }
}

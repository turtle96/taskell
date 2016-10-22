package seedu.taskell.model;

import com.google.common.eventbus.Subscribe;

import seedu.taskell.commons.core.EventsCenter;
import seedu.taskell.commons.events.model.TaskManagerChangedEvent;
import seedu.taskell.commons.events.ui.JumpToListRequestEvent;
import seedu.taskell.commons.events.ui.ShowHelpRequestEvent;
import seedu.taskell.commons.exceptions.IllegalValueException;
import seedu.taskell.logic.Logic;
import seedu.taskell.logic.LogicManager;
import seedu.taskell.logic.commands.*;
import seedu.taskell.model.TaskManager;
import seedu.taskell.model.Model;
import seedu.taskell.model.ModelManager;
import seedu.taskell.model.ReadOnlyTaskManager;
import seedu.taskell.model.tag.Tag;
import seedu.taskell.model.tag.UniqueTagList;
import seedu.taskell.model.task.*;
import seedu.taskell.storage.StorageManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static seedu.taskell.commons.core.Messages.*;

public class TaskDateTest {
    
    @Test
    public void assertValidFormatBehaviourForDate() {
        //Valid Day of the Week
        assertTrue(TaskDate.isValidDate("mon"));
        assertTrue(TaskDate.isValidDate("tue"));
        assertTrue(TaskDate.isValidDate("WED"));
        assertTrue(TaskDate.isValidDate("thurs"));
        assertTrue(TaskDate.isValidDate("fRi"));
        assertTrue(TaskDate.isValidDate("saturday"));
        assertTrue(TaskDate.isValidDate("sun"));
        
        //Valid Month
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
        
        //Valid Month and Year
        assertTrue(TaskDate.isValidDate("may 2016"));
        assertTrue(TaskDate.isValidDate("may-2016"));
        assertTrue(TaskDate.isValidDate("may.2016"));
        assertTrue(TaskDate.isValidDate("may/2016"));
        
        //Valid Day and Month
        assertTrue(TaskDate.isValidDate("1 jan"));
        assertTrue(TaskDate.isValidDate("1-jan"));
        assertTrue(TaskDate.isValidDate("1.jan"));
        assertTrue(TaskDate.isValidDate("1/jan"));
        
        //Valid full Date
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
        
        //Valid Today
        assertTrue(TaskDate.isValidDate("Today"));
        assertTrue(TaskDate.isValidDate("tdy"));
        
        //Valid Tomorrow
        assertTrue(TaskDate.isValidDate("Tomorrow"));
        assertTrue(TaskDate.isValidDate("tmr"));
        
    }
    
    @Test
    public void assertInvalidFormatBehaviourForDate() {
        assertFalse(TaskDate.isValidDate(""));
        assertFalse(TaskDate.isValidDate(null));
        assertFalse(TaskDate.isValidDate("1st January"));
        assertFalse(TaskDate.isValidDate("1/2"));
        assertFalse(TaskDate.isValidDate("01022016"));
        assertFalse(TaskDate.isValidDate("2016"));
        assertFalse(TaskDate.isValidDate("NotAValidDate"));
    }
    
    @Test
    public void assertCorrectTodayDate() {
        DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
        assertEquals(LocalDate.now().format(standardFormat), TaskDate.getTodayDate());
    }
    
    @Test
    public void assertCorrectTomorrowDate() {
        DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("d-MM-yyyy");
        assertEquals(LocalDate.now().plusDays(1).format(standardFormat), TaskDate.getTomorrowDate());
    }
    
    @Test
    public void assertCorrectThisYear() {
        assertEquals(LocalDate.now().getYear() + "", TaskDate.getThisYear());
    }
    
    @Test
    public void assertDateisBeforeBehaviour() throws IllegalValueException {
            TaskDate startDate = new TaskDate("1-1-2100");
            TaskDate endDateDiffDaySameMonthSameYear = new TaskDate("10-1-2100");
            TaskDate endDateSameDayDiffMonthSameYear = new TaskDate("1-2-2100");
            TaskDate endDateSameDaySameMonthDiffYear = new TaskDate("1-1-2200");
            
            assertTrue(startDate.isBefore(endDateDiffDaySameMonthSameYear));
            assertTrue(startDate.isBefore(endDateSameDayDiffMonthSameYear));
            assertTrue(startDate.isBefore(endDateSameDaySameMonthDiffYear));
            
            assertFalse(endDateDiffDaySameMonthSameYear.isBefore(startDate));
            assertFalse(endDateSameDayDiffMonthSameYear.isBefore(startDate));
            assertFalse(endDateSameDaySameMonthDiffYear.isBefore(startDate));
    }
    
    @Test
    public void assertDateisAfterBehaviour() throws IllegalValueException {
            TaskDate startDate = new TaskDate("1-1-2100");
            TaskDate endDateDiffDaySameMonthSameYear = new TaskDate("10-1-2100");
            TaskDate endDateSameDayDiffMonthSameYear = new TaskDate("1-2-2100");
            TaskDate endDateSameDaySameMonthDiffYear = new TaskDate("1-1-2200");
            
            assertTrue(endDateDiffDaySameMonthSameYear.isAfter(startDate));
            assertTrue(endDateSameDayDiffMonthSameYear.isAfter(startDate));
            assertTrue(endDateSameDaySameMonthDiffYear.isAfter(startDate));
            
            assertFalse(startDate.isAfter(endDateDiffDaySameMonthSameYear));
            assertFalse(startDate.isAfter(endDateSameDayDiffMonthSameYear));
            assertFalse(startDate.isAfter(endDateSameDaySameMonthDiffYear));
    }
    
    @Test
    public void assertCorrectDisplayDate() throws IllegalValueException {
        TaskDate date = new TaskDate("22-10-2016");
        assertEquals("Saturday, 22 October 2016", date.getDisplayDate());
    }
    
    @Test
    public void assertCorrectToString() throws IllegalValueException {
        TaskDate date = new TaskDate("1-1-2015");
        assertEquals("1-1-2015", date.toString());
    }
    
    @Test
    public void assertEqualsBehaviour() throws IllegalValueException {
        TaskDate date = new TaskDate("1-1-2015");
        TaskDate sameDate = new TaskDate("1-1-2015");
        TaskDate differentDate = new TaskDate("2-2-2016");
        
        assertEquals(date, date);
        assertEquals(date, sameDate);
        
        assertNotSame(date, differentDate);
        assertNotSame(date, "1-1-2015");
        assertNotSame(date, "NOT A DATE");
        assertNotSame(date, null);
    }
}

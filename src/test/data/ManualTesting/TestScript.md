# Manual Testing: Test Script
## How to load the sample data
1. Navigate to Taskell folder in File Explorer
2. Ensure both Taskell jar and SampleData.xml are in the same folder
3. Open config.json in a text editor
4. At taskManagerFilePath, replace "data/taskmanager.xml" with "SampleData.xml"
5. Save config.json
6. Launch Taskell jar

## Test cases
### Add command
Command: `add meet friends by today` <br>
Expected: Task card with description "meet friends" added to left panel on today's date, 9 November 2016

Command: `add walk the dog on fri` <br>
Expected: Task card with description "walk the dog" added to left panel on friday's date, 11 November 2016

Command: `add meet cs2013 tutor for consultation #study #project #meeting on 5-11-2016`<br>
Expected: Should see invalid command result: "Start date and time must be before end date and time <br>
All date and time should not before current time"

Command: `add meet cs2013 tutor for consultation #study #project #meeting on 9-11-2016 from 5pm to 6pm`<br>
Expected: Task card with description "meet cs2013 tutor for consultation " added to left panel on today's date, 9 November 2016, from 5pm to 6pm

Command: `add `<br>
Expected: Should see invalid command result: a message explaining how to use add command should be shown, along with parameters and examples

### Edit command
Command: `edit `<br>
Expected: Should see invalid command result: a message explaining how to use edit command should be shown, along with parameters and examples

Command `edit 1 desc: go China with family`<br>
Expected:
<br>Original Task: go korea with family StartDate: 7-12-2015 EndDate: 29-12-2015 StartTime: 2:00AM EndTime: 11:40PM TaskPriority: 2 RecurringType: neverRecur TaskStatus: finished Tags: [holiday] 
<br>Updated Task: go China with family StartDate: 7-12-2015 EndDate: 29-12-2015 StartTime: 2:00AM EndTime: 11:40PM TaskPriority: 2 RecurringType: neverRecur TaskStatus: finished Tags: [holiday]

Command `edit 2 st: 3pm`<br>
Expected:
<br>Original Task: internship at cousin's company StartDate: 3-1-2016 EndDate: 8-4-2016 StartTime: 8:00AM EndTime: 5:20PM TaskPriority: 0 RecurringType: neverRecur TaskStatus: finished Tags:  <br>
Updated Task: internship at cousin's company StartDate: 3-1-2016 EndDate: 8-4-2016 StartTime: 3:00PM EndTime: 5:20PM TaskPriority: 0 RecurringType: neverRecur TaskStatus: finished Tags: 

Command `edit 4 ed: 19-05-2016`<br>
Expected:
<br>Original Task: celebrate my 21st birthday StartDate: 17-5-2016 EndDate: 17-5-2016 StartTime: 7:00PM EndTime: 9:00PM TaskPriority: 3 RecurringType: neverRecur TaskStatus: finished Tags: [birthday][event][party] 
<br>Updated Task: celebrate my 21st birthday StartDate: 17-5-2016 EndDate: 19-5-2016 StartTime: 7:00PM EndTime: 9:00PM TaskPriority: 3 RecurringType: neverRecur TaskStatus: finished Tags: [birthday][event][party]

Command `edit 6 sd: 02-11-2016`<br>
Expected:
<br>Original Task: do CS2010 ps6 StartDate: 30-10-2016 EndDate: 9-11-2016 StartTime: 10:47PM EndTime: 11:59PM TaskPriority: 3 RecurringType: neverRecur TaskStatus: incomplete Tags: [cs2010][assignment] 
<br>Updated Task: do CS2010 ps6 StartDate: 2-11-2016 EndDate: 9-11-2016 StartTime: 10:47PM EndTime: 11:59PM TaskPriority: 3 RecurringType: neverRecur TaskStatus: incomplete Tags: [cs2010][assignment]

Command `edit 8 et: 1pm`<br>
Expected:
<br>Original Task: buy cabbage StartDate: 4-10-2016 EndDate: 25-10-2016 StartTime: 10:51PM EndTime: 11:59PM TaskPriority: 0 RecurringType: neverRecur TaskStatus: finished Tags: [groceries] 
<br>Updated Task: buy cabbage StartDate: 4-10-2016 EndDate: 25-10-2016 StartTime: 10:51PM EndTime: 1:00PM TaskPriority: 0 RecurringType: neverRecur TaskStatus: finished Tags: [groceries]

Command `edit 10 p: 3`<br>
Expected:
<br>Original Task: buy new earphones StartDate: 10-11-2016 EndDate: 10-11-2016 StartTime: 12:00AM EndTime: 11:59PM TaskPriority: 0 RecurringType: neverRecur TaskStatus: incomplete Tags:  <br>
Updated Task: buy new earphones StartDate: 10-11-2016 EndDate: 10-11-2016 StartTime: 12:00AM EndTime: 11:59PM TaskPriority: 3 RecurringType: neverRecur TaskStatus: incomplete Tags: 

Command `edit 20 desc: MA1101R exam sd: 23-11-2016 ed: 23-11-2016 p: 3`<br>
Expected:
<br>Original Task: ST2334 exam StartDate: 22-11-2016 EndDate: 22-11-2016 StartTime: 1:00PM EndTime: 3:00PM TaskPriority: 0 RecurringType: neverRecur TaskStatus: incomplete Tags: [exam][stats] 
<br>Updated Task: MA1101R exam StartDate: 23-11-2016 EndDate: 23-11-2016 StartTime: 1:00PM EndTime: 3:00PM TaskPriority: 3 RecurringType: neverRecur TaskStatus: incomplete Tags: [exam][stats]

Command `edit 30 desc: CS2105 revision lecture st: 4pm et: 6pm`<br>
Expected:
<br>Original Task: ST2334 revision lecture StartDate: 8-11-2016 EndDate: 8-11-2016 StartTime: 12:00PM EndTime: 2:00PM TaskPriority: 2 RecurringType: neverRecur TaskStatus: incomplete Tags: [revision] 
<br>Updated Task: CS2105 revision lecture StartDate: 8-11-2016 EndDate: 8-11-2016 StartTime: 4:00PM EndTime: 6:00PM TaskPriority: 2 RecurringType: neverRecur TaskStatus: incomplete Tags: [revision]

Command `edit 44 sd: 09-11-2016 st: 10pm p: 2`<br>
Excepted 
<br>Original Task: revise for finals StartDate: 11-11-2016 EndDate: 11-11-2016 StartTime: 12:00PM EndTime: 2:00PM TaskPriority: 1 RecurringType: neverRecur TaskStatus: incomplete Tags: [cs2010][revision] 
<br>Updated Task: revise for finals StartDate: 9-11-2016 EndDate: 11-11-2016 StartTime: 10:00PM EndTime: 2:00PM TaskPriority: 2 RecurringType: neverRecur TaskStatus: incomplete Tags: [cs2010][revision]

### List command
Command `list`<br>
Expected: List all uncompleted tasks

Command `list-done`<br>
Expected: List all completed tasks

Command `list-all`<br>
Expected: List all uncompleted and completed tasks

Command `list-priority 1`<br>
Expected: List all tasks with low priority

Command `list-priority 3`<br>
Expected: List all tasks with high priority

Command `list-date 4-10-2016`<br>
Expected: List the tasks which start at 4-10-2016

Command `list-date 4-11-2016`<br>
Expected: List the tasks which start at 4-11-2016 

Command `list-date 11-11-2016`<br>
Expected: List the tasks which start at 11-11-2016

Command `list-date 2-11-2016`<br>
Expected: No tasks will be shown in the left panel 

### Delete command
Command: `delete `<br>
Expected: Should see invalid command result: a message explaining how to use delete command should be shown, along with parameters and examples

Command `delete 1`<br>
Expected: Remove the first task from task list

Command `delete 2`<br>
Expected: Remove the second task from task list

Command `delete 20`<br>
Expected: Remove the 20th task from task list

Command `delete 30`<br>
Expected: Remove the 30th task form task list

Command `delete 50`<br>
Expected: Remove the 50th task from task list

Command `delete 100`<br>
Expected: The task index provided is invalid

### Find command
Command: `find cs2010` <br>
Expected: All tasks with "cs2010" in either description or tags (includes substrings)

Command: `find cs2010 assign` <br>
Expected: All tasks with "cs2010" and "assign" in either description or tags (includes substrings)

Command: `find-tag cs2106`<br>
Expected: All tasks with "cs2106" in tags (includes substrings)

Command: `find-tag exam revision`<br>
Expected: All tasks with either "exam" or "revision" in tags (includes substrings)

### History command
Command: `hist`<br>
Expected: Right panel shows list of command history so far, for add/delete/edit/done/undone commands

### Undo command
Command: `undo` <br>
Expected: Undo most recent command executed, which is delete (to fill in command), and will add back the task

Command: `undo` <br>
Expected: Redo the previous undo command, and delete the task again

Command: `hist`<br>
Command: `undo 3` <br>
Expected: Undo the third command listed in history

### Save command
Command: `save ****` <br>
Expected: Taskell will reject as file name given has invalid symbol

Command: `save A:` <br>
Expected: Taskell will reject as the directory is invalid

Command: `save newDataFile` <br>
Expected: Taskell will create folder called "newDataFile" in folder of Taskell jar and relocate SampleData.xml to taskmanager.xml in that new folder, the old xml file will be deleted (due to the way Taskell is coded, the data file will be renamed to taskmanager.xml whenever `save` is executed)

Command: `save ` (to add external filepath here) <br>
Expected: Taskell will relocate taskmanager.xml to the given filepath, the old xml file will be deleted

### Clear command
Command: clear
Expected: A popup window will appear. Can choose 'ok' or 'cancel'. If choose ok, all data will be wiped out. If chose cancel, there will be no changes made.
<br>

Command: clear additionalParameter
Expected: <br>
Invalid command format! <br>
clear: Clears all tasks. <br>
Example: clear <br>
<br>
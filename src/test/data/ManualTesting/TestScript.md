# Manual Testing: Test Script
## How to load the sample data
1. Navigate to Taskell folder in File Explorer
2. Ensure both Taskell jar and SampleData.xml are in the same folder
3. Open config.json in a text editor
4. At taskManagerFilePath, replace "data/taskmanager.xml" with "SampleData.xml"
5. Save config.json
6. Launch Taskell jar

4a. If Taskell is launched via Eclipse's run Java Application, use "src/test/data/ManualTesting/SampleData.xml" instead.

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
Command:  <br>
Expected:

Command: `edit `<br>
Expected: Should see invalid command result: a message explaining how to use edit command should be shown, along with parameters and examples

### List command
Command:  <br>
Expected:

### Done command
Command: `done 1` <br>
Expected: First task "do CS2010 ps6" is marked done and disappears from list

Command: `list-done` <br>
Expected: Task "do CS2010 ps6" should be visible in list

### Undone command
Command: `undone 6` <br>
Expected: Task "do CS2010 ps6" is marked undone and disappears from list

Command: `list` <br>
Expected: Task "do CS2010 ps6" should be visible in list again (at index 1)

### Delete command
Command:  <br>
Expected:

Command: `delete `<br>
Expected: Should see invalid command result: a message explaining how to use delete command should be shown, along with parameters and examples

### Find command
Command: `find cs2010` <br>
Expected: All tasks with "cs2010" in either description or tags

Command: `find cs2010 assignment` <br>
Expected: All tasks with "cs2010" and "assign" in either description or tags

Command: `find-tag cs2106`<br>
Expected: All tasks with "cs2106" in tags

Command: `find-tag movie`<br>
Expected: All tasks with "movie" in tags

Command: `find-tag exam revision`<br>
Expected: All tasks with either "exam" or "revision" in tags

Command: `find assign`<br>
Expected: All tasks with "assign" in either description or tags, including substrings (means "assignment" which has "assign" is included as well)

Command: `find-tag cs`<br>
Expected: All tasks with "cs" in tags, including substrings (means "cs2010", "cs2106", which has "cs" is included as well)

Command: `find `<br>
Expected: Should see invalid command result: a message explaining how to use find command should be shown, along with parameters

### History command
Command: `hist`<br>
Expected: Right panel shows list of all commands executed so far, for add/delete/edit/done/undone commands

### Undo command
Command: `undo` <br>
Expected: Undo most recent command executed, which is delete, and will add back the task

Command: `undo` <br>
Expected: Redo the previous undo command, and delete the task again

Command: `hist`<br>
Command: `undo 3` <br>
Expected: Undo the third command listed in history, which is `add meet cs2013 tutor for consultation #study #project #meeting on 9-11-2016 from 5pm to 6pm`

### Calendar command
Command: `hist` <br>
Command: `cal` <br>
Expected: Toggle right panel view back to calendar view

### Save command
Command: `save ****` <br>
Expected: Taskell will reject as file name given has invalid symbol

Command: `save A:` <br>
Expected: Taskell will reject as the given directory is invalid

Command: `save newDataFile` <br>
Expected: Taskell will create folder called "newDataFile" in folder of Taskell jar and relocate SampleData.xml to taskmanager.xml in that new folder, the old xml file will be deleted (due to the way Taskell is coded, the data file will be renamed to taskmanager.xml whenever `save` is executed)

Command: `save ` (to add external filepath here) <br>
Expected: Taskell will relocate taskmanager.xml to the given filepath, the old xml file will be deleted

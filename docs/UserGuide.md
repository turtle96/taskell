# User Guide
* [Introduction](#introduction)
* [Quick Start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Glossary](#glossary)
* [Command Summary](#command-summary)
* [Appendix A](#appendix-a)
* [Appendix B](#appendix-b)

<!--- @@author A0142073R ---> 

## Introduction
Are you having a hard time remembering all the work you have to do? Do you have trouble finding a task manager that suits your preference for keyboard input? Well, worry no more, Taskell is here for you! <br>
Taskell will be your personal secretary. It will keep track of your daily tasks and remind you of any important dates and deadlines. What distinguishes Taskell from other task managers is that Taskell only requires a single line of command for every task input. This means that you can record each one of your tasks with just a single statement. You will no longer have to use a mouse if you do not wish to. <br>
Ready to begin life anew with a more efficient task manager? Read on to find out more! 

<!--- @@author --> 

## Quick Start
This section guides you through the installation of Taskell. <br>
Step 1: Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>

  > Having any Java 8 version is not enough. <br>
    This application will not work with earlier versions of Java 8.

Step 2: Download the latest `Taskell.jar` from <a href="https://github.com/CS2103AUG2016-W15-C3/main/releases">here</a>.<br>
	<br> &nbsp; &nbsp; &nbsp; &nbsp; <img src="images/Icon.png" width="100"></br>
	
Step 3: Copy the file to the folder you want to use as the home folder for your Task Manager.<br>
Step 4: Double-click the file to start the application. The GUI should appear in a few seconds.
 <br><p align="center"><img src="images/screenshots/GUI.png" width="800"><br>
Figure 1: A screenshot of the Graphical User Interface (GUI)<br>
</p>

Step 5:	Type the relevant command in the command box and press <kbd>Enter</kbd> to execute it.<br>
Step 6: Some example commands you can try:<br>
   * `list` : Displays all tasks
   * `add` buy MA1101R textbook today : Adds a task called buy MA1101R textbook to be done by today
   * `delete` 3 : Deletes the third task shown in the current list
   * `exit` : Exits the application <br>
Refer to the [Features](#features) section below for details of each command.<br>

<!--- @@author A0139257X --->
## Features

This section shows the different commands that you can use in Taskell. Words that are in UPPER_CASE are parameters. The parameters are listed below.
- TASK: Indicates the content of a work
- DATE: Indicates a date

> Please refer to Appendix A for the date formats that Taskell supports. <br> 
> Default start date has been set to today's date. <br>
> Default end date has been set to be the same as the start date. <br>

- TIME: Indicates a time

> Please refer to Appendix B for the time formats that Taskell supports. <br>
> Default start time has been set to 12:00AM <br>
> Default end time has been set to 11:59PM <br>
> If the task is added today and no start time is provided, the default start time will be set to the current time.

- PRIORITY: Indicates the level of importance of a task ranging from level 0 to 3. Level 0, 1, 2, 3 indicates default, low, medium and high priority respectively

> In the GUI, level 1,2 and 3 tasks are marked as green, yellow and red respectively. Tasks with default priority level are not marked with any colors.

- RECURRING: Indicates the repetitive nature of a task. A task can be repeated daily, weekly or monthly
- TAG: Indicates the category in which a task belongs to

Words that are in <i>italics</i> are used to identify the parameters while words enclosed in SQUARE_BRACKETS are optional. <br>
INDEX refers to the index number shown in the most recent listing.
<!--- @@author --->

#### Viewing list of commands : `help`
You can use the `help` command to view a summary of all the commands. <br>

To open the help window<br>
 Format: `help`
 
<!--- @@author A0139257X --->

#### Adding a task: **`add`**
You can use the `add` command to add different tasks.<br>

To add a floating task<br>
Format: 
`add` TASK <br>
Example: `add` Read Harry Potter book <br>

To add a task with priority<br>
Format:
`add` TASK <i>p/</i>PRIORITY <br>
Example: `add` Complete math assignment <i>p/</i>3<br>

To add a task with tag(s)<br>
Formats:
- `add` TASK <i>#</i>TAG <br>
Example: `add` Meet Alice in Bugis <i>#</i>friends<br>
- `add` TASK <i>#</i>TAG [<i>#</i>MORE_TAGS] <br>
Example: `add` Swimming with Jane <i>#</i>friends <i>#</i>leisure <br>

To add a recurring task <br>
Format:
`add` TASK <i>r/</i>RECURRING <br>
Example: `add` Read newspaper <i>on</i> mon <i>r/</i>daily
> Take Note! <br>
> Floating tasks are not allowed to have recurring status since they do not have any element of date or time.

To add a task with date and time<br>
Formats:
- `add` TASK <i>from</i> START_DATE <i>to</i> END_DATE<br>
Example: `add` Go camping at Kota Tinggi <i>from</i> 3-jun-2016 <i>to</i> 7-jun-2016
- `add` TASK <i>from</i> START_TIME <i>to</i> END_TIME <br>
Example: `add` Watch Dr Strange <i>from</i> 7.30pm <i>to</i> 9.25pm


To allow greater flexibility in the command format, Taskell supports a few natural variation such as <i>by</i>, <i>on</i> and <i>at</i>.
<br>

The <i>by</i> keyword indicates that the task is a deadline task. Any date or time preceded by this keyword will be stored as an end date and end time respectively.
<br>
Formats:
- `add` TASK <i>by</i> DATE <br>
Example: `add` Buy textbook <i>by</i> tuesday
- `add` TASK <i>by</i> TIME<br>
Example: `add` visit Sandy at her house by the seaside <i>by</i> 3.35pm
- `add` TASK <i>by</i>  DATE <i>by</i>  TIME <br>
Example: `add` Do lab homework <i>by</i> Friday <i>by</i> 7pm
<br>

The <i>on</i> keyword indicates that the task has to be done on the given date. Any date preceded by this keyword will be stored as a start date.
<br>
Format:
`add` TASK <i>on</i> DATE<br>
Example: `add` Go for meeting <i>on</i> mon <br>


The <i>at</i> keyword indicates that the task has to be done at the given time. Any time preceded by this keyword will be stored as a start time.
<br>
Format:
`add` TASK <i>at</i> TIME <br>
Example: `add` Go for meeting <i>at</i> 3pm <br>


Having understood the aforementioned behaviors of the <i>by</i>, <i>on</i>, <i>at</i>, <i>from</i> and <i>to</i> keyword, you can fuse them together to form more complex tasks.
<br>
Formats:
- `add` TASK <i>on</i> DATE <i>at</i> TIME <br>
Example: `add` Go for meeting <i>on</i> Sunday <i>at</i> 3pm <br>
- `add` TASK <i>by</i> TIME <i>on</i> DATE <br>
Example: `add` Go for meeting <i>by</i> 3pm <i>on</i> 1-jan
- `add` TASK <i>from</i> DATE <br>
Example:`add` Go out with friends <i>from</i> 9am
- `add` TASK <i>on</i> DATE <i>from</i> TIME <i>to</i> TIME <br>
Example: `add` Watch webcast <i>on</i> sat <i>from</i> 4.45pm <i>to</i> 7pm
- `add` TASK <i>from</i> DATE <i>to</i> DATE <i>from</i> TIME <i>to</i> TIME [<i>#</i>TAG] [<i>p/</i>PRIORITY]  [<i>r/</i>RECURRING] <br>
Example: `add` Holiday in San Francisco at Ocean Beach by the sea <i>from</i> 7-may <i>to</i> 2-jun <i>from</i> 9am <i>to</i> 11pm <i>#</i>holiday <i>#</i>leisure <i>p/</i>3 <i>r/</i>monthly
<br><p align="center"><img src="images/screenshots/addCmd.png" width="800"></br>
Figure 2: Adds a new task to Taskell<br>

> Take note! <br>
> A task can only contain up to a maximum of 1 start time, 1 end time, 1 start date and 1 end date. Any additional parameter will be treated as part of the description. <br>
> Any date or time not preceded by 'by', 'on', 'at', 'from' or 'to' will also be treated as part of the description.

Moreover, Taskell is able to automatically make adjustments to the date and time so that the task entered remains relevant. <br> 
Examples: 
- `add` Create powerpoint slides for project <i>from</i> 11pm <i>to</i> 3am <br>
This task will be added as a valid task that starts today at 11pm and ends  tomorrow at 3am.
- `add` Staycation with friends <i>from</i> sunday <i>to</i> tues <br>
If today is a Saturday, this task will be added as a valid task that starts from tomorrow and end on this coming Tuesday. <br>
If today is a Sunday, this task will be added as a valid task that starts from next Sunday and end on the following Tuesday.

<!--- @@author -->

<!--- @@author A0142073R --->

#### Editing a task : `edit`
You can use the `edit` command to edit any parts of a task. <br>

Format: 
`edit` INDEX [<i>st:</i> NEW_START_TIME] [<i>et:</i> NEW_END_TIME] [<i>desc:</i> NEW_DESCRIPTION] [<i>sd:</i> NEW_START_DATE] [<i>ed:</i> NEW_END_DATE] [<i>p:</i> NEW_PRIORITY] 
<br><p align="center"><img src="images/screenshots/editCmd.png" width="800"></br>
Figure 3: Edits the third task on the list<br>

Entering "`edit` 3 desc: send all emails sd: 11-11-2016 ed: 12-11-2016 st: 3pm et: 4pm p: 3", will update description to "send all emails", start date to 11-11-2016, end date to 12-11-2016, start time to 3pm end time to 4pm and priority to 3.<br>

> Take Note! <br>
> * You only need to key in the necessary parameters of the task you would like to change. Not all parameters are required.
> * Order of the parameters is not important.
    
<!--- @@author --->

<!--- @@author A0142130A --->  

#### Finding tasks: `find`
You can use the `find` command to view tasks with specific keywords.<br> 

Formats: <br>
- `find` KEYWORD [MORE_KEYWORDS]<br>
Displays a list of tasks with description or tags matching all the keywords.<br>
Example: `find` banana milk essay<br>
Displays a list of all the tasks with description or tags matching 'banana', 'milk', and 'essay'. <br>

> Take Note! <br>
> Tasks with words that match the keyword includes those that contain the keyword. For example, searching for "book" will match with "book", "textbook", "storybook" etc.

- `find-tag` TAG [MORE_TAGS]<br>
Displays list of tasks with the same tags.<br>
Example: `find-tag` homework essay cs2103<br>
Displays a list of tasks having tagged as either 'homework', 'essay', or 'cs2103'.
 <br><p align="center"><img src="images/screenshots/find_cs2010_results.png" width="800"></br> <br>
Figure 4: `find` cs2010 displays list of tasks with 'cs2010' as one of the keywords in task description

> Take Note! <br>
> * The order of the keywords does not matter. e.g. 'chicken egg' will match 'egg chicken'.
> * Tasks matching at least one keyword will be displayed.
    e.g. 'chicken' will match 'chicken duck'
    
<!--- @@author --->

<!--- @@author A0148004R --->

#### Deleting a task : `delete`
You can use the `delete` command to delete a task at a specified INDEX.<br>

Format: `delete` INDEX <br>
Example: `find` violin, then `delete` 1<br>
Deletes the first task shown in the list after executing the `find` command.
<br><p align="center"><img src="images/screenshots/deleteCmd.png" width="800"></br>
Figure 5: `delete` 3 deletes the first task on the list<br>

#### Marking a task as completed: `done`
You can use the `done` command to mark an uncompleted task as completed.<br>

Format: `done` INDEX<br>
Example: `done` 1<br>
Marks the first task as finished and moves it to the list of completed tasks.
<br><p align="center"><img src="images/screenshots/doneCmd.png" width="800"></br>
Figure 6: Marks the third task as finished<br>

#### Marking a task as incomplete: `undone`
You can use the `undone` command to mark a completed task as uncompleted.<br>

Format: `undone` INDEX<br>
Example: `undone` 1<br>
Marks the first task as incomplete and moves it to the lsit of uncompleted tasks.<br>

<!--- @@author --->

<!--- @@author A0148004R --->

#### Listing tasks : `list`
You can use the `list` command to display a certain type of tasks.<br>

Formats: 
- `list` <br>
Displays a list of uncompleted tasks.<br>
- `list-all` <br>
Displays a list of all tasks, both completed and uncompleted.<br>
- `list-date` DATE <br> 
Displays a list of all the tasks due on the specified date.<br>
- `list-done` <br>
Displays a list of completed tasks.<br>
- `list-priority` PRIORITY<br>
Displays a list of tasks with the specified priority.
<br><p align="center"><img src="images/screenshots/listAllCmd.png" width="800"></br>
Figure 8: `list-all` displays both completed and uncompleted tasks<br>
<!-- @@author -->
   
<!--- @@author A0142130A ---> 

#### Clearing all entries : `clear`
You can use the `clear` command to permanently clear **all** task data. <br>

Format: `clear`  

<p align="center"><img src="images/screenshots/clearCmd.png" width="800"></br>
Figure 9: `clear` prompts a confirmation pop-up window<br>

> Take Note! <br>
> * `clear` command is irreversible!

#### Showing history : `history` or `hist`
You can view the command history available for undo on the right panel when undoing your previous commands.<br>

Format: `history` or `hist` 
<p align="center"><img src="images/screenshots/historyCmd.png" width="800"></br> <br>
Figure 10: `hist` displays a list of command history on the right panel

> Take Note! <br>
> * Only commands that are available for undo will be shown here. 
> * Refer to the `undo` section below for more information on which commands can be undone.

#### Reverting previous action : `undo`
If you wish to undo your most recent action, you can do so by using the `undo` command.<br>
To undo previous commands, <kbd>Enter</kbd> `hist` to see a list of 
previous commands that can be undone.<br>
Then use the `undo` command together with the specified INDEX from the list of command history.

Formats: <br>
- `undo`<br> 
Undo the most recent command executed.<br>
- `undo` INDEX<br> 
Undo the command at the specified index in the  command history.<br>
Example: `hist`, then `undo` 3, will undo the third command in the command history.
<p align="center"><img src="images/screenshots/undoCmd.png" width="800"></br>
Figure 11: `undo` reverts the last item on the list of command history

> Take Note! <br>
> * Undo command only supports `add`, `edit`, `delete`, `done`, `undone` and `undo` commands.
> * `clear` command is irreversible!

#### Showing calendar view : `calendar` or `cal`
You can use the `calendar` command to view a calendar. <br>

Format: `calendar` or `cal` <br>

You can refer the calendar on the right panel when adding tasks and scheduling events. A fine red line is used to indicate the current time.
<br> <p align="center"> <img src="images/screenshots/calendarCmd.png" width="800"> </br>
Figure 12: Example of how the current time marker looks like.<br>

The calendar view reflects the tasks shown in the left panel. Each block is marked with the index corresponding to the task, meaning a block marked "4" would correspond to the fourth task.<br>
By default, the calendar view will be shown on the right panel. As the `history` command also utilises the right panel to display the command history, you have to use the `calendar` command to toggle between both views.<br>

> Take note! <br>
> Entering other commands (i.e. `find`, `add`) will revert the right panel back to calendar as it is the default view. <br>


#### Saving the information in Taskell : `save`
You can use the `save` command to specify the path of a folder to store Taskell's data file. Please note that you should have permissions to access the folder. <br>

Format: `save` FILE_PATH<br>
Example: `save` C:\Users\Jim\Documents
<br>

To obtain the file-path, navigate to the required file in your File Explorer. Copy the path at the top of the
screen and paste it into Taskell. Refer to the diagram below for an example of a file-path. <br>

<p align="center"> <img src="images/filepath_screenshot.png" width="1000"> </br>
Figure 13: Screenshot of File Explorer in Windows.  <br>

If the specified directory is valid but the file is missing, for example, if you entered `save` C:\Users\Jim\Documents\Project,  and 'C:\Users\Jim\Documents\Project' is valid
but 'project' file has not been created, Taskell will create the file for you.<br>


> Take Note! <br>
> * If you only specify a folder name without directory, i.e. `save` project, Taskell will create a file named 'project' within Taskell's own directory. 
>Whereas `save` C:/Users/Jim/Documents/project will open a file named 'project' within your desktop's Documents folder.
> * Both Windows and Linux OS have restricted symbols that are not allowed for folder names. Please be aware of the symbols
shown in the table below.
    
Windows |  Linux 
-------- | :-------- 
       > | >
       < | <
       : | :
       " | &
       / | /
       \ | &#124;
  &#124; | 
       ? |
       * |
Table 1: Restricted symbols in Windows and linux


<!--- @@author ---> 

#### Exiting the program : `exit`
You can use the `exit` command to exit Taskell.
 
Format: `exit`  

Alternatively, you can hold down <kbd>Alt</kbd> + <kbd>F4</kbd>. <br>

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the application in the other computer and overwrite the empty data file it creates with the file that contains the data in your previous Taskell folder.<br>

<!--- @@author A0142130A --->

**Q**: Do I have to save the data every time I enter new tasks? <br>
**A**: No, Taskell automatically saves your data every time you enter new tasks. Use `save` only when you want to
transfer your data to a new location on your computer. <br>


<!--- @@author --->

<!--- @@author A0139257X ---> 

## Glossary

1. GUI: Graphical User Interface
2. Floating task: A task without date and time

<!--- @@author --->

<!--- @@author A0142073R ---> 
     
## Command Summary

Command | Format  
-------- | :-------- 
Add | `add` TASK <i>p/</i>[PRIORITY] <i>#</i>[TAG] <br> `add` TASK <i>by</i> DATE <br>  `add` TASK <i>by</i> TIME <br> `add` TASK <i>by</i> DATE <i>by</i> TIME <br> `add` TASK <i>at</i> TIME <br> `add` TASK <i>on</i> DATE <br> `add` TASK <i>on</i> DATE <i>by</i> TIME <br> `add` Task <i>on</i> DATE <i>at</i> TIME <br> `add` TASK <i>from</i> DATE <i>to</i> DATE <br> `add` TASK <i>from</i> TIME <i>to</i> TIME <br> `add` TASK <i>on</i> DATE <i>from</i> TIME <i>to</i> TIME <i>p/</i>[PRIORITY] <i>#</i>[TAG] <i>r/</i>[RECURRING]
Calendar View | `calendar` or `cal`
Clear | `clear`
Delete | `delete` INDEX
Edit | `edit` INDEX <br> `desc:` NEW_DESCRIPTION <br> `sd:` NEW_START_DATE <br> `st:` NEW_START_TIME <br> `ed:` NEW_END_DATE <br> `et:` NEW_END_TIME <br> `p:` NEW_PRIORITY
Exit | `exit` <br> <kbd>Alt</kbd> + <kbd>F4</kbd>
Find | `find` KEYWORD [MORE_KEYWORDS]
Find by tag | `find-tag` TAG [MORE_TAGS]
Help | `help`
History | `history` or `hist`
List all tasks | `list-all`
List by priority | `list-priority` PRIORITY
List by specified date | `list-date` DATE
List completed tasks | `list-done`
List uncompleted tasks| `list`
Mark task as finished | `done` INDEX
Mark task as incomplete | `undone` INDEX
Save | `save` FILE_PATH
Undo | `undo` <br> `undo` INDEX

<!-- @@author -->

<!--- @@author A0139257X --->
## Appendix A

Supported Date Format |   Example  
-------- | :-------- 
DD-MM-YYYY |1-1-2016 <br> 1/2/2016<br> 1-mar-2016 <br> 1-April-2016 <br> 1.May.2016 <br> 1.Jun.2016
MM-YYYY  | jul-2016 <br> july-2016
MM  | jan <br> sept <br> December
day  | today <br> tdy <br> tomorrow <br> tmr <br> thursday <br> thurs <br> thu

## Appendix B

Supported Time Format |   Example  
-------- | :-------- 
In 12-hour format | 12am <br> 5:30am<br> 1pm <br> 10-35pm <br> 11.45pm
In words | midnight <br> afternoon <br> noon

<!-- @@author -->


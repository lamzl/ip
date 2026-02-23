# ChatLZL User Guide

ChatLZL is a desktop app for managing your daily tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, ChatLZL can get your task management done faster than traditional GUI apps.

## Quick Start

1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `chatlzl.jar` from the [releases tab](../../releases).
3. Copy the file to the folder you want to use as the home folder for your ChatLZL.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar chatlzl.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. e.g. typing `ls` and pressing Enter will list all your tasks.

## Features

### Adding a Todo: `todo`
Adds a simple task without any date or time attached to it.
* **Format:** `todo DESCRIPTION`
* **Example:** `todo read book`

### Adding a Deadline: `deadline`
Adds a task that needs to be done before a specific date/time.
* **Format:** `deadline DESCRIPTION /by DEADLINE`
* **Example:** `deadline return book /by Sunday`

### Adding an Event: `event`
Adds a task that starts at a specific time and ends at a specific time.
* **Format:** `event DESCRIPTION /from START /to END`
* **Example:** `event project meeting /from Mon 2pm /to 4pm`

### Listing all tasks: `ls`
Shows a list of all tasks currently in your task list.
* **Format:** `ls`

### Finding tasks by keyword: `find`
Finds tasks whose descriptions contain the given keyword.
* **Format:** `find KEYWORD`
* **Example:** `find book` returns all tasks with "book" in the description.

### Marking a task as done: `mark`
Marks a specific task in the list as completed.
* **Format:** `mark INDEX`
    * The index must be a positive integer (1, 2, 3...) corresponding to the task number in the list.
* **Example:** `mark 2` marks the 2nd task in the list as done.

### Unmarking a task: `unmark`
Marks a specific task in the list as not completed yet.
* **Format:** `unmark INDEX`
* **Example:** `unmark 2` marks the 2nd task in the list as incomplete.

### Deleting a task: `rm`
Removes a specific task from the list permanently.
* **Format:** `rm INDEX`
* **Example:** `rm 3` deletes the 3rd task in the task list.

### Exiting the program: `bye`
Exits the application and saves your data securely.
* **Format:** `bye`

## Data Storage
ChatLZL saves your data automatically to your hard disk (`./data/chatlzl.txt`) after any command that changes the data. There is no need to save manually.
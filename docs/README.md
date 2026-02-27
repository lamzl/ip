# ChatLZL User Guide

ChatLZL is a task manager that utilises the CLI (Command Line Interface). If you can type fast and are familiar with the command line, ChatLZL can get your task management done faster than traditional GUI apps.

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding a Todo: `todo`](#adding-a-todo-todo)
    * [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
    * [Adding an Event: `event`](#adding-an-event-event)
    * [Listing all tasks: `ls`](#listing-all-tasks-ls)
    * [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
    * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    * [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    * [Deleting a task: `rm`](#deleting-a-task-rm)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
* [Data Storage](#data-storage)
* [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have Java `17` or above installed in your Computer.
2. Download `chatlzl.jar` from the releases tab.
3. Copy the file to the folder you want to use as the home folder for your ChatLZL.
4. Open a command terminal, `cd` into the folder you put the jar file in, and type `java -jar chatlzl.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. e.g. typing `ls` and pressing Enter will list all your tasks.

---

## Features

### Adding a Todo: `todo`
Adds a task without any date or time attached.
* **Format:** `todo DESCRIPTION`
* **Example:** `todo read book`

### Adding a Deadline: `deadline`
Adds a task that needs to be done before a specific date or time.
* **Format:** `deadline DESCRIPTION /by DEADLINE`
* **Example:** `deadline return book /by Sunday`

### Adding an Event: `event`
Adds a task that starts at a specific time and ends at a specific time.
* **Format:** `event DESCRIPTION /from START /to END`
* **Example:** `event project meeting /from Mon 2pm /to 4pm`

### Listing all tasks: `ls`
Lists of all tasks currently in your task list.
* **Format:** `ls`

### Finding tasks by keyword: `find`
Finds tasks whose descriptions contain the given keyword.
* **Format:** `find KEYWORD`
* **Example:** `find book` returns all tasks with "book" in the description.

### Marking a task as done: `mark`
Marks a specific task in the list as completed.
* **Format:** `mark INDEX`
    * The index must be a positive integer that is present in the list.
* **Example:** `mark 2` marks the 2nd task in the list as done.

### Unmarking a task: `unmark`
Marks a specific task in the list as not completed yet.
* **Format:** `unmark INDEX`
* **Example:** `unmark 2` unmarks the second item on the list.

### Deleting a task: `rm`
Removes a specific task from the list permanently.
* **Format:** `rm INDEX`
* **Example:** `rm 3` deletes the third item in the task list.

### Exiting the program: `bye`
Exits the application and saves your data securely.
* **Format:** `bye`

---

## Data Storage
ChatLZL saves your data automatically to your hard disk (`./data/chatlzl.txt`) after any command that changes the data. There is no need to save manually. Your tasks will be present even after exiting the app!

---

## Command Summary

| Action | Format | Example                                       |
| :--- | :--- |:----------------------------------------------|
| **Todo** | `todo DESCRIPTION` | `todo grocery shopping`                       |
| **Deadline** | `deadline DESCRIPTION /by DEADLINE` | `deadline return book /by Sunday`             |
| **Event** | `event DESCRIPTION /from START /to END` | `event project meeting /from Mon 2pm /to 4pm` |
| **List** | `ls` | `ls`                                          |
| **Find** | `find KEYWORD` | `find book`                                   |
| **Mark** | `mark INDEX` | `mark 2`                                      |
| **Unmark** | `unmark INDEX` | `unmark 2`                                    |
| **Delete** | `rm INDEX` | `rm 3`                                        |
| **Exit** | `bye` | `bye`                                         |
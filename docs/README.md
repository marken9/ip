# Painter User Guide

## Introduction

Painter is a command-line task management application written in Java.

It allows users to:

- Add tasks (Todo, Deadline, Event)
- Mark and unmark tasks
- Delete tasks
- Clear all tasks
- Find tasks by keyword 
- Automatically save and load tasks from a file

---

## Quick Start

1. Ensure you have **Java 17 or later** installed.
2. Download the jar file from [here](https://github.com/marken9/ip/releases/tag/v0.2)
3. Move the jar file to any folder and create an empty folder named "data" beside it.
4. Open a command terminal, cd into the folder you put the jar file in, and use the following command.

```
java -jar painter.jar
```

5. Type commands in the console and press Enter.

---

## Features

---

### Viewing all tasks: `list`

Displays all tasks currently stored in the task list.

**Format**

```
list
```

---

### Adding a todo: `todo`

Adds a simple task without a date or time.

**Format**

```
todo DESCRIPTION
```

**Example**

```
todo Read CS2113 lecture notes
```

---

### Adding a deadline: `deadline`

Adds a task with a due date.

**Format**

```
deadline DESCRIPTION /by DATE
```

**Example**

```
deadline Submit assignment /by Friday
```

---

### Adding an event: `event`

Adds a task with a start and end time.

**Format**

```
event DESCRIPTION /from START /to END
```

**Example**

```
event Team meeting /from 2pm /to 4pm
```

---

### Marking a task: `mark`

Marks a task as completed.

**Format**

```
mark INDEX
```

**Example**

```
mark 2
```

- The index must be a positive integer 1, 2, 3, ...

---

### Unmarking a task: `unmark`

Marks a task as not completed.

**Format**

```
unmark INDEX
```

**Example**

```
unmark 2
```

---

- The index must be a positive integer 1, 2, 3, ...

### Deleting a task: `delete`

Deletes a task from the list.

**Format**

```
delete INDEX
```

**Example**

```
delete 3
```

- The index must be a positive integer 1, 2, 3, ...

---

### Clearing all tasks: `clear`

Removes all tasks from the task list.

**Format**

```
clear
```

---

### Finding tasks: `find`

Finds tasks containing a keyword in their description.

**Format**

```
find KEYWORD
```

**Example**

```
find meeting
```

- Only the first keyword is used.

---

### Exiting the program: `bye`

Closes the application.

**Format**

```
bye
```

or

```
exit
```

---

## Storage

Tasks are automatically saved to:

```
./data/painter.txt
```

The file uses `;` as a separator.

Do not manually edit the file unless you understand the storage format.

Example storage format:

```
T;O;Read book
D;X;Submit assignment;Friday
E;O;Meeting;2pm;4pm
```

---

## Command Summary

| Command  | Format                                  |
| -------- | --------------------------------------- |
| list     | `list`                                  |
| todo     | `todo DESCRIPTION`                      |
| deadline | `deadline DESCRIPTION /by DATE`         |
| event    | `event DESCRIPTION /from START /to END` |
| mark     | `mark INDEX`                            |
| unmark   | `unmark INDEX`                          |
| delete   | `delete INDEX`                          |
| clear    | `clear`                                 |
| find     | `find KEYWORD`                          |
| bye      | `bye` or `exit`                         |

---

## Notes

- Input must not contain `;` (used internally as file separator).
- Commands are case-sensitive.

---

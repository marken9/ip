package painter.ui;
import painter.task.TaskList;

/**
 * Handles all user interface interactions for the Painter application.
 * The Ui class is responsible for displaying messages, errors,
 * and task-related information to the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints a separator line.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a formatted message surrounded by separator lines.
     *
     * @param msg The message to display.
     */
    public void printMessage(String msg) {
        printLine();
        System.out.println(msg);
        printLine();
    }

    /**
     * Prints an error message.
     *
     * @param msg The error message to display.
     */
    public void printError(String msg) {
        printMessage("Error: " + msg);
    }

    /**
     * Prints an exception message in a formatted manner.
     *
     * @param e The exception to display.
     */
    public void printException(Exception e) {
        printMessage("Hey! Stop trying to crash my system! Error: " + e.getMessage());
    }

    /**
     * Prints all tasks currently in the task list.
     *
     * @param taskList The TaskList to display.
     */
    public void printTaskList(TaskList taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        System.out.print(taskList.toString()); // display format
        printLine();
    }

    /**
     * Prints tasks that match a search keyword.
     *
     * @param taskList The TaskList containing matching tasks.
     */
    public void printMatchingTasks(TaskList taskList) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        System.out.print(taskList.toString()); // display format
        printLine();
    }

    /**
     * Prints confirmation that a task has been added.
     *
     * @param taskList The TaskList after the new task has been added.
     */
    public void printTaskAdded(TaskList taskList) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTaskString(taskList.getTaskCount()));
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
        printLine();
    }

    /**
     * Prints confirmation that a task has been deleted.
     *
     * @param removedTask The string representation of the removed task.
     * @param remaining The number of remaining tasks.
     */
    public void printTaskDeleted(String removedTask, int remaining) {
        printLine();
        System.out.println("The following task was sent to the void: " + removedTask);
        System.out.println("Now you have " + remaining + " tasks in the list.");
        printLine();
    }

    /**
     * Prints confirmation that a task has been marked or unmarked.
     *
     * @param taskStr The string representation of the task.
     * @param isDone True if the task was marked as done, false if unmarked.
     */
    public void printTaskMarked(String taskStr, boolean isDone) {
        printLine();
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:\n" + taskStr);
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n" + taskStr);
        }
        printLine();
    }
}

package painter.ui;
import painter.task.TaskList;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    public void printLine() {
        System.out.println(LINE);
    }

    public void printMessage(String msg) {
        printLine();
        System.out.println(msg);
        printLine();
    }

    public void printError(String msg) {
        printMessage("Error: " + msg);
    }

    public void printException(Exception e) {
        printMessage("Hey! Stop trying to crash my system! Error: " + e.getMessage());
    }

    public void printTaskList(TaskList taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        System.out.print(taskList.toString()); // display format
        printLine();
    }

    public void printTaskAdded(TaskList taskList) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTaskString(taskList.getTaskCount()));
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
        printLine();
    }

    public void printTaskDeleted(String removedTask, int remaining) {
        printLine();
        System.out.println("The following task was sent to the void: " + removedTask);
        System.out.println("Now you have " + remaining + " tasks in the list.");
        printLine();
    }

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

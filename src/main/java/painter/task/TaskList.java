package painter.task;
import java.util.ArrayList;

public class TaskList {
    public static final String LINESTRING = "____________________________________________________________";

    protected ArrayList<Task> taskList;
    protected int taskCount;

    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public String getTaskString(int taskNumber) {
        return taskList.get(taskNumber - 1).toString();
    }

    public static void printLine() {
        System.out.println(LINESTRING);
    }

    public static void printSentence(String x) {
        printLine();
        System.out.println(x);
        printLine();
    }
    public void add(Task t) {
        taskList.add(t);
        taskCount += 1;
    }

    public void markTaskList(int n, boolean isDone) {
        if (n > taskCount) {
            printSentence("You only have " + taskCount + " tasks but you entered " + n + ".");
            return;
        }

        if (n <= 0) {
            printSentence("Task index cannot be 0 or negative");
            return;
        }

        if (isDone) {
            taskList.get(n - 1).markAsDone();
            printSentence("Nice! I've marked this task as done: " + taskList.get(n - 1));
        } else {
            taskList.get(n - 1).markAsUndone();
            printSentence("OK, I've marked this task as not done yet: " + taskList.get(n - 1));
        }
    }

    public void deleteTask(int taskNumber) {
        String removeTask = taskList.get(taskNumber - 1).toString();
        taskList.remove(taskNumber - 1);
        taskCount--;
        printLine();
        System.out.println("The following task was sent to the void: " + removeTask);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();

    }

    public String toString() {
        String result = "";
        for (int i = 0; i < taskCount; i += 1) {
            result = result + Integer.toString( i + 1 ) + ". " + (taskList.get(i).toString()) + System.lineSeparator();
        } // To explore StringBuilder class if free
        return result;
    }

}

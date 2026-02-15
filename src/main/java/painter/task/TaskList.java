package painter.task;

public class TaskList {
    public static final int TASKLISTLENGTH = 100;
    public static final String LINESTRING = "____________________________________________________________";

    protected Task[] taskList;
    protected int taskCount;

    public TaskList() {
        taskList = new Task[TASKLISTLENGTH];
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public String getTaskString(int taskNumber) {
        return taskList[taskNumber - 1].toString();
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
        taskList[taskCount] = t;
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
            taskList[n - 1].markAsDone();
            printSentence("Nice! I've marked this task as done: " + taskList[n - 1].toString());
        } else {
            taskList[n - 1].markAsUndone();
            printSentence("OK, I've marked this task as not done yet: " + taskList[n - 1].toString());
        }
    }

    public void unmarkTaskList(int n) {
        taskList[n].markAsUndone();
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < taskCount; i += 1) {
            result = result + Integer.toString( i + 1 ) + ". " + (taskList[i].toString()) + System.lineSeparator();
        } // To explore StringBuilder class if free
        return result;
    }

}

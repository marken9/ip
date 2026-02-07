package task;

public class TaskList {
    protected Task[] taskList;
    protected int taskCount;

    public TaskList() {
        taskList = new Task[100];
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public String printTask(int n) {
        return taskList[n - 1].toString();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
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
        String a = "";
        for (int i = 0; i < taskCount; i += 1) {
            a = a + Integer.toString( i + 1 ) + ". " + (taskList[i].toString()) + System.lineSeparator();
        }
        return a;
    }

}

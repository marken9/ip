package task;

public class TaskList {
    protected Task[] taskList;
    protected int nTasks;


    public TaskList() {
        taskList = new Task[100];
        nTasks = 0;
    }

    public int getnTasks() {
        return nTasks;
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
        taskList[nTasks] = t;
        nTasks += 1;
    }

    public void markTaskList(int n, boolean isDone) {
        if (n > nTasks) {
            printSentence("You only have " + nTasks + " tasks but you entered " + n + ".");
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
        for (int i = 0; i < nTasks; i += 1) {
            a = a + Integer.toString( i + 1 ) + ". " + (taskList[i].toString()) + System.lineSeparator();
        }
        return a;
    }

}

import java.util.Scanner;

import task.Task;

public class Painter {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printSentence(String x) {
        printLine();
        System.out.println(x);
        printLine();
    }

    public static void markTaskList(boolean mark, Task[] taskList, int nTasks, int n) {
        if (n > nTasks) {
            printSentence("You only have " + nTasks + " tasks but you entered " + n + ".");
            return;
        }
        if (mark) {
            taskList[n - 1].markAsDone();
            printSentence("Nice! I've marked this task as done: " + taskList[n - 1].toString());
        } else {
            taskList[n - 1].markAsUndone();
            printSentence("OK, I've marked this task as not done yet: " + taskList[n - 1].toString());
        }
    }

    public static void printTaskList(Task[] taskList, int nTasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < nTasks; i += 1) {
            System.out.println((i + 1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
        printLine();
    }


    public static void main(String[] args) {
        printSentence("Hello expendable. I'm Painter :D\nPlay with my task list and I'll open the way to the escape submarine");

        Task[] taskList = new Task[100];
        int nTasks = 0;

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            line = line.strip();

            switch (line) {
            case "bye":
                printSentence("Bye. Hope to see you again soon!");
                return;
            case "list":
                printTaskList(taskList, nTasks);
                break;
            default:
                String[] words = line.split(" ");
                // Code assumes that mark/unmark is called correctly
                // e.g. "mark string" will crash.
                if (words[0].equals("mark")) {
                    markTaskList(true, taskList, nTasks, Integer.parseInt(words[1]));
                } else if (words[0].equals("unmark")) {
                    markTaskList(false, taskList, nTasks, Integer.parseInt(words[1]));
                } else {
                    taskList[nTasks] = new Task(line);
                    nTasks += 1;
                    printSentence("Added: " + line);
                }
            }
        }
    }
}

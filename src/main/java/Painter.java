import java.util.Scanner;
import java.util.Arrays;

import task.TaskList;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class Painter {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printSentence(String x) {
        printLine();
        System.out.println(x);
        printLine();
    }


    public static void printTaskList(TaskList taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        System.out.print(taskList.toString());
        printLine();
    }

    public static void printAddTask(TaskList taskList) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.printTask(taskList.getnTasks()));
        System.out.println("Now you have " + Integer.toString(taskList.getnTasks()) + " tasks in the list.");
        printLine();
    }


    public static void main(String[] args) {
        printSentence("Hello expendable. I'm Painter :D\nPlay with my task list and I'll open the way to the escape submarine");

        TaskList taskList = new TaskList();
        int nTasks = 0;

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            line = line.strip();

            String[] sentence;
            sentence = line.split(" ");
            switch (sentence[0]) {
            case "bye":
                printSentence("Bye. Hope to see you again soon!");
                return;
            case "list":
                printTaskList(taskList);
                break;
            case "todo":
                String[] s = Arrays.copyOfRange(sentence, 1, sentence.length);
                String description = String.join(" ", s);
                Todo t = new Todo(description);
                taskList.add(t);
                printAddTask(taskList);
                break;
            case "deadline":
                break;
            case "event":
                break;
            case "mark":
                taskList.markTaskList(Integer.parseInt(sentence[1]), true);
                break;
            case "unmark":
                taskList.markTaskList(Integer.parseInt(sentence[1]), false);
                break;
            default:
                printSentence("invalid command");
                break;
                }
            }
        }
    }


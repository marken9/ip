import java.util.Scanner;
import java.util.Arrays;

import task.TaskList;
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
        System.out.println(taskList.printTask(taskList.getTaskCount()));
        System.out.println("Now you have " + Integer.toString(taskList.getTaskCount()) + " tasks in the list.");
        printLine();
    }

    public static int returnIndex(String[] sentence, String s) throws PainterException{
        for (int i = 0; i < sentence.length; i += 1) {
            if (sentence[i].equals(s)) {
                return i;
            }
        }
        throw new PainterException("Could not find " + s);
    }

    public static void printError() {
        printSentence("Invalid command detected, try again");
    }



    public static void printException(PainterException e) {
        System.out.println("Hey! Stop trying to crash my system! Error: " + e.getMessage());
    }

    public static boolean verifyToDo(String[] sentence) throws PainterException{
        if (sentence.length <= 1) {
            throw new PainterException("Todo command missing description");
        }
        return true;
    }

    public static void handleToDo(String[] sentence, TaskList taskList) {
        try {
            if (verifyToDo(sentence)) {
                String[] s = Arrays.copyOfRange(sentence, 1, sentence.length);
                String descriptionToDo = String.join(" ", s);
                Todo t = new Todo(descriptionToDo);
                taskList.add(t);
                printAddTask(taskList);
            }
        } catch (PainterException e) {
            printException(e);
        }

    }

    public static void handleDeadline(String[] sentence, TaskList taskList) {
        try {
        int i = returnIndex(sentence, "/by");
        String[] a = Arrays.copyOfRange(sentence, 1, i);
        String descriptionDeadline = String.join(" ", a);
        String[] b = Arrays.copyOfRange(sentence, i + 1, sentence.length);
        String by = String.join(" ", b);
        Deadline d = new Deadline(descriptionDeadline, by);
        taskList.add(d);
        printAddTask(taskList);
        } catch (PainterException e) {
            printException(e);
        }
    }

    public static void handleEvent(String[] sentence, TaskList taskList) {
        try {
            int i = returnIndex(sentence, "/from");
            int j = returnIndex(sentence, "/to");
            String[] a = Arrays.copyOfRange(sentence, 1, i);
            String descriptionEvent = String.join(" ", a);
            String[] b = Arrays.copyOfRange(sentence, i + 1, j);
            String from = String.join(" ", b);
            String[] c = Arrays.copyOfRange(sentence, j + 1, sentence.length);
            String to = String.join(" ", c);
            Event e = new Event(descriptionEvent, from, to);
            taskList.add(e);
            printAddTask(taskList);
        } catch (PainterException e) {
            printException(e);
        }
    }

    public static void main(String[] args) {
        printSentence("Hello expendable. I'm Painter :D\nPlay with my task list and I'll open the way to the escape submarine");

        TaskList taskList = new TaskList();
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            line = in.nextLine();
            line = line.strip();

            String[] sentence;
            sentence = line.split(" ");

            switch (sentence[0]) {
            case "bye":
            case "exit":
                printSentence("Bye. Hope to see you again soon!");
                return;
            case "list":
                printTaskList(taskList);
                break;
            case "todo":
                handleToDo(sentence, taskList);
                break;
            case "deadline":
                handleDeadline(sentence, taskList);
                break;
            case "event":
                handleEvent(sentence, taskList);
                break;
            case "mark":
                taskList.markTaskList(Integer.parseInt(sentence[1]), true);
                break;
            case "unmark":
                taskList.markTaskList(Integer.parseInt(sentence[1]), false);
                break;
            default:
                printError();
                break;
                }
            }
        }
    }


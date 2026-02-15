package painter;

import java.util.Scanner;
import java.util.Arrays;

import painter.exception.PainterException;
import painter.task.TaskList;
import painter.task.Todo;
import painter.task.Deadline;
import painter.task.Event;

public class Painter {

    public static final String LINESTRING = "____________________________________________________________";

    public static void printLine() {
        System.out.println(LINESTRING);
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
        System.out.println(taskList.getTaskString(taskList.getTaskCount()));
        System.out.println("Now you have " + Integer.toString(taskList.getTaskCount()) + " tasks in the list.");
        printLine();
    }

    public static int returnIndex(String[] sentence, String s) throws PainterException {
        for (int i = 0; i < sentence.length; i += 1) {
            if (sentence[i].equals(s)) {
                return i;
            }
        }
        String command;
        switch (s) {
        case "/by":
            command = "deadline";
            break;
        case "/from":
        case "/to":
            command = "event";
            break;
        default:
            command = "unknown command";
        }
        throw new PainterException("Could not find " + s + " for " + command);
    }

    public static void printError() {
        printSentence("Invalid command detected, try again");
    }


    public static void printException(Exception e) {
        System.out.println("Hey! Stop trying to crash my system! Error: " + e.getMessage());
    }

    public static boolean verifyToDo(String[] sentence) throws PainterException {
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

    public static boolean verifyDeadline(String[] description, String[] by) throws PainterException {
        if (description.length <= 0) {
            throw new PainterException("Deadline command missing description");
        } else if (by.length <= 0) {
            throw new PainterException("Deadline command missing \"/by\" description");
        }
        return true;
    }

    public static void handleDeadline(String[] sentence, TaskList taskList) {
        try {
            int i = returnIndex(sentence, "/by");
            String[] a = Arrays.copyOfRange(sentence, 1, i);
            String[] b = Arrays.copyOfRange(sentence, i + 1, sentence.length);
            if (verifyDeadline(a, b)) {
                String descriptionDeadline = String.join(" ", a);
                String by = String.join(" ", b);
                Deadline d = new Deadline(descriptionDeadline, by);
                taskList.add(d);
                printAddTask(taskList);
            }
        } catch (PainterException e) {
            printException(e);
        }
    }

    public static boolean verifyEvent(String[] description, String[] from, String[] to) throws PainterException {
        if (description.length <= 0) {
            throw new PainterException("Event command missing description");
        } else if (from.length <= 0) {
            throw new PainterException("Event command missing \"/from\" description");
        } else if (to.length <= 0) {
            throw new PainterException("Event command missing \"/to\" description");
        }
        return true;
    }

    public static void verifyEventIndex(int fromIndex, int toIndex) throws PainterException{
        if (toIndex <= fromIndex) {
            throw new PainterException("Event command invalid due to misplaced /from and /to");
        }
    }

    public static void handleEvent(String[] sentence, TaskList taskList) {
        try {
            int i = returnIndex(sentence, "/from");
            int j = returnIndex(sentence, "/to");
            verifyEventIndex(i, j);
            String[] a = Arrays.copyOfRange(sentence, 1, i);
            String[] b = Arrays.copyOfRange(sentence, i + 1, j);
            String[] c = Arrays.copyOfRange(sentence, j + 1, sentence.length);
            if (verifyEvent(a, b, c)) {
                String descriptionEvent = String.join(" ", a);
                String from = String.join(" ", b);
                String to = String.join(" ", c);
                Event e = new Event(descriptionEvent, from, to);
                taskList.add(e);
                printAddTask(taskList);
            }
        } catch (PainterException e) {
            printException(e);
        }
    }

    public static void markTask(String[] sentence, TaskList taskList, boolean isMark) {
        try {
            int taskNumber = Integer.parseInt(sentence[1]);
            taskList.markTaskList(taskNumber, isMark);
        } catch (NumberFormatException e) {
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
                markTask(sentence, taskList, true);
                break;
            case "unmark":
                markTask(sentence, taskList, false);
                break;
            default:
                printError();
                break;
            }
        }
    }
}


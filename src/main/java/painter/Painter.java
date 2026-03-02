package painter;

import java.util.Scanner;
import java.util.Arrays;

import painter.exception.PainterException;
import painter.storage.Storage;
import painter.task.TaskList;
import painter.task.Todo;
import painter.task.Deadline;
import painter.task.Event;
import painter.ui.Ui;

import painter.parser.Parser;

/**
 * The main controller class for the Painter application.
 * Painter coordinates the interaction between the user interface (Ui),
 * storage system (Storage), and task list (TaskList).
 * It processes user input and executes the corresponding commands.
 */
public class Painter {
    private static Ui ui = new Ui();
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Returns the index of a specified string in the input array.
     *
     * @param sentence Split user input.
     * @param s The separator to search for (e.g., "/by", "/from").
     * @return The index of the separator.
     * @throws PainterException If the separator is not found.
     */
    private static int returnIndex(String[] sentence, String s) throws PainterException {
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

    public static boolean verifyToDo(String[] sentence) throws PainterException {
        if (sentence.length <= 1) {
            throw new PainterException("Todo command missing description");
        }
        return true;
    }
    /**
     * Handles the "todo" command by creating and adding a Todo task.
     *
     * @param sentence Split user input.
     * @param taskList The current task list.
     */
    public static void handleToDo(String[] sentence, TaskList taskList) {
        try {
            if (verifyToDo(sentence)) {
                String[] s = Arrays.copyOfRange(sentence, 1, sentence.length);
                String descriptionToDo = String.join(" ", s);
                Todo t = new Todo(descriptionToDo);
                taskList.add(t);
                ui.printTaskAdded(taskList);
            }
        } catch (PainterException e) {
            ui.printException(e);
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
    /**
     * Handles the "deadline" command by creating and adding a Deadline task.
     * Validates the presence of the "/by" separator.
     *
     * @param sentence Split user input.
     * @param taskList The current task list.
     */
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
                ui.printTaskAdded(taskList);
            }
        } catch (PainterException e) {
            ui.printException(e);
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

    public static void verifyEventIndex(int fromIndex, int toIndex) throws PainterException {
        if (toIndex <= fromIndex) {
            throw new PainterException("Event command invalid due to misplaced /from and /to");
        }
    }
    /**
     * Handles the "event" command by creating and adding an Event task.
     * Validates the "/from" and "/to" separators.
     *
     * @param sentence Split user input.
     * @param taskList The current task list.
     */
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
                ui.printTaskAdded(taskList);
            }
        } catch (PainterException e) {
            ui.printException(e);
        }
    }

    /**
     * Handles the "mark" and "unmark" commands.
     *
     * @param sentence Split user input.
     * @param taskList The current task list.
     * @param isMark True to mark as done, false to unmark.
     */
    public static void markTask(String[] sentence, TaskList taskList, boolean isMark) {
        try {
            int taskNumber = Integer.parseInt(sentence[1]);
            taskList.markTaskList(taskNumber, isMark);
        } catch (NumberFormatException e) {
            ui.printException(e);
        }
    }

    /**
     * Handles the "delete" command by removing a task.
     *
     * @param sentence Split user input.
     * @param taskList The current task list.
     */
    public static void handleDelete(String[] sentence, TaskList taskList) {
        try {
            int taskNumber = Integer.parseInt(sentence[1]);
            taskList.deleteTask(taskNumber);
        } catch (NumberFormatException e) {
            ui.printException(e);
        }
    }
    /**
     * Handles the "find" command by searching for tasks
     * containing the specified keyword.
     *
     * @param sentence Split user input.
     * @param taskList The current task list.
     * @throws PainterException If no keyword is provided.
     */
    public static void handleFind(String[] sentence, TaskList taskList) throws PainterException {
        if (sentence.length <= 1) {
            throw new PainterException("no keywords found when using find command");
        }
        String keyword = sentence[1];
        TaskList tempTaskList = new TaskList();
        for (int i = 0; i < taskList.getTaskCount(); i += 1) {
            if (taskList.accessTask(i).getDescription().contains(keyword)) {
                tempTaskList.add(taskList.accessTask(i));
            }
        }
        ui.printMatchingTasks(tempTaskList);
    }



    /**
     * Constructs a Painter object and initializes storage, UI,
     * parser, and loads tasks from the specified file path.
     *
     * @param filePath Path to the file used for saving and loading tasks.
     */
    public Painter(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            taskList = storage.load();
            ui.printMessage("Hello expendable. I'm Painter :D\n"
                    + "Play with my task list and I'll open the way to the escape submarine");
        } catch (Exception e) {
            taskList = new TaskList();
            ui.printException(e);
        }
    }
    /**
     * Starts the main program loop.
     * Continuously reads user input and executes commands
     * until the user exits the program.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String line;
                line = in.nextLine();
                String commandWord = parser.getCommandWord(line);
                String[] sentence = parser.getArgs(line);
                switch (commandWord) {
                case "bye":
                case "exit":
                    ui.printMessage("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    ui.printTaskList(taskList);
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
                case "delete":
                    handleDelete(sentence, taskList);
                    break;
                case "clear":
                    taskList.clear();
                    break;
                case "find":
                    handleFind(sentence, taskList);// only the first keyword will be detected
                    break;
                default:
                    ui.printError("Invalid command detected, try again");
                    break;
                }
                storage.save(taskList);
            } catch (Exception e) {
                ui.printException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Painter("./data/painter.txt").run();
        }
    }



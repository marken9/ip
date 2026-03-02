package painter.storage;

import painter.ui.Ui;

import painter.exception.PainterException;
import painter.task.Deadline;
import painter.task.Event;
import painter.task.TaskList;
import painter.task.Todo;


import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks to and from a file.
 * Storage is responsible for reading task data from disk
 * and writing updated task lists back to disk.
 */
public class Storage {
    private final Ui ui = new Ui();
    private String filePath;
    private TaskList taskList;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        taskList = new TaskList();
    }


    private void markImportTask(String s) {
        if (s.equals("X")) {
            taskList.accessTask(taskList.getTaskCount() - 1).markAsDone();
        }
    }

    private boolean checkSeparator(String line, int expectedSemicolons) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ';') {
                count++;
            }
        }
        return count == expectedSemicolons;
    }

    private void importToDo(String[] sentence) {
        taskList.add(new Todo(sentence[2]));

        markImportTask(sentence[1]);
    }

    private void importDeadline(String[] sentence) {
        taskList.add(new Deadline(sentence[2], sentence[3]));

        markImportTask(sentence[1]);
    }

    private void importEvent(String[] sentence) {
        taskList.add(new Event(sentence[2], sentence[3], sentence[4]));

        markImportTask(sentence[1]);
    }

    /**
     * Parses a single line from the storage file and reconstructs
     * the corresponding Task object.
     * The method first validates that the line contains the correct
     * number of separators (';') based on the task type.
     * It then delegates task creation to the appropriate import method.
     *
     * @param s A single line from the data file representing a task.
     * @throws PainterException If the task type is unknown or
     *                          if the line format is invalid.
     */
    private void importTask(String s) throws PainterException {
        String[] sentence = s.split(";");
        int expected;
        switch (sentence[0]) {
        case "T":
            expected = 2;
            break;
        case "D":
            expected = 3;
            break;
        case "E":
            expected = 4;
            break;
        default:
            throw new PainterException("Unknown task found in data when importing");
        }

        if (!checkSeparator(s, expected)) {
            throw new PainterException("Corrupted task line (wrong number of ';'): " + s);
        }

            switch (sentence[0]) {
            case "T":
                importToDo(sentence);
                break;
            case "D":
                importDeadline(sentence);
                break;
            case "E":
                importEvent(sentence);
                break;
            default:
                throw new PainterException("Unknown task found in data when importing");
            }
        }

    /**
     * Loads tasks from the storage file into a TaskList.
     *
     * @return The TaskList containing loaded tasks.
     * @throws NullPointerException If the filePath is null.
     */
    public TaskList load() throws NullPointerException {
        File f = new File(filePath);
        if (!f.exists()) {
            // If no file yet, start with empty list
            ui.printError("Loading failed due to missing file path. Starting with a new empty task list.");
            return taskList;
        }
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                importTask(line); // add without saving
            }
        } catch (Exception e) {
            ui.printException(e);
        }
        return taskList;
    }

    /**
     * Saves the given TaskList to the storage file.
     *
     * @param taskList The TaskList to save.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(taskList.toFileString());
            fw.close();
        } catch (Exception e) {
            ui.printException(e);
        }
    }
}
package painter.storage;

import painter.ui.Ui;

import painter.exception.PainterException;
import painter.task.Deadline;
import painter.task.Event;
import painter.task.Task;
import painter.task.TaskList;
import painter.task.Todo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    private void importTask(String s) throws PainterException {

            String[] sentence = s.split(";");
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
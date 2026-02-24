package painter.task;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import painter.ui.Ui;

public class TaskList {

    protected ArrayList<Task> taskList;
    protected int taskCount;

    private final Ui ui = new Ui();

    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task accessTask(int n) {
        return taskList.get(n);
    }

    public String getTaskString(int taskNumber) {
        return taskList.get(taskNumber - 1).toString();
    }

    public void add(Task t) {
        taskList.add(t);
        taskCount += 1;

    }

    public void markTaskList(int n, boolean isDone) {
        if (n > taskCount) {
            ui.printMessage("You only have " + taskCount + " tasks but you entered " + n + ".");
            return;
        }

        if (n <= 0) {
            ui.printMessage("Task index cannot be 0 or negative");
            return;
        }

        if (isDone) {
            taskList.get(n - 1).markAsDone();
            ui.printTaskMarked(this.getTaskString(n), true);
        } else {
            taskList.get(n - 1).markAsUndone();
            ui.printTaskMarked(this.getTaskString(n), true);
        }
    }

   public void clear() {
        taskList.clear();
        taskCount = 0;
        ui.printMessage("Task list and file cleared.");
    }


    public String toFileString() {
        String result = "";
        for (int i = 0; i < taskCount; i += 1) {
            result = result + (taskList.get(i).toFileString()) + System.lineSeparator();
        } // To explore StringBuilder class if free
        return result;
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber > taskCount) {
            ui.printMessage("You only have " + taskCount + " tasks but you entered " + taskNumber + ".");
            return;
        }
        if (taskNumber <= 0) {
            ui.printMessage("Task index cannot be 0 or negative");
            return;
        }
        String removeTask = taskList.get(taskNumber - 1).toString();
        taskList.remove(taskNumber - 1);
        taskCount--;
        ui.printTaskDeleted(removeTask, taskCount);

    }

    public String toString() {
        String result = "";
        for (int i = 0; i < taskCount; i += 1) {
            result = result + Integer.toString( i + 1 ) + ". " + (taskList.get(i).toString()) + System.lineSeparator();
        } // To explore StringBuilder class if free
        return result;
    }

}

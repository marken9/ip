package painter.task;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TaskList {
    public static final String LINESTRING = "____________________________________________________________";

    protected ArrayList<Task> taskList;
    protected int taskCount;

    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public String getTaskString(int taskNumber) {
        return taskList.get(taskNumber - 1).toString();
    }

    public static void printLine() {
        System.out.println(LINESTRING);
    }

    public static void printSentence(String x) {
        printLine();
        System.out.println(x);
        printLine();
    }
    public void add(Task t) {
        taskList.add(t);
        taskCount += 1;
        saveToFile();
    }

    public void markTaskList(int n, boolean isDone) {
        if (n > taskCount) {
            printSentence("You only have " + taskCount + " tasks but you entered " + n + ".");
            return;
        }

        if (n <= 0) {
            printSentence("Task index cannot be 0 or negative");
            return;
        }

        if (isDone) {
            taskList.get(n - 1).markAsDone();
            printSentence("Nice! I've marked this task as done: " + taskList.get(n - 1));
        } else {
            taskList.get(n - 1).markAsUndone();
            printSentence("OK, I've marked this task as not done yet: " + taskList.get(n - 1));
        }
        saveToFile();
    }

    private void printFileContents() throws FileNotFoundException {
        File f = new File("./data/painter.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public void importToPainter() {
        try {
            printFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // false to overwrite, true to append
        fw.write(textToAdd);
        fw.close();
    }

    public void saveToFile() {
        String filePath = "./data/painter.txt";
        try {
            writeToFile(filePath, this.toFileString()); // !!! use this.toString() instead of taskList.toString() funny mistake
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public String toFileString() {
        String result = "";
        for (int i = 0; i < taskCount; i += 1) {
            result = result + (taskList.get(i).toFileString()) + System.lineSeparator();
        } // To explore StringBuilder class if free
        return result;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < taskCount; i += 1) {
            result = result + Integer.toString( i + 1 ) + ". " + (taskList.get(i).toString()) + System.lineSeparator();
        } // To explore StringBuilder class if free
        return result;
    }

}

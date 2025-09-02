package waz.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import waz.exception.WazException;
import waz.task.Deadline;
import waz.task.Event;
import waz.task.Task;
import waz.task.TaskList;
import waz.task.Todo;

/**
 * The {@code Storage} class handles reading and writing the task data to a persistent file.
 */
public class Storage {
    private String fileName;

    /**
     * Constructs a Storage instance with the given filename
     * @param fileName the name of the file to read/write tasks
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Read the content of the file line by line and convert each line into a {@link Task} object
     * Corrupted lines will be skipped with a warning printed.
     * If file does not exist, an empty task list is returned.
     *
     * @return a {@link TaskList} containing all valid tasks read from the file
     */
    public TaskList readContent() {
        TaskList taskList = new TaskList();
        File file = new File(fileName);

        // if file is empty return empty list for now
        if (!file.exists()) {
            System.out.println("File does not exist");
            return taskList;
        }

        // Read the file line by line
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                try {
                    Task task = lineToTask(line); // Convert each line into a waz.task.Task object
                    taskList.addTask(task);
                } catch (WazException e) { // Corrupted lines
                    System.out.println((e.getMessage()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println((e.getMessage()));
        }

        System.out.println("Read file content successfully");
        return taskList;
    }

    /**
     * Converts a line from the file into a waz.task.Task Object
     * <p>
     *   <ul>
     *       <li>T | 1 | read book</li>
     *       <li>D | 0 | return book | June 6th</li>
     *       <li>T | 1 | join sports clubs</li>
     *   </ul>
     * </p>
     *
     * For {@link Event} tasks, if the "/from" field does not contain "am" or "pm", it will be automatically appended.
     *
     * @param line a single line from the file
     * @return the corresponding w{@link Task} object (Todo, Deadline, or Event)
     * @throws WazException if the line is corrupted or any unknown task type
     */
    private Task lineToTask(String line) throws WazException {
        String[] parts = line.split("\\| ");

        if (parts.length < 3) {
            throw new WazException("Line is corrupted. Failed to create task.");
        }

        String taskType = parts[0].trim();
        boolean isMarked = parts[1].trim().equals("1");

        switch (taskType) {
        case "T":
            Todo todoTask = new Todo(parts[2]);
            if (isMarked) {
                todoTask.markAsDone();
            }

            return todoTask;
        case "D":
            if (parts.length < 4) {
                throw new WazException("Line is corrupted. Failed to create deadline task.");
            }

            Deadline deadlineTask = new Deadline(parts[2], parts[3]);
            if (isMarked) {
                deadlineTask.markAsDone();
            }

            return deadlineTask;
        case "E":
            if (parts.length < 4) {
                throw new WazException("Line is corrupted. Failed to create event task.");
            }

            String[] eventInfo = parts[3].split("-", 2);

            if (eventInfo.length < 2) {
                throw new WazException("Line is corrupted. Failed to create event task expected from-to.");
            }

            // Add "am" or "pm" to /from
            if (eventInfo[1].contains("am")) {
                eventInfo[0] += "am";
            } else {
                eventInfo[0] += "pm";
            }

            Event eventTask = new Event(parts[2], eventInfo[0], eventInfo[1]);

            if (isMarked) {
                eventTask.markAsDone();
            }

            return eventTask;
        default:
            throw new WazException("Line is corrupted. Unknown task type.");
        }

    }

    /**
     * Save the current list of tasks to a file
     *
     * Each task in the list is converted to a string representation using {@link Task#toDataString()}
     * The file is overwritten everytime the task list is edited to ensure the state of the state of the task list.
     *
     * Example of file content:
     * <p>
     *     <ul>
     *         <li>T | 1 | read book</li>
     *         <li>D | 0 | return book | June 6th</li>
     *         <li>E | 0 | project meeting | Aug 6th 2-4pm</li>
     *     </ul>
     * </p>
     *
     * @param taskList the list of tasks to save
     */
    public void saveContent(ArrayList<Task> taskList) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Task task: taskList) {
                // Convert each task into string/line and add to the next line
                fw.write(task.toDataString() + System.lineSeparator());
            }
            System.out.println("waz.task.Task saved successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

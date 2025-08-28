import jdk.management.jfr.EventTypeInfo;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private static final String fileName = "waz.txt";

    /**
     * Read the content of the file line by line and convert each line into a Task object
     * Corrupted lines will be skipped with a warning printed.
     * If file does not exist, an empty task list is returned.
     *
     * @return an arraylist of Task objects read from the file waz.txt
     */
    public static ArrayList<Task> readContent() {
        ArrayList<Task> taskList = new ArrayList<>();
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
                    Task task = convertTask(line); // Convert each line into a Task object
                    taskList.add(task);
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
     * Converts a line from the file into a Task Object
     * <p>
     *   <ul>
     *       <li>T | 1 | read book</li>
     *       <li>D | 0 | return book | June 6th</li>
     *       <li>T | 1 | join sports clubs</li>
     *   </ul>
     * </p>
     *
     * For Event tasks, if the "/from" field does not contain "am" or "pm", it will be automatically appended.
     *
     * @param line each string line from the file represents a Task
     * @return the corresponding Task object (Todo, Deadline, Event)
     * @throws WazException if the line is corrupted or any unknown task type
     */
    private static Task convertTask(String line) throws WazException{
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

            Event eventTask = new Event(parts[2],eventInfo[0], eventInfo[1]);

            if (isMarked) {
                eventTask.markAsDone();
            }

            return eventTask;
        default:
            throw new WazException("Line is corrupted. Unknown task type.");
        }

    }
}

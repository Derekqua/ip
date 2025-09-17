package waz.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import waz.exception.WazException;
import waz.tag.Tag;
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
        TaskList tasks = new TaskList();
        File file = new File(fileName);

        // if file is empty return empty list for now
        if (!file.exists()) {
            System.out.println("File does not exist");
            return tasks;
        }

        // Read the file line by line
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                try {
                    Task task = convertLineToTask(line); // Convert each line into a waz.task.Task object
                    tasks.addTask(task);
                } catch (WazException e) { // Corrupted lines
                    System.out.println((e.getMessage()));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println((e.getMessage()));
        }

        System.out.println("Read file content successfully");
        return tasks;
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
    private Task convertLineToTask(String line) throws WazException {
        String[] commandParts = line.split(" \\|");
        List<Tag> tags;
        String tagString;
        Task task;

        if (commandParts.length < 3) {
            throw new WazException("Line is corrupted. Failed to create task.");
        }

        String taskType = commandParts[0].trim();
        String description = commandParts[2].trim();
        boolean isMarked = commandParts[1].trim().equals("1");

        switch (taskType) {
        case "T":
            // Handle Tags
            tagString = commandParts.length > 3 ? commandParts[3] : "";
            tags = convertToTags(tagString);
            task = createTodo(description, tags, isMarked);
            break;
        case "D":
            if (commandParts.length < 4) {
                throw new WazException("Line is corrupted. Failed to create deadline task.");
            }

            String deadline = commandParts[3].trim();

            // Handle Tags
            tagString = commandParts.length > 4 ? commandParts[4] : "";
            tags = convertToTags(tagString);
            task = createDeadline(description, deadline, tags, isMarked);
            break;
        case "E":
            if (commandParts.length < 4) {
                throw new WazException("Line is corrupted. Failed to create event task.");
            }

            String[] time = commandParts[3].split("~", 2);

            if (time.length < 2) {
                throw new WazException("Line is corrupted. Failed to create event task expected from-to.");
            }

            String startTime = time[0].trim();
            String endTime = time[1].trim();

            // Handle Tags
            tagString = commandParts.length > 4 ? commandParts[4] : "";
            tags = convertToTags(tagString);
            task = createEvent(description, startTime, endTime, tags, isMarked);
            break;
        default:
            throw new WazException("Line is corrupted. Unknown task type.");
        }

        return task;
    }

    /**
     * Creates a Todo task with optional tags and completion status.
     *
     * @param description the task description
     * @param tags the list of tags associated with this task
     * @param isMarked true if the task should be marked as done
     * @return a Todo task
     */
    private Todo createTodo(String description, List<Tag> tags, boolean isMarked) {
        // Create Todo
        Todo todoTask = new Todo(description);
        todoTask.addTags(tags);

        if (isMarked) {
            todoTask.markAsDone();
        }
        return todoTask;
    }

    /**
     * Creates a Deadline task with optional tags and completion status.
     *
     * @param description the task description
     * @param deadline the deadline string in an accepted datetime format
     * @param tags the list of tags associated with this task
     * @param isMarked true if the task should be marked as done
     * @return a Deadline task
     * @throws WazException if the deadline string is invalid or cannot be parsed
     */
    private Deadline createDeadline(String description, String deadline, List<Tag> tags, boolean isMarked)
            throws WazException {
        // Create Deadline
        Deadline deadlineTask = new Deadline(description, deadline);
        deadlineTask.addTags(tags);

        if (isMarked) {
            deadlineTask.markAsDone();
        }
        return deadlineTask;
    }

    /**
     * Creates an Event task with start and end times, optional tags, and completion status.
     *
     * <p>
     * Validates that both startTime and endTime are provided and parses them into
     * the correct datetime format if necessary.
     * </p>
     *
     * @param description the task description
     * @param startTime the start time string (e.g., "/from" field)
     * @param endTime the end time string (e.g., "/to" field)
     * @param tags the list of tags associated with this task
     * @param isMarked true if the task should be marked as done
     * @return an Event task
     * @throws WazException if startTime or endTime is missing, invalid, or endTime is before startTime
     */
    private Event createEvent(String description, String startTime, String endTime, List<Tag> tags, boolean isMarked)
            throws WazException {
        // Create Event
        Event eventTask = new Event(description, startTime, endTime);
        eventTask.addTags(tags);

        if (isMarked) {
            eventTask.markAsDone();
        }
        return eventTask;
    }


    /**
     * Converts a string of space-separated tag names into a list of {@link Tag} objects.
     * <p>
     * Each tag in the input string is trimmed and normalized in the {@link Tag} constructor.
     * If the input string is empty or contains only whitespace, an empty list is returned.
     * </p>
     *
     * @param tagString a string containing space-separated tag names (e.g., "#fun #urgent")
     * @return a {@link List} of {@link Tag} objects corresponding to the input string
     */
    public List<Tag> convertToTags(String tagString) {
        List<Tag> tags = new ArrayList<>();
        boolean isTagsEmpty = tagString.trim().isEmpty();
        if (!isTagsEmpty) {
            tags = Arrays.stream(tagString.split("#"))
                    .skip(1)
                    .map(Tag::new) // convert each string to a Tag object
                    .toList();
        }
        return tags;
    }

    /**
     * Save the current list of tasks to a file
     * <p>
     * Each task in the list is converted to a string representation using {@link Task#toDataString()}
     * The file is overwritten everytime the task list is edited to ensure the state of the state of the task list.
     * <p>
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

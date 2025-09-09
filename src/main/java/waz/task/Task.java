package waz.task;

/**
 * Represents a generic task
 * <p>
 * The {@code Task} class serves as the base class for all types of tasks,
 * such as {@link Todo}, {@link Event}, and {@link Deadline}. It stores
 * a description and completion status.
 * </p>
 *
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description
     * The task is defaulted to not done. (isDone = false)
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon which represent whether the task is done
     * @return "X" if done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Format task into String to be saved in the file
     * @return a formatted string for storage
     */
    public abstract String toDataString();

    /**
     * Returns a string representation of this task to be displayed to the user
     * @return a string in the format "[ ] description" or "[X] description"
     */
    @Override
    public String toString() {
        String formattedString = "[" + getStatusIcon() + "] " + description;
        return formattedString;
    }
}

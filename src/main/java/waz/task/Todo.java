package waz.task;

/**
 * <p>
 * A Todo is a type of {@link Task} that only has a description and
 * a completion status. It does not have a specific date or time.
 * </p>
 *
 */
public class Todo extends Task {

    /**
     * Constructs a new too with the given description
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Format ToDo task into String to be saved in the file
     * @return a formatted string representing this Todo
     */
    @Override
    public String toDataString() {
        String formattedDataString = "T | " + (isDone ? "1" : "0") + " | " + description;
        return formattedDataString;
    }

    /**
     * Returns a string representation of this Todo to be displayed to the user
     * @return a string in the format "[T][ ] description"
     */
    @Override
    public String toString() {
        String formattedString = "[T]" + super.toString();
        return formattedString;
    }
}

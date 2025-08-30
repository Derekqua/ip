package waz.task;

/**
 * Represents an Event task
 * <p>
 *     An Event is a type of {@link Task} that has a description as well as a start time /from and an end time /to.
 * </p>
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event with the given description, from, to
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Format Event task into String to be saved in the file
     * @return a formatted string representing this Event
     */
    @Override
    public String toDataString() {
        if (from.contains("pm") || from.contains("am")) {
            from = from.substring(0, from.length() - 2);
        }
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + "-" + to;
    }

    /**
     * Returns a string representation of this Event to be displayed to the user
     * @return a string in the format "[E][ ] description (from: from to: to)"
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

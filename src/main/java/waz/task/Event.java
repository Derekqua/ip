package waz.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Format the waz.task.Task object into String to be saved in the file
     * @return a formatted waz.task.Task string
     */
    @Override
    public String toDataString() {
        if (from.contains("pm") || from.contains("am")) {
            from = from.substring(0, from.length() - 2);
        }
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + "-" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

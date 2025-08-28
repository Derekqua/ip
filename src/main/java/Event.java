public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Format the Task object into String to be saved in the file
     * @return a formatted Task string
     */
    @Override
    public String toDataString() {

        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.substring(0, from.length() - 2) +
                "-" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

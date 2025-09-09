package waz.task;

/**
 * Represents an Event task
 * <p>
 *     An Event is a type of {@link Task} that has a description as well as a start time /from and an end time /to.
 * </p>
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Constructs a new Event with the given description, startTime, endTime
     * @param description
     * @param startTime
     * @param endTime
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Format Event task into String to be saved in the file
     * @return a formatted string representing this Event
     */
    @Override
    public String toDataString() {
        String formattedStartTime = startTime.toLowerCase();
        boolean isAmOrPm = formattedStartTime.contains("pm") || formattedStartTime.contains("am");

        if (isAmOrPm) { // remove am or pm from the start time
            formattedStartTime = formattedStartTime.substring(0, formattedStartTime.length() - 2);
        }

        String formattedDataString = "E | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedStartTime
                + "-" + endTime;
        return formattedDataString;
    }

    /**
     * Returns a string representation of this Event to be displayed to the user
     * @return a string in the format "[E][ ] description (from: from to: to)"
     */
    @Override
    public String toString() {
        String formattedString = "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
        return formattedString;
    }
}

package waz.task;

import java.time.LocalDateTime;

import waz.exception.WazException;
import waz.parser.DateTimeUtil;

/**
 * Represents an Event task
 * <p>
 *     An Event is a type of {@link Task} that has a description as well as a start time /from and an end time /to.
 * </p>
 */
public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs a new Event with the given description, startTime, endTime
     * @param description the description of the event
     * @param startTime the start time as a string
     * @param endTime the end time as a string
     * @throws WazException if the datetime format is invalid or endTime is before startTime
     */
    public Event(String description, String startTime, String endTime) throws WazException {
        super(description);

        LocalDateTime start = DateTimeUtil.parse(startTime);
        LocalDateTime end = DateTimeUtil.parse(endTime);

        boolean isInvalidTimeFormat = (start == null || end == null);

        if (isInvalidTimeFormat) { // Invalid date/time format
            throw new WazException("Invalid date/time format. Please try again. \n"
                    + "Below is the accepted format: \n 2019-10-15 1800 \n 2019-10-15 18:00 \n 15/10/2019 1800 \n "
                    + "15/10/2019 18:00 \n Oct 15 2019 18:00");
        }

        // Ensure end time is after start time
        if (!end.isAfter(start)) {
            throw new WazException("End time must be later than start time.");
        }

        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Format Event task into String to be saved in the file
     * @return a formatted string representing this Event
     */
    @Override
    public String toDataString() {
        String formattedStartTime = DateTimeUtil.formatForData(startTime);
        String formattedEndTime = DateTimeUtil.formatForData(endTime);

        String formattedDataString = "E | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedStartTime
                + "~" + formattedEndTime + " | " + getTagsString();
        return formattedDataString;
    }

    /**
     * Returns a string representation of this Event to be displayed to the user
     * @return a string in the format "[E][ ] description (from: from to: to)"
     */
    @Override
    public String toString() {
        String formattedStartTime = DateTimeUtil.formatForDisplay(startTime);
        String formattedEndTime = DateTimeUtil.formatForDisplay(endTime);
        String formattedString = "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime
                + ") " + getTagsString();
        return formattedString;
    }
}

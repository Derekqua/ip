package waz.task;

import java.time.LocalDateTime;

import waz.exception.WazException;
import waz.parser.DateTimeUtil;

/**
 * Represents a Deadline task
 * <p>
 *     A Deadline is a type of {@link Task} that has a description and specific date and tie by which the task should
 *     be completed. Multiple datetime formats are accepted when creating a Deadline.
 * </p>
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline with the given description and deadline string
     * @param description the description of the task
     * @param deadlineString the deadline as string
     * @throws WazException if the datetime format is invalid/not supported
     */
    public Deadline(String description, String deadlineString) throws WazException {
        super(description);
        LocalDateTime time = DateTimeUtil.parse(deadlineString);
        boolean isInvalidTimeFormat = time == null;

        if (isInvalidTimeFormat) { // Invalid date/time format
            throw new WazException("Invalid date/time format. Please try again. \n"
                    + "Below is the accepted format: \n 2019-10-15 1800 \n 2019-10-15 18:00 \n 15/10/2019 1800 \n "
                    + "15/10/2019 18:00 \n Oct 15 2019 18:00");
        }
        this.deadline = time;
    }

    /**
     * Format Deadline task into String to be saved in the file
     * @return a formatted string representing this Deadline
     */
    @Override
    public String toDataString() {
        String formattedDateTime = DateTimeUtil.formatForData(deadline);
        String formattedDataString = "D | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedDateTime
                + " | " + getTagsString();
        return formattedDataString;
    }

    /**
     * Returns a string representation of this Deadline to be displayed to the user
     * @return a string in the format "[D][ ] description (by: MMM dd yyyy HH:mm)"
     */
    @Override
    public String toString() {
        String formattedDateTime = DateTimeUtil.formatForDisplay(deadline);
        String formattedString = "[D]" + super.toString() + " (by: " + formattedDateTime + ") " + getTagsString();
        return formattedString;
    }
}

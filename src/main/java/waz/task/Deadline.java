package waz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import waz.exception.WazException;

/**
 * Represents a Deadline task
 * <p>
 *     A Deadline is a type of {@link Task} that has a description and specific date and tie by which the task should
 *     be completed. Multiple datetime formats are accepted when creating a Deadline.
 * </p>
 */
public class Deadline extends Task {
    /**
     * Supported datetime formats for parsing input
     */
    private static final DateTimeFormatter[] TIME_FORMATS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // e.g. 2019-10-15 1800
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), // e.g. 2019-10-15 18:00
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"), // e.g. 15/10/2019 1800
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"), // e.g. 15/10/2019 18:00
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm") // e.g. Oct 15 2019 18:00
    };

    private LocalDateTime by;

    /**
     * Constructs a new Deadline with the given description and deadline string
     * @param description the description of the task
     * @param byString the deadline as string
     * @throws WazException if the datetime format is invalid/not supported
     */
    public Deadline(String description, String byString) throws WazException {
        super(description);

        LocalDateTime time = parseDateTime(byString);
        if (time == null) { // Invalid date/time format
            throw new WazException("Invalid date/time format. Please try again. \n"
                    + "Below is the accepted format: \n 2019-10-15 1800 \n 2019-10-15 18:00 \n 15/10/2019 1800 \n "
                    + "15/10/2019 18:00 \n Oct 15 2019 18:00");
        }
        this.by = time;
    }

    /**
     * Parse dateTime string into LocalDateTime
     * <p>
     *     The method iterates through a predefined list of DateTimeFormatter patterns. If patterns succeeds with one
     *     formatter. DateTime is returned. Else parsing fails, the method returns null.
     * </p>
     * <p><b>Example accepted formats:</b></p>
     *  <ul>
     *    <li>"yyyy-MM-dd HHmm" (e.g., "2025-08-30 1830")</li>
     *    <li>"yyyy/MM/dd HH:mm" (e.g., "2025/08/30 18:30")</li>
     *    <li>"dd-MM-yyyy HH:mm" (e.g., "30-08-2025 18:30")</li>
     *    <li>"dd/MM/yyyy HH:mm" (e.g., "15/10/2019 18:00")</li>
     *    <li>"MMM dd yyyy HH:mm" (e.g., "Oct 15 2019 18:00")</li>
     *  </ul>
     *
     * @param byString the dateTime string to  be parsed
     * @return LocalDateTime if sucessful, else null if format is not supported
     */
    private LocalDateTime parseDateTime(String byString) {
        for (DateTimeFormatter formatter: TIME_FORMATS) {
            try {
                return LocalDateTime.parse(byString, formatter);
            } catch (DateTimeParseException ignore) {
                // Try next format
            }
        }
        return null; // If none matched
    }

    /**
     * Format Deadline task into String to be saved in the file
     * @return a formatted string representing this Deadline
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of this Deadline to be displayed to the user
     * @return a string in the format "[D][ ] description (by: MMM dd yyyy HH:mm)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}

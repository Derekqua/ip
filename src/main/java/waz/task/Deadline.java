package waz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, String byString) {
        super(description);
        try {
            this.by = LocalDateTime.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date/time format. Please use 'yyyy-MM-dd HHmm' (e.g., 2019-10-15 1800).");
        }
    }

    /**
     * Format the waz.task.Task object into String to be saved in the file
     * @return a formatted waz.task.Task string
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}

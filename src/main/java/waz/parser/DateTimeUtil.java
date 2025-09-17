package waz.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Utility class for parsing and formatting datetime strings.
 */
public class DateTimeUtil {

    private static final DateTimeFormatter[] TIME_FORMATS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH)
    };

    private static final DateTimeFormatter DATA_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm",
            Locale.ENGLISH);

    /**
     * Attempts to parse a datetime string using the supported formats.
     * <p><b>Example accepted formats:</b></p>
     *  <ul>
     *    <li>"yyyy-MM-dd HHmm" (e.g., "2025-08-30 1830")</li>
     *    <li>"yyyy/MM/dd HH:mm" (e.g., "2025/08/30 18:30")</li>
     *    <li>"dd-MM-yyyy HH:mm" (e.g., "30-08-2025 18:30")</li>
     *    <li>"dd/MM/yyyy HH:mm" (e.g., "15/10/2019 18:00")</li>
     *    <li>"MMM dd yyyy HH:mm" (e.g., "Oct 15 2019 18:00")</li>
     *  </ul>
     *
     * @param dateTimeString the datetime string to parse
     * @return LocalDateTime if parsing succeeds, else null
     */
    public static LocalDateTime parse(String dateTimeString) {
        String trimmed = dateTimeString.trim();
        for (DateTimeFormatter formatter : TIME_FORMATS) {
            try {
                LocalDateTime time = LocalDateTime.parse(trimmed, formatter);
                return time;
            } catch (DateTimeParseException ignore) {
                // try next format
            }
        }
        return null;
    }

    /**
     * Formats a LocalDateTime for saving in data files.
     */
    public static String formatForData(LocalDateTime dateTime) {
        return dateTime.format(DATA_FORMAT);
    }

    /**
     * Formats a LocalDateTime for displaying to the user.
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMAT);
    }
}

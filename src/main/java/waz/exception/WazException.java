package waz.exception;

/**
 * Represents exception specific to Waz
 *
 * <p>
 *     This class is used to signal errors that occur during command parsing, task creation, file handling, or any
 *     other waz specific operations.
 * </p>
 */
public class WazException extends Exception {
    /**
     * Constructs a new {@code WazException} with the specified detailed message
     * @param message the detail message explaining the reason for the exception
     */
    public WazException(String message) {
        super(message);
    }

}

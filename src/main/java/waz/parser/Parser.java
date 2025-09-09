package waz.parser;


import waz.command.AddDeadlineCommand;
import waz.command.AddEventCommand;
import waz.command.AddTodoCommand;
import waz.command.Command;
import waz.command.DeleteCommand;
import waz.command.ExitCommand;
import waz.command.FindCommand;
import waz.command.ListCommand;
import waz.command.MarkCommand;
import waz.command.UnmarkCommand;
import waz.exception.WazException;

/**
 * The {@code Parse} class is responsible for converting raw user input string into the corresponding {@code Command}
 * objects that can be executed.
 * <p>Example usage:</p>
 * <pre>
 * Command cmd = Parser.parse("todo read book");
 * cmd.execute(taskList, ui, storage);
 * </pre>
 */
public class Parser {

    /**
     * Parses the user's input string and returns the {@code Command} object
     *
     * <p>Supported commands:</p>
     * <ul>
     *     <li>bye → {@code ExitCommand}</li>
     *     <li>list → {@code ListCommand}</li>
     *     <li>unmark → {@code UnmarkCommand}</li>
     *     <li>mark → {@code MarkCommand}</li>
     *     <li>delete → {@code DeleteCommand}</li>
     *     <li>todo → {@code AddTodoCommand}</li>
     *     <li>deadline → {@code AddDeadlineCommand}</li>
     *     <li>event → {@code AddEventCommand}</li>
     * </ul>
     *
     * @param input the raw string entered by the user
     * @return the appropriate {@code Command} object based on the user input
     * @throws WazException if the input does not match any valid command
     */
    public static Command parse(String input) throws WazException {
        String[] commandParts = input.split(" ", 2);
        String command = commandParts[0];
        String commandInput = (commandParts.length > 1) ? commandParts[1] : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "unmark":
            return new UnmarkCommand(commandInput);
        case "mark":
            return new MarkCommand(commandInput);
        case "delete":
            return new DeleteCommand(commandInput);
        case "todo":
            return new AddTodoCommand(commandInput);
        case "deadline":
            return new AddDeadlineCommand(commandInput);
        case "event":
            return new AddEventCommand(commandInput);
        case "find":
            return new FindCommand(commandInput);
        default:
            throw new WazException("Invalid Command. Please try again!");
        }
    }
}

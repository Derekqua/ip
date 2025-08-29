package waz.parser;

import waz.command.*;
import waz.exception.WazException;

public class Parser {

    /**
     * Parses the user's input string and returns the waz.command.Command object
     * @param input the raw string entered by the user
     * @return a waz.command.Command object based on the user input
     * @throws WazException if the input does not match any valid command
     */
    public static Command parse(String input) throws WazException {
        String[] split = input.split(" ", 2);
        String command = split[0];
        String argument = (split.length > 1) ? split[1] : "";

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "unmark":
                return new UnmarkCommand(argument);
            case "mark":
                return new MarkCommand(argument);
            case "delete":
                return new DeleteCommand(argument);
            case "todo":
                return new AddTodoCommand(argument);
            case "deadline":
                return new AddDeadlineCommand(argument);
            case "event":
                return new AddEventCommand(argument);
            default:
                throw new WazException("Invalid waz.command.Command");
        }
    }
}

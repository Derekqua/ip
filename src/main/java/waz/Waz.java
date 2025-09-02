package waz;

import waz.command.Command;
import waz.exception.WazException;
import waz.parser.Parser;
import waz.storage.Storage;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * The {@code Waz} class represent the main chatbot application that manages tasks.
 * <p>
 * Waz allows the user to add todos, events, and deadlines, mark tasks as done,
 * unmark tasks, delete tasks, search for tasks, and exit the program by typing "bye".
 * It handles user input, parses commands, executes them, and interacts with storage and UI.
 * </p>
 */
public class Waz {
    private TaskList storeList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new chatbot
     * Initialises the Ui, storage, and loads task from the file
     */
    public Waz() {
        ui = new Ui();
        storage = new Storage("waz.txt");
        storeList = storage.readContent();
    }
    /**
     * Generates a response for the user's chat message.
     *
     * @return the formatted string
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(storeList, ui, storage);
        } catch (WazException e) {
            return ui.showErrorMsg(e);
        }
    }
}

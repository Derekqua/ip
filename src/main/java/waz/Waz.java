package waz;

import java.util.Scanner;

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
     * Starts teh chatbot and enters the main loop to process user commands
     * <p>
     *     The loop continues until the user issues an exits command.
     *     All input is parsed and executed, any wazException is caught will be displayed to the user.
     * </p>
     */
    public void run() {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        // Greet User
        ui.showGreetMessage();

        // Loop
        while (!isExit) {
            try {
                String input = scanner.nextLine();
                Command command = Parser.parse(input);
                command.execute(storeList, ui, storage);

                isExit = command.isExit(); // breaks loop if waz.command.ExitCommand else false
            } catch (WazException e) {
                ui.showErrorMsg(e);
            }
        }

    }
    public static void main(String[] args) {
        new Waz().run();
    }
}

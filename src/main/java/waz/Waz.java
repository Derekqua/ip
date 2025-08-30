package waz;

import waz.ui.Ui;
import waz.parser.Parser;
import waz.storage.Storage;
import waz.task.TaskList;
import waz.command.Command;
import waz.exception.WazException;

import java.util.Scanner;

public class Waz {
    private TaskList storeList;
    private Ui ui;
    private Storage storage;

    public Waz() {
        ui = new Ui();
        storage = new Storage("waz.txt");
        storeList = storage.readContent();
    }

    public void run() {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        // Greet User
        ui.greet();

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

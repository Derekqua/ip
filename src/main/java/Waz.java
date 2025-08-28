import java.util.ArrayList;
import java.util.Scanner;

public class Waz {
    private ArrayList<Task> storeList;
    private Ui ui;
    private Storage storage;

    public Waz() {
        ui = new Ui();
        storage = new Storage();
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

                isExit = command.isExit(); // breaks loop if ExitCommand else false
            } catch (WazException e) {
                ui.showErrorMsg(e);
            }
        }

    }
    public static void main(String[] args) {
        new Waz().run();
    }
}

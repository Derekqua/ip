import java.util.ArrayList;
import java.util.Scanner;

public class Waz {
    private static ArrayList<String> storeList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet User
        greet();

        // Loop
        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    exit();
                    return; // exits program
                case "list":
                    displayStoreList();
                    break;
                default:
                    echo(input); // add task to store list
            }
        }
    }

    /* Level 0 */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }
    private static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Waz");
        System.out.println("What can I do for you?");
        horizontalLine();
    }
    private static void horizontalLine() {
        System.out.println("----------------------------------------------");
    }

    /* Level 1 */
    private static void echo(String input) {
        storeList.add(input); // add to store
        horizontalLine();
        System.out.println("added: " + input);
        horizontalLine();
    }

    /* Level 2 */
    private static void displayStoreList() {
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println((i + 1) + ". " + storeList.get(i));
        }
        horizontalLine();
    }
}

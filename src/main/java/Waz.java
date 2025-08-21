import java.util.ArrayList;
import java.util.Scanner;

public class Waz {
    private static ArrayList<Task> storeList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet User
        greet();

        // Loop
        while (true) {
            String input = scanner.nextLine();

            String[] split = input.split(" ", 2);
            String command = split[0];

            switch (command) {
                case "bye":
                    exit();
                    return; // exits program
                case "list":
                    displayStoreList();
                    break;
                case "unmark":
                    Task unmarkTask = storeList.get(Integer.parseInt(split[1])-1);
                    unmarkTask.markAsNotDone();
                    horizontalLine();
                    break;
                case "mark":
                    Task markTask = storeList.get(Integer.parseInt(split[1])-1);
                    markTask.markAsDone();
                    horizontalLine();
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
        Task t = new Task(input);
        storeList.add(t); // add new task to store
        horizontalLine();
        System.out.println("added: " + input);
        horizontalLine();
    }

    /* Level 2 */
    private static void displayStoreList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println((i + 1) + ". " + storeList.get(i));
        }
        horizontalLine();
    }

    /* Level 3 */
}

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
            try {
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
                        int index = Integer.parseInt(split[1])-1;
                        if (index < 0 || index > storeList.size()) {
                            throw new WazException("OOPS! That task number doesn't exist");
                        }
                        Task unmarkTask = storeList.get(index);
                        unmarkTask.markAsNotDone();
                        horizontalLine();
                        break;
                    case "mark":
                        int index2 = Integer.parseInt(split[1])-1;
                        if (index2 < 0 || index2 > storeList.size()) {
                            throw new WazException("OOPS! That task number doesn't exist");
                        }
                        Task markTask = storeList.get(index2);
                        markTask.markAsDone();
                        horizontalLine();
                        break;
                    default:
                        echo(input, command); // add task to store list (todos, deadlines, events)
                }
            } catch (WazException e) {
                System.out.println(e.getMessage());
                horizontalLine();
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
    private static void echo(String input, String command) {
        Task t = null;
        try {
            switch (command) {
                case "todo":
                    String[] parts1 = input.split(" ", 2);
                    if (parts1.length < 2 || parts1[1].trim().isEmpty()) {
                        throw new WazException("A todo task needs a description!");
                    }
                    t = new Todo(parts1[1].trim());
                    break;
                case "deadline":
                    String[] parts2 = input.split(" /by ", 2);
                    String[] descSplit = parts2[0].split(" ", 2);
                    if (descSplit.length < 2 || descSplit[1].trim().isEmpty()) {
                        throw new WazException("A deadline task needs a description!");
                    }
                    else if (parts2.length < 2 || parts2[1].trim().isEmpty()) {
                        throw new WazException("A deadline task needs a deadline!");
                    }
                    t = new Deadline(descSplit[1], parts2[1]); // task name, deadline by...
                    break;
                case "event":
                    String[] event = input.split(" /from ", 2);
                    String[] eventDescSplit = event[0].split(" ", 2);
                    if (eventDescSplit.length < 2 || eventDescSplit[1].trim().isEmpty()) {
                        throw new WazException("A event task needs a description!");
                    }

                    if (event.length < 2) {
                        throw new WazException("A event task must include /from and /to!");
                    }

                    String[] time = event[1].split(" /to ", 2); // from and to
                    if (time.length < 2) {
                        throw new WazException("A event task must include /from and /to!");
                    }
                    t = new Event(eventDescSplit[1], time[0], time[1]); // task name, from, to
                    break;
                default:
                    throw new WazException("OOPS! I'm sorry, but I don't know what that means :-(");
            }

            storeList.add(t); // add new task to store

            horizontalLine();
            System.out.println("Got it. I've added this task:\n" + t);
            System.out.println("Now you have " + storeList.size() + " tasks in the list.");
            horizontalLine();
        } catch (WazException e) {
            System.out.println(e.getMessage());
            horizontalLine();
        }

    }

    /* Level 2 */
    private static void displayStoreList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println((i + 1) + ". " + storeList.get(i));
        }
        horizontalLine();
    }

    /* Level 3 */
}

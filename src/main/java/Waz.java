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
                String argument = (split.length > 1) ? split[1] : "";

                switch (command) {
                case "bye":
                    exit();
                    return;
                case "list":
                    displayStoreList();
                    break;
                case "unmark":
                    setTaskStatus(argument, false);
                    break;
                case "mark":
                    setTaskStatus(argument, true);
                    break;
                case "delete":
                    deleteTask(argument);
                    break;
                case "todo":
                    addTask(argument, command);
                    break;
                case "deadline":
                    addTask(argument, command);
                    break;
                case "event":
                    addTask(argument, command);
                    break;
                default:
                    throw new WazException("Invalid Command");
                }
            } catch (WazException e) {
                System.out.println(e.getMessage());
                horizontalLine();
            }
        }
    }

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
    private static Task getTaskByArgument(String argument) throws WazException {
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            throw new WazException("OOPS! Please provide a valid task number.");
        }

        int index = Integer.parseInt(argument) - 1;
        if (index < 0 || index >= storeList.size()) {
            throw new WazException("OOPS! That task number doesn't exist");
        }

        return storeList.get(index);
    }
    private static void setTaskStatus(String argument, boolean isMarked) throws WazException {
        Task task = getTaskByArgument(argument);
        if (isMarked) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        horizontalLine();
    }
    private static void deleteTask(String argument) throws WazException {
        Task delTask = getTaskByArgument(argument);
        storeList.remove(delTask);
        System.out.println("Noted. I've removed this task:\n" + delTask);
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        horizontalLine();
    }
    private static Task createDeadlineTask(String argument) throws WazException {
        String[] parts = argument.split("/by", 2);

        if (parts[0].trim().isEmpty()) { // Check if description is empty
            throw new WazException("A deadline task needs a description!");
        } else if (parts.length < 2 || parts[1].trim().isEmpty()) { // Check if /by is missing or deadline is empty
            throw new WazException("A deadline task needs a deadline!");
        }

        return new Deadline(parts[0], parts[1]); // task name, deadline by...
    }
    private static Task createEventTask(String argument) throws WazException {
        String[] event = argument.split("/from", 2);

        if(event[0].trim().isEmpty()) { // Check if /from is missing or description is empty
            throw new WazException("A event task needs a description!");
        } else if (event.length < 2) {
            throw new WazException("A event task must include /from and /to!");
        }

        String[] time = event[1].split("/to", 2); // from and to
        if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) { // Check if /to is missing or description empty
            throw new WazException("A event task must include /from and /to!");
        }
        return new Event(event[0], time[0], time[1]); // task name, from, to
    }
    private static void addTask(String argument, String taskType) throws WazException {
        Task t = null;

        if (argument.trim().isEmpty()) {
            throw new WazException("A " + taskType + " task needs a description!");
        }

        if (taskType.equals("todo")) {
            t = new Todo(argument);
        } else if (taskType.equals("deadline")) {
            t = createDeadlineTask(argument);
        } else {
            t = createEventTask(argument);
        }

        storeList.add(t); // add new task to store
        horizontalLine();
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        horizontalLine();
    }
    private static void displayStoreList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println((i + 1) + ". " + storeList.get(i));
        }
        horizontalLine();
    }
}

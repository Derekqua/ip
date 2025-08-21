import java.util.Scanner;

public class Waz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet User
        greet();

        // Loop
        while (true) {
            String input = scanner.nextLine();

            // Input is bye, break loop
            if (input.equals("bye")) {
                exit();
                break;
            }

            // Echo command entered by the user
            echo(input);

        }

        scanner.close();
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

    private static void echo(String input) {
        horizontalLine();
        System.out.println(input);
        horizontalLine();
    }

}

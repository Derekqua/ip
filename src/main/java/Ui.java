public class Ui {

    /**
     * Prints a goodbye message and a horizontal line.
     * This method is called when the program is exiting.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    /**
     * Prints a greeting message and a horizontal line.
     * This method is called when the program starts.
     */
    public void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Waz");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    /**
     * Prints a horizontal line used to format the chatbot output.
     */
    public void horizontalLine() {
        System.out.println("----------------------------------------------");
    }
}

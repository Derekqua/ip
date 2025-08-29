package waz.ui;

import waz.task.Task;
import waz.exception.WazException;
import java.util.ArrayList;

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
        System.out.println("Hello! I'm waz.Waz");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    /**
     * Prints a horizontal line used to format the chatbot output.
     */
    public void horizontalLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Prints out error message
     */
    public void showErrorMsg(WazException e) {
        System.out.println(e.getMessage());
        horizontalLine();
    }

    /**
     * Prints a confirmation message that a task has been added into the list
     */
    public void showAddedTask(Task task, int size) {
        horizontalLine();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        horizontalLine();
    }

    /**
     * Prints a confirmation message that a task has been deleted from the list
     */
    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        horizontalLine();
    }



    /**
     * Displays all tasks currently in the store list
     */
    public void displayList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        horizontalLine();
    }

    /**
     * Show confirmation message that task has been unmarked
     */
    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Show confirmation message that task has been marked
     */
    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }
}

package waz.ui;

import waz.task.Task;
import waz.exception.WazException;
import waz.task.TaskList;

import java.util.ArrayList;

/**
 * The {@code Ui} class handles all interactions with the user.
 * <p>
 * It is responsible for displaying messages, prompts, and responses
 * to the user in the console.
 * </p>
 *
 * <p>Responsibilities include:</p>
 * <ul>
 *     <li>Displaying welcome, exit, and error messages</li>
 *     <li>Showing task-related updates (e.g., add, delete, mark/unmark)</li>
 *     <li>Displaying all tasksin the list</li>
 * </ul>
 *
 * <p>
 * </p>
 */
public class Ui {

    /**
     * Prints a goodbye message and a horizontal line.
     * This method is called when the program is exiting.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        showHorizontalLine();
    }

    /**
     * Prints a greeting message and a horizontal line.
     * This method is called when the program starts.
     */
    public void showGreetMessage() {
        showHorizontalLine();
        System.out.println("Hello! I'm waz.Waz");
        System.out.println("What can I do for you?");
        showHorizontalLine();
    }

    /**
     * Prints a horizontal line used to format the chatbot output.
     */
    public void showHorizontalLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Prints out error message
     */
    public void showErrorMsg(WazException e) {
        System.out.println(e.getMessage());
        showHorizontalLine();
    }

    /**
     * Prints a confirmation message that a task has been added into the list
     */
    public void showAddedTask(Task task, int size) {
        showHorizontalLine();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showHorizontalLine();
    }

    /**
     * Prints a confirmation message that a task has been deleted from the list
     */
    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showHorizontalLine();
    }

    /**
     * Displays all tasks currently in the store list
     */
<<<<<<< HEAD
    public void showTaskList(ArrayList<Task> taskList) {
=======
    public void displayList(TaskList taskList) {
>>>>>>> branch-Level-9
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
        showHorizontalLine();
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

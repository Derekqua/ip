package waz.ui;

import waz.exception.WazException;
import waz.task.Task;
import waz.task.TaskList;

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
     *
     * @return a formatted string
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a greeting message and a horizontal line.
     * This method is called when the program starts.
     *
     * @return a formatted string
     */
    public String showGreetMessage() {
        return "Hello I'm Waz.\n" + "What can I do for you?";
    }

    /**
     * Prints a horizontal line used to format the chatbot output.
     *
     * @return a formatted string
     */
    public String showHorizontalLine() {
        return "----------------------------------------------\n";
    }

    /**
     * Prints out error message
     *
     * @return a formatted string
     */
    public String showErrorMsg(WazException e) {
        return e.getMessage();
    }

    /**
     * Prints a confirmation message that a task has been added into the list
     *
     * @return a formatted string
     */
    public String showAddedTask(Task task, int size) {
        assert task != null : "Task must not be null";
        assert size >= 0 : "Size must not be non-negative";
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks" + " in the list.";
    }

    /**
     * Prints a confirmation message that a task has been deleted from the list
     *
     * @return a formatted string
     */
    public String showDeletedTask(Task task, int size) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays all tasks currently in the task list. if task list is isMatch, display matching task message.
     * Otherwise, default task message.
     *
     * @return a formatted string
     */
    public String showTaskList(TaskList taskList, boolean isMatch) {
        String sentence = "";
        if (isMatch) {
            sentence = "Here are the matching tasks in your list:\n";
        } else {
            sentence = "Here are the tasks in your list:\n";
        }

        for (int i = 0; i < taskList.size(); i++) {
            sentence += (i + 1) + ". " + taskList.getTask(i) + "\n";
        }
        return sentence;
    }

    /**
     * Show confirmation message that task has been unmarked
     *
     * @return a formatted string
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task + "\n";
    }

    /**
     * Show confirmation message that task has been marked
     *
     * @return a formatted string
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }
}

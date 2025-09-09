package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Task;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command to find tasks in the task list that match a given keyword. The search is also case
 * in-sensitive.
 *
 */
public class FindCommand extends Command {
    /**
     * Creates a FindCommand with the given search keyword
     * @param argument the keyword to find
     */
    public FindCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the find command by searching for tasks in the task list that contain the given keyword. Displays the
     * matching tasks if found, otherwise informs the user that no tasks matched.
     * @param taskList the list of tasks
     * @param ui the Ui
     * @param storage the storage
     * @return a formatted string
     * @throws WazException if the keyword is empty
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        assert !argument.trim().isEmpty() : "Keyword to search is empty";
        if (argument.trim().isEmpty()) {
            throw new WazException("Please provide a keyword to search");
        }

        TaskList matchingTasks = new TaskList();
        String keyword = argument.trim().toLowerCase();

        assert !taskList.getTaskList().isEmpty() : "Tasklist should not be empty";
        // Add task that match description into the list
        for (Task task: taskList.getTaskList()) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.getTaskList().isEmpty()) { // No matching task found
            throw new WazException("No tasks found matching: " + keyword);
        } else { // matching task found, display list of task related to keyword
            return ui.showTaskList(matchingTasks, true);
        }
    }
}

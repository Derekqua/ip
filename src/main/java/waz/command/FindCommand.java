package waz.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * @param commandInput the keyword to find
     */
    public FindCommand(String commandInput) {
        super(commandInput);
    }

    /**
     * Executes the find command by searching for tasks in the task list that contain the given keyword. Displays the
     * matching tasks if found, otherwise informs the user that no tasks matched.
     * @param tasks the list of tasks
     * @param ui the Ui
     * @param storage the storage
     * @return a formatted string
     * @throws WazException if the keyword is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WazException {
        boolean isKeywordEmpty = commandInput.trim().isEmpty();
        assert !isKeywordEmpty : "Keyword to search is empty";

        if (isKeywordEmpty) {
            throw new WazException("Please provide a keyword to search");
        }

        TaskList matchingTasks = new TaskList();
        String keyword = commandInput.trim().toLowerCase();

        assert !tasks.getTaskList().isEmpty() : "Tasklist should not be empty";
        // Add task that match description into the list
        ArrayList <Task> filteredTasks = tasks.getTaskList().stream()
                .filter(task -> task.toString().toLowerCase().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        matchingTasks = new TaskList(filteredTasks);

        boolean isTasksEmpty = matchingTasks.getTaskList().isEmpty();
        if (isTasksEmpty) { // No matching task found
            throw new WazException("No tasks found matching: " + keyword);
        } else { // matching task found, display list of task related to keyword
            return ui.showTaskList(matchingTasks, true);
        }
    }
}

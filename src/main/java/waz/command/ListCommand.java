package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command that displays all tasks in the task list
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand with no arguments
     */
    public ListCommand() {
        super("");
    }

    /**
     * Execute the command to display all tasks in the task list
     * @param tasks the list of task
     * @param ui the Ui to display the list
     * @param storage the storage (not used in this command)
     * @return a formatted string
     * @throws WazException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WazException {
        return ui.showTaskList(tasks, false);
    }
}

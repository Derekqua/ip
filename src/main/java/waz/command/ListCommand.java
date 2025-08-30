package waz.command;

import waz.task.TaskList;
import waz.exception.WazException;
import waz.storage.Storage;
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
     * @param taskList the list of task
     * @param ui the Ui to display the list
     * @param storage the storage (not used in this command)
     * @throws WazException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        ui.showTaskList(taskList.getTaskList());
    }
}
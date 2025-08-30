package waz.command;

import waz.task.TaskList;
import waz.exception.WazException;
import waz.storage.Storage;
import waz.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    /**
     * Display the list of task in the task list
     * @param taskList the list of task
     * @param ui
     * @param storage
     * @throws WazException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        ui.displayList(taskList);
    }
}
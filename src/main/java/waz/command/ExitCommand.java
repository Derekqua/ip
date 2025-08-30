package waz.command;

import waz.task.TaskList;
import waz.storage.Storage;
import waz.ui.Ui;

public class ExitCommand extends Command{
    public ExitCommand() {
        super("");
    }

    /**
     * Exits the loop
     *
     * @param taskList the list of task
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

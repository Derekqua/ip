package waz.command;

import waz.storage.Storage;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command that exits the application
 * When executed, it triggers the Ui to display the exit message.
 * The {@link #isExit()}} method returns true to indicate the application should terminate
 */
public class ExitCommand extends Command {
    /**
     * Constructs an ExitCommand with no arguments
     */
    public ExitCommand() {
        super("");
    }

    /**
     * Executes the exit command
     * Display goodbye message using the {@link Ui} class
     * @param taskList the list of task
     * @param ui the Ui to show the exit message
     * @param storage the storage
     * @return a formatted string
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showExitMessage();
    }

    /**
     * Indicates that this command exits the application
     * @return true always
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

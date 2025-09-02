package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents an abstract command that can be executed on the task list
 * <p>
 *     Each concrete command should implement the {@link #execute(TaskList, Ui, Storage)} method to define its
 *     specific behavior.
 * </p>
 */
public abstract class Command {
    protected String argument;

    /**
     * Constructs a Command with the given argument
     *
     * @param argument the argument string for the command
     */
    public Command(String argument) {
        this.argument = argument;
    }

    /**
     * Executes the command with access to the task list, Ui, storage
     * @param taskList the list of tasks
     * @param ui the Ui to interact with the user
     * @param storage the storage to persist changes
     * @throws WazException if the command encounters an error such as invalid input
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws WazException;

    /**
     * Indicates whether executing this command should terminate the application
     * <p>
     *     By default, commands do not exit. Only ExitCommand will override it to true
     * </p>
     *
     * @return true if this command signals program to exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}

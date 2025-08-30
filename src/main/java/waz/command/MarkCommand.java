package waz.command;

import waz.task.TaskList;
import waz.task.Task;
import waz.exception.WazException;
import waz.storage.Storage;
import waz.ui.Ui;

/**
 * Represents a command that marks a task as done
 * <p>
 * This command takes an index argument referring to the task in the task list,
 * and updates the task's status to "not done". It also updates the storage
 * and prints a confirmation message via the {@link Ui} class.
 * </p>
 */
public class MarkCommand extends Command {

    /**
     * Constructs an UnmarkCommand with the given argument
     * @param argument the index of the task to be unmarked, as a string
     */
    public MarkCommand(String argument) {
        super(argument);
    }


    /**
     * Executes the command to mark a task in the task list
     *
     * @param taskList the list of task
     * @param ui the Ui to display messages
     * @param storage the storage for saving tasks
     * @throws WazException if the index is invalid or out of range
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            throw new WazException("OOPS! Please provide a valid task number.");
        }

        int index = Integer.parseInt(argument) - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new WazException("OOPS! That task number doesn't exist");
        }

        Task task =  taskList.getTask(index);
        task.markAsDone();
        ui.showMarkTask(task);
        storage.saveContent(taskList.getTaskList());
    }
}

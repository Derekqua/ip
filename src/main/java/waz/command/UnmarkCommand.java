package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Task;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command that marks a task as not done
 * <p>
 * This command takes an index argument referring to the task in the task list,
 * and updates the task's status to "not done". It also updates the storage
 * and prints a confirmation message via the {@link Ui} class.
 * </p>
 */
public class UnmarkCommand extends Command {
    /**
     * Constructs an UnmarkCommand with the given argument
     * @param commandInput the index of the task to be unmarked, as a string
     */
    public UnmarkCommand(String commandInput) {
        super(commandInput);
    }

    /**
     * Executes the command to unmark a task in the task list
     *
     * @param tasks the list of task
     * @param ui the Ui to display messages
     * @param storage the storage for saving tasks
     * @return a formatted string
     * @throws WazException if the index is invalid or out of range
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WazException {
        boolean isInputEmpty = commandInput.isEmpty();
        boolean isNotDigit = !commandInput.matches("\\d+");
        boolean isInvalidTaskNumber = isInputEmpty || isNotDigit;

        if (isInvalidTaskNumber) {
            throw new WazException("OOPS! Please provide a valid task number.");
        }

        int index = Integer.parseInt(commandInput) - 1;

        boolean isNegativeNumber = index < 0;
        boolean isOutOfRange = index >= tasks.size();
        boolean isIndexOutOfRange = isNegativeNumber || isOutOfRange;
        assert !isIndexOutOfRange : "Invalid task number";

        if (isIndexOutOfRange) {
            throw new WazException("OOPS! That task number doesn't exist");
        }

        Task task = tasks.getTask(index);
        task.markAsNotDone();
        storage.saveContent(tasks.getTaskList());
        return ui.showUnmarkTask(task);
    }
}

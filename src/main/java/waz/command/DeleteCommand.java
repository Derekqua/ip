package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Task;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command that deletes a task from the task list
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the specified argument
     * @param commandInput
     */
    public DeleteCommand(String commandInput) {
        super(commandInput);
    }

    /**
     * Executes the delete command
     * <p>
     *     Removes the task at the given index from the task list, updates the Ui with a confirmation message, and
     *     save the updated task list to storage
     * </p>
     *
     * @param tasks the list of task
     * @param ui the Ui to show confirmation message
     * @param storage the storage to save the updated task list
     * @return a formatted string
     * @throws WazException if the number is not a digit or index is out of range
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

        if (isIndexOutOfRange) {
            throw new WazException("OOPS! That task number doesn't exist");
        }

        Task deleteTask = tasks.getTask(index);
        tasks.deleteTask(deleteTask);
        storage.saveContent(tasks.getTaskList());
        return ui.showDeletedTask(deleteTask, tasks.size());
    }
}

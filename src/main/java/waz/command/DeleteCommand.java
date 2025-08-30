package waz.command;

import waz.task.TaskList;
import waz.task.Task;
import waz.exception.WazException;
import waz.storage.Storage;
import waz.ui.Ui;

/**
 * Represents a command that deletes a task from the task list
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the specified argument
     * @param argument
     */
    public DeleteCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the delete command
     * <p>
     *     Removes the task at the given index from the task list, updates the Ui with a confirmation message, and
     *     save the updated task list to storage
     * </p>
     *
     * @param taskList the list of task
     * @param ui the Ui to show confirmation message
     * @param storage the storage to save the updated task list
     * @throws WazException if the number is not a digit or index is out of range
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
        taskList.deleteTask(task);
        ui.showDeletedTask(task, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}
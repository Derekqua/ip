package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Deadline;
import waz.task.Task;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command to add {@link Deadline} task to the task list
 * <p>
 *     When executed, this command validates the input description, start time and end time. Creates a new Deadline task
 *     and adds it to the {@link TaskList}, updates the Ui, save the updated list to storage.
 * </p>
 */
public class AddDeadlineCommand extends Command {
    /**
     * Constructs an AddDeadlineCommand with the given task description, deadline
     * @param argument the description of the Deadline task
     */
    public AddDeadlineCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command by creating a Deadline task and adding it to the task list.
     * <p>
     *     The method also updates the Ui to show the newly added task and persists the updated list to the storage file
     * </p>>
     * @param taskList the list of task
     * @param ui the Ui to show feedback to the user
     * @param storage the storage to save the updated task list
     * @throws WazException if the description, /by parts are missing or empty
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        String[] parts = argument.split("/by", 2);

        if (parts[0].trim().isEmpty()) { // Check if description is empty
            throw new WazException("A deadline task needs a description!");
        } else if (parts.length < 2 || parts[1].trim().isEmpty()) { // Check if /by is missing or deadline is empty
            throw new WazException("A deadline task needs a deadline!");
        }

        Task deadline = new Deadline(parts[0].trim(), parts[1].trim()); // task name, deadline by...
        taskList.addTask(deadline);
        ui.showAddedTask(deadline, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}
